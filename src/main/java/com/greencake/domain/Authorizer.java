package com.greencake.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorizer", schema = "guirencake")
public class Authorizer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authId")
	private int authId;
	@Column(name = "wechat_account")
	private String wechat_account;

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getWechat_account() {
		return wechat_account;
	}

	public void setWechat_account(String wechat_account) {
		this.wechat_account = wechat_account;
	}
}
