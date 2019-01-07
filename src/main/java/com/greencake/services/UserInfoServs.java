package com.greencake.services;

import java.util.List;

import com.greencake.domain.Userinfo;
import com.greencake.dto.DataAnalysisDTO;
import com.greencake.dto.UserInfo_DTO;

public interface UserInfoServs {

	public String addUserInfo(UserInfo_DTO userInfo);
	
	public List<Userinfo> findAllUsersByOpenId(String aType,String openid,String query) throws Exception;
	
	public List<DataAnalysisDTO> getSummaryDataByDatetime(String aType,String openid);

}