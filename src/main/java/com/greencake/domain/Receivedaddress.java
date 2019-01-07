package com.greencake.domain;

public class Receivedaddress {
	private int receiveaddid;
	private String receiveduser;
	private String receivedphone;
	private String receivedaddress;
	private String wx_openid;

	public int getReceiveaddid() {
		return receiveaddid;
	}

	public void setReceiveaddid(int receiveaddid) {
		this.receiveaddid = receiveaddid;
	}

	public String getReceiveduser() {
		return receiveduser;
	}

	public void setReceiveduser(String receiveduser) {
		this.receiveduser = receiveduser;
	}
	
	public String getReceivedphone() {
		return receivedphone;
	}

	public void setReceivedphone(String receivedphone) {
		this.receivedphone = receivedphone;
	}

	public String getReceivedaddress() {
		return receivedaddress;
	}

	public void setReceivedaddress(String receivedaddress) {
		this.receivedaddress = receivedaddress;
	}

	public String getWx_openid() {
		return wx_openid;
	}

	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}
}
