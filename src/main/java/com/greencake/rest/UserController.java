package com.greencake.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greencake.domain.Userinfo;
import com.greencake.dto.DataAnalysisDTO;
import com.greencake.dto.UserInfo_DTO;
import com.greencake.dto.WxMerchantDTO;
import com.greencake.services.UserInfoServs;
import com.greencake.services.WxUserInfoServs;

@RestController
public class UserController {

	@Autowired
	private WxUserInfoServs wxUserInfoServs;

	@Autowired
	private UserInfoServs userInfoServ;

	@PostMapping(path = "/collectWxUserInfo")
	public String collectWxUserInfo(@RequestBody WxMerchantDTO wxUser) {
		String result = "The wechat user existed in Database.";
		if (wxUser != null && !wxUser.getOpenid().isEmpty()) {
			List<String> openIds = wxUserInfoServs.getWxOpenIds();
			if (!openIds.contains(wxUser.getOpenid())) {
				result = wxUserInfoServs.collectWxMerchant(wxUser);
			}
		}
		else {
			result = "OpenID is an empty.";
		}
		return result;
	}

	@PostMapping("/collectuserinfo")
	public String collectUserInfo(@RequestBody UserInfo_DTO userInfo) {
		return userInfoServ.addUserInfo(userInfo);
	}

	@GetMapping(path = "/findAllUsers")
	public List<Userinfo> findAllUsers(@RequestParam String openid) throws Exception {
		return userInfoServ.findAllUsersByOpenId("all", openid, "all");
	}

	@GetMapping(path = "/getUsersByQuery")
	public List<Userinfo> findAllUsers(@RequestParam String openid, String type, String query) throws Exception {
		return userInfoServ.findAllUsersByOpenId(type, openid, query);
	}

	@GetMapping(path = "/getSummaryData")
	public List<DataAnalysisDTO> getSummaryData(@RequestParam String dateType, @RequestParam String openid) {
		if (dateType == null || openid == null) {
			return null;
		}

		return userInfoServ.getSummaryDataByDatetime(dateType, openid);
	}

	@GetMapping(path = "/getWxOpenIds")
	public List<String> getWxOpenIds() {
		return wxUserInfoServs.getWxOpenIds();
	}
}
