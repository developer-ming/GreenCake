package com.greencake.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencake.dbhelper.ReceivedAddressAction;
import com.greencake.domain.Receivedaddress;
import com.greencake.dto.UserInfo_DTO;;

@Service
public class ReceivedAddressServs {
	@Autowired
	public ReceivedAddressAction receiAction;
	
	public void addReceivedAddress(UserInfo_DTO user) {
	    Receivedaddress receivedaddress = new Receivedaddress();
	    receivedaddress.setReceiveduser(user.getReceive_username());
	    receivedaddress.setReceivedphone(user.getReceive_phone());
	    receivedaddress.setReceivedaddress(user.getReceive_address());
	    receivedaddress.setWx_openid(user.getWx_openid());
		receiAction.InsertAddress(receivedaddress); 
	}
}