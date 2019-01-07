package com.greencake.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencake.rest.exceptions.WeChatApiException;
import com.greencake.services.WeChatApiService;
import com.greencake.services.dto.WeChatApiResult;

@Service
public class WeChatApiServiceImpl implements WeChatApiService {
	@Autowired
	private RestTemplate restTemplate;
	
	//private RestTemplate restTemplate = new RestTemplate();

	@Override
	public <T extends WeChatApiResult> T get(String apiUrl, Class<T> type) throws Exception {
		String result = restTemplate.getForObject(apiUrl, String.class);
		T obj = new ObjectMapper().readValue(result, type);
		if (obj.hasError())
			throw new WeChatApiException(obj.getErrcode(), obj.getErrmsg());
		return obj;
	}

	@Override
	public <T extends WeChatApiResult> T post(String apiUrl, String data, Class<T> type) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(data, headers);
		String result = restTemplate.postForObject(apiUrl, entity, String.class);
		T obj = new ObjectMapper().readValue(result, type);
		if (obj.hasError())
			throw new WeChatApiException(obj.getErrcode(), obj.getErrmsg());
		return obj;
	}
}
