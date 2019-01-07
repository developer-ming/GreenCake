package com.greencake.services.dto;
 
import org.springframework.util.StringUtils;
 
public class WeChatApiResult {	 
	private String errcode;
	private String errmsg;
	private String unionid;
	private String openid;
	private String session_key;
	
	/**
	 * -1 		system busy, please try again later.
	 * 0  		request successfully
	 * 40029    invalid code
	 * 45011    limit to 1000 each minutes
	 * */
	public boolean hasError() {
        return !StringUtils.isEmpty(errcode) && !errcode.equals("0");
    }

	public String getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public String getUnionid() {
		return unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public String getSession_key() {
		return session_key;
	}
}
