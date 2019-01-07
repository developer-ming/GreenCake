package com.greencake.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author a255719
 *
 */
@Entity
@Table(name = "wxuserinfo", schema = "guirencake")
public class Wxuserinfo {
	
	public Wxuserinfo() {}
	public Wxuserinfo(String wx_openid) {
	 this.wx_openid = wx_openid;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wxuserID")
	private int wxuserid;
	@Column(name = "wx_nickName")
	private String wx_nickName;
	@Column(name = "wx_avatarUrl")
	private String wx_avatarUrl;
	@Column(name = "wx_gender")
	private String wx_gender;
	@Column(name = "wx_country")
	private String wx_country;
	@Column(name = "wx_province")
	private String wx_province;
	@Column(name = "wx_city")
	private String wx_city;
	@Column(name = "wx_language")
	private String wx_language;
	@Column(name = "wx_openid")
	private String wx_openid;

	public int getWxuserid() {
		return wxuserid;
	}

	public void setWxuserid(int wxuserid) {
		this.wxuserid = wxuserid;
	}

	public String getWx_nickName() {
		return wx_nickName;
	}

	public void setWx_nickName(String wx_nickName) {
		this.wx_nickName = wx_nickName;
	}

	public String getWx_avatarUrl() {
		return wx_avatarUrl;
	}

	public void setWx_avatarUrl(String wx_avatarUrl) {
		this.wx_avatarUrl = wx_avatarUrl;
	}

	public String getWx_gender() {
		return wx_gender;
	}

	public void setWx_gender(String wx_gender) {
		this.wx_gender = wx_gender;
	}

	public String getWx_country() {
		return wx_country;
	}

	public void setWx_country(String wx_country) {
		this.wx_country = wx_country;
	}

	public String getWx_province() {
		return wx_province;
	}

	public void setWx_province(String wx_province) {
		this.wx_province = wx_province;
	}

	public String getWx_city() {
		return wx_city;
	}

	public void setWx_city(String wx_city) {
		this.wx_city = wx_city;
	}

	public String getWx_language() {
		return wx_language;
	}

	public void setWx_language(String wx_language) {
		this.wx_language = wx_language;
	}

	public String getWx_openid() {
		return wx_openid;
	}

	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}
}
