package com.greencake.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greencake.config.WxConfig;
import com.greencake.services.dto.WeChatApiResult;
import com.greencake.services.WeChatApiService;

@RestController
public class AuthenticationController {

	@Autowired
	private WxConfig wxConfig;

	@Autowired
	private WeChatApiService weChatApiService;

	@GetMapping(path = "/getOpenID")
	public WeChatApiResult getOpenIDbyCode(@RequestParam String code) {
		if(code == null)
			return null;
 
		WeChatApiResult result = null;
		try {
			result = weChatApiService.get(wxConfig.getJscodeToSessionApi(code), WeChatApiResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
