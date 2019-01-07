package com.greencake.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencake.dbhelper.UserInfoAction;
import com.greencake.domain.Userinfo;
import com.greencake.dto.DataAnalysisDTO;
import com.greencake.dto.UserInfo_DTO;
import com.greencake.services.UserInfoServs;
import com.greencake.utils.AnalysisType;

@Service
public class UserInfoServiceImpl implements UserInfoServs {

	@Autowired
	private UserInfoAction userInfoAction;

	public String addUserInfo(UserInfo_DTO userInfo) {
		Userinfo entityUInfo = new Userinfo();
		entityUInfo.setUsername(userInfo.getUsername());
		entityUInfo.setGender(userInfo.getGender());
		entityUInfo.setPhone(userInfo.getPhone());
		entityUInfo.setBirthplace(userInfo.getBirthplace());
		entityUInfo.setCurrentaddress(userInfo.getPresentaddress());
		entityUInfo.setBirthday(userInfo.getBirthday());
		entityUInfo.setBirthtime(userInfo.getBirthtime());
		entityUInfo.setCalendartype(userInfo.getCalendar());
		entityUInfo.setCakeprice(userInfo.getPrice());
		entityUInfo.setCakesize(userInfo.getSize());
		entityUInfo.setPersonnum(userInfo.getPersonnum());
		entityUInfo.setFortunetype(userInfo.getFortunetype());
		entityUInfo.setReceiveduser(userInfo.getReceive_username());
		entityUInfo.setReceivedphone(userInfo.getReceive_phone());
		entityUInfo.setReceivedaddress(userInfo.getReceive_address());
		entityUInfo.setWxopenid(userInfo.getWx_openid());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			entityUInfo.setCreatedate(dateFormat.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfoAction.insertUserInfo(entityUInfo);
	}

	@Override
	public List<Userinfo> findAllUsersByOpenId(String aType, String openid, String query) throws Exception {
		String condition = "";
		switch (aType) {
		case "everyyear":
			condition = "and year(createdate) = '" + query + "'";
			break;
		case "everymonth":
			condition = "and concat(year(createdate),'-',month(createdate)) = '" + query + "'";
			break;
		case "everyday":
			condition = "and concat(year(createdate),'-',month(createdate),'-',day(createdate)) = '" + query + "'";
			break;

		default:
			break;
		}

		return userInfoAction.getUsersByOpenId(openid, condition);
	}

	@Override
	public List<DataAnalysisDTO> getSummaryDataByDatetime(String aType, String openid) {
		List<DataAnalysisDTO> dataAnalysisLists = new ArrayList<DataAnalysisDTO>();
		switch (aType) {
		case "everyyear":
			dataAnalysisLists = userInfoAction.getSummaryDataByDatetime(AnalysisType.YearQuery, openid);
			break;
		case "everymonth":
			dataAnalysisLists = userInfoAction.getSummaryDataByDatetime(AnalysisType.MonthQuery, openid);
			break;
		case "everyday":
			dataAnalysisLists = userInfoAction.getSummaryDataByDatetime(AnalysisType.DayQuery, openid);
			break;
		default:
			break;
		}

		return dataAnalysisLists;
	}

}