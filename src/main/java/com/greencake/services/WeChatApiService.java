package com.greencake.services;

import com.greencake.services.dto.WeChatApiResult;

public interface WeChatApiService {
	<T extends WeChatApiResult> T get(String apiUrl, Class<T> type) throws Exception;

	<T extends WeChatApiResult> T post(String apiUrl, String data, Class<T> type) throws Exception;
}
