package com.greencake.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencake.domain.Wxuserinfo;
import com.greencake.dto.WxMerchantDTO;
import com.greencake.repository.WxUserRepository;
import com.greencake.services.WxUserInfoServs;

@Service
public class WxUserInfoImpl implements WxUserInfoServs {
	@Autowired
	private WxUserRepository wxUserRepository;

	public String collectWxMerchant(WxMerchantDTO merchant) {
		Wxuserinfo wxuser = new Wxuserinfo();
		try {
			wxuser.setWx_nickName(merchant.getNickName());
			wxuser.setWx_avatarUrl(merchant.getAvatarUrl());
			wxuser.setWx_gender(Integer.parseInt(merchant.getGender()) == 1 ? "男" : "女");
			wxuser.setWx_country(merchant.getCountry());
			wxuser.setWx_province(merchant.getProvince());
			wxuser.setWx_city(merchant.getCity());
			wxuser.setWx_language(merchant.getLanguage());
			wxuser.setWx_openid(merchant.getOpenid());
			Wxuserinfo wxu = wxUserRepository.save(wxuser);

			if (wxu != null && wxu.getWxuserid() != 0) {
				return "Ok";
			}

		} catch (Exception e) {
			throw e;
		}

		return "Failed";
	}

	public List<String> getWxOpenIds() {
		return wxUserRepository.getMerchantWxOpenIds();
	}
}
