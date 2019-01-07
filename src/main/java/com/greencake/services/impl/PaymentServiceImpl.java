package com.greencake.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.greencake.config.WxConfig;
import com.greencake.domain.Payment;
import com.greencake.rest.exceptions.WxPayException;
import com.greencake.services.PaymentService;
import com.greencake.services.dto.UnifiedOrderParam;
import com.greencake.utils.WxPayUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private WxConfig wxConfig;
	@Autowired
	private RestTemplate restTemplate;
//	@Autowired
//	private PaymentRepository paymentRepository;

	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public Map<String, String> unifiedOrder(UnifiedOrderParam param) throws Exception {
		Calendar start = Calendar.getInstance();
		Calendar expire = Calendar.getInstance();
		expire.add(Calendar.MINUTE, 10);

		Map<String, String> data = new HashMap<>();
		data.put("appid", wxConfig.getAppId());
		data.put("mch_id", wxConfig.getMchid());
		data.put("device_info", "GreenCake"); // 可选， 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
		data.put("nonce_str", WxPayUtil.generateNonceStr());

		data.put("sign_type", "MD5");// 可选，默认是MD5
		data.put("body", param.getTitle()); // 商品描述
		data.put("detail", param.getRemarks()); // 可选，商品详情
		data.put("attach", param.getRemarks()); // 可选，附加数据
		data.put("out_trade_no", generateTradeNo());
		data.put("fee_type", "CNY"); // 可选，默认值是标价币种
		data.put("total_fee", param.getPayAmt().multiply(BigDecimal.valueOf(100)).toBigInteger().toString()); // 标价金额
		data.put("spbill_create_ip", param.getRemoteIp()); // 终端IP
		data.put("time_start", dateFormatter.format(start.getTime())); // 交易起始时间
		data.put("time_expire", dateFormatter.format(expire.getTime())); // 交易结束时间

		data.put("goods_tag", "WXG"); // 可选，订单优惠标记，使用代金券或立减优惠功能时需要的参数
		data.put("notify_url", wxConfig.getNotifyUri()); // 通知地址
		data.put("trade_type", "JSAPI"); // 交易类型
		data.put("product_id", generateTradeNo()); // 可选，商品ID
		data.put("limit_pay", "no_credit"); // 可选，指定支付方式
		data.put("openid", param.getOpenId()); // 可选，用户标识

		String xml = WxPayUtil.generateSignedXml(data, wxConfig.getKey());
		String result = restTemplate.postForObject(wxConfig.getUnifiedOrderApi(), xml, String.class);
		Map<String, String> resultData = getAndValidateResultData(result);

		if (!resultData.getOrDefault("result_code", "").equals("SUCCESS"))
			throw new WxPayException(resultData.get("return_code"), resultData.get("return_msg"),
					resultData.get("err_code"), resultData.get("err_code_des"));
		
		//Save payment information into db
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        //payment.setUserId(user.getId());
         
        payment.setTradeNo(data.get("out_trade_no"));
        payment.setTitle(data.get("body"));
        payment.setAttach(data.get("attach"));
        payment.setTotalFee(param.getPayAmt());
        payment.setRemoteIP(data.get("spbill_create_ip"));
        payment.setTradeType(data.get("trade_type"));
        payment.setDeviceInfo(data.get("device_info"));
        payment.setCreationDate(start.getTime());
        payment.setStatus('N'); //NOTPAY(订单未支付)
        //paymentRepository.save(payment);
        
      //Make parameters for JS API call
        Map<String, String> jsApiData = new HashMap<>();
        jsApiData.put("appId", resultData.get("appid"));
        jsApiData.put("timeStamp", WxPayUtil.getCurrentTimestamp() + "");
        jsApiData.put("nonceStr", WxPayUtil.generateNonceStr());
        jsApiData.put("package", "prepay_id=" + resultData.get("prepay_id"));
        jsApiData.put("signType", "MD5");
        jsApiData.put("paySign", WxPayUtil.generateSignature(jsApiData, wxConfig.getKey()));
        jsApiData.put("title", payment.getTitle());
        jsApiData.put("attach", payment.getAttach());
        jsApiData.put("totalFee", payment.getTotalFee().toString());
        jsApiData.put("tradeNo", payment.getTradeNo());
         

        return jsApiData;
	}

	private String generateTradeNo() {
		return wxConfig.getMchid() + dateFormatter.format(Calendar.getInstance().getTime()) + new Random().nextInt(999);
	}

	private Map<String, String> getAndValidateResultData(String result) throws Exception {
		if (!result.startsWith("<xml>"))
			throw new WxPayException("Result string is not starting with '<xml>'");

		Map<String, String> resultData = WxPayUtil.xmlToMap(result);

		if (!resultData.containsKey("return_code"))
			throw new WxPayException("Result does not contain key 'return_code'");
		if (!resultData.get("return_code").equals("SUCCESS"))
			throw new WxPayException(resultData.get("return_code"), resultData.get("return_msg"));
		if (!WxPayUtil.isSignatureValid(resultData, wxConfig.getKey()))
			throw new WxPayException("Result signature is not valid");

		return resultData;
	}
}
