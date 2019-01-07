package com.greencake.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxConfig {
	@Value("${wx.appid}")
	private String appId;
	@Value("${wx.appsecret}")
	private String appSecret;
	@Value("${wx.mchid}")
	private String mchid;
	@Value("${wx.key}")
	private String key;
	@Value("${wx.api.jscode2session}")
	private String jscodeToSessionApi;

	@Value("${wx.uri.notify}")
	private String notifyUri;
	
	@Value("${wx.api.unifiedorder}")
	private String unifiedOrderApi;

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getJscodeToSessionApi(String code) {
		return jscodeToSessionApi.replace("$JSCODE$", code);
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNotifyUri() {
		return notifyUri;
	}

	public String getUnifiedOrderApi() {
		return unifiedOrderApi;
	}

	public void setUnifiedOrderApi(String unifiedOrderApi) {
		this.unifiedOrderApi = unifiedOrderApi;
	}
}