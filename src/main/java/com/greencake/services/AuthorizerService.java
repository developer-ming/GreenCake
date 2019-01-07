package com.greencake.services;

import java.util.List;

import com.greencake.domain.Authorizer;

public interface AuthorizerService {
	public List<Authorizer> getAllAuthorizer();
	public boolean isExistInAuthorizer(String wechatAccount);
}
