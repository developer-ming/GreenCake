package com.greencake.services;

import java.util.Map;

import com.greencake.services.dto.UnifiedOrderParam;


public interface PaymentService {
	 Map<String, String> unifiedOrder(UnifiedOrderParam param) throws Exception;
}
