package com.greencake.services;

import java.util.List;

import com.greencake.dto.WxMerchantDTO;

public interface WxUserInfoServs {
	public String collectWxMerchant(WxMerchantDTO merchant);

	public List<String> getWxOpenIds();
}
