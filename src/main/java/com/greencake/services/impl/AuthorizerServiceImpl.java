package com.greencake.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencake.domain.Authorizer;
import com.greencake.repository.AuthorizerRepository;
import com.greencake.services.AuthorizerService;

@Service
public class AuthorizerServiceImpl implements AuthorizerService {

	@Autowired
	private AuthorizerRepository autoRepository;

	@Override
	public List<Authorizer> getAllAuthorizer() {
		return autoRepository.findAll();
	}

	@Override
	public boolean isExistInAuthorizer(String wechatAccount) {
		List<String> verifiedList = new ArrayList<String>();
		List<Authorizer> allAuthos = autoRepository.findAll();
		for (Iterator iterator = allAuthos.iterator(); iterator.hasNext();) {
			Authorizer authorizer = (Authorizer) iterator.next();
			if (wechatAccount.toLowerCase().equals(authorizer.getWechat_account().toLowerCase())) {
				 
			}
		}
		return false;
	}
}
