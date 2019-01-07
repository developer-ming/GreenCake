package com.greencake.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greencake.services.PaymentService;
import com.greencake.services.dto.UnifiedOrderParam;


@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
    @PostMapping("/unifiedorder")
    public Map<String, String> unifiedOrder(HttpServletRequest request, @RequestBody UnifiedOrderParam param) throws Exception {   
    	param.setRemoteIp(request.getRemoteAddr());
//    	param.setOpenId(request.getAttribute("openid").toString());
        
        Map<String, String> resultData = paymentService.unifiedOrder(param);
        return resultData;
    }
}
