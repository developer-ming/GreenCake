package com.greencake.dbhelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.greencake.domain.Userinfo;
import com.greencake.dto.DataAnalysisDTO;
import com.greencake.utils.AnalysisType;

@Service
public class UserInfoAction {
	 

	@Autowired
	private JdbcTemplate jdbcTemp;

	public String insertUserInfo(Userinfo userInfo) {
		boolean flag = false;
		try {
			String insertSql = "INSERT INTO `guirencake`.`userinfo`(`username`,`gender`,`phone`,`birthplace`,`currentaddress`,";
			insertSql += "`calendartype`,`birthday`,`birthtime`,`cakeprice`,`cakesize`,`personnum`,`fortunetype`,`receiveduser`,`receivedphone`,`receivedaddress`,`wx_openid`,`createdate`)";
			insertSql += "VALUES('" + userInfo.getUsername() + "','" + userInfo.getGender() + "','"
					+ userInfo.getPhone() + "'";
			insertSql += ",'" + userInfo.getBirthplace() + "','" + userInfo.getCurrentaddress() + "'";
			insertSql += ",'" + userInfo.getCalendartype() + "','" + userInfo.getBirthday() + "'";
			insertSql += ",'" + userInfo.getBirthtime() + "','" + userInfo.getCakeprice() + "','"
					+ userInfo.getCakesize() + "','" + userInfo.getPersonnum() + "'";
			insertSql += ",'" + userInfo.getFortunetype() + "','" + userInfo.getReceiveduser() + "','"
					+ userInfo.getReceivedphone() + "','" + userInfo.getReceivedaddress() + "','"
					+ userInfo.getWxopenid() + "','" + userInfo.getCreatedate() + "')";

			jdbcTemp.execute(insertSql);

			flag = true;

		} catch (Exception e) {
			System.out.println("**************user info inser error*******************:  " + e.getMessage());
			flag = false;
		}

		if (flag)
			return "ok";
		else
			return "failed";
	}

	public List<Userinfo> getUsersByOpenId(String opendId, String condition) {
		if (opendId == null)
			return null;

		List<Userinfo> uList = new ArrayList<Userinfo>();

		String selectUsersSql = "SELECT `userinfo`.`userid`,`userinfo`.`username`,`userinfo`.`gender`, "
				+ "`userinfo`.`phone`,`userinfo`.`birthplace`,`userinfo`.`currentaddress`,`userinfo`.`calendartype`, "
				+ "`userinfo`.`birthday`, `userinfo`.`birthtime`, `userinfo`.`cakeprice`,`userinfo`.`cakesize`,`userinfo`.`personnum`, "
				+ "`userinfo`.`fortunetype`,`userinfo`.`receiveduser`,`userinfo`.`receivedphone`,`userinfo`.`receivedaddress`, `userinfo`.`wx_openid`, `userinfo`.`createdate` "
				+ "FROM `guirencake`.`userinfo` where wx_openid = '" + opendId + "' order by createdate desc ";
		if(condition != null && condition != "") {
			selectUsersSql = selectUsersSql.concat(condition);
		}

		List<Map<String, Object>> list = jdbcTemp.queryForList(selectUsersSql);
		for (Map<String, Object> map : list) {
			Set<Entry<String, Object>> entries = map.entrySet();
			Userinfo userinfo = new Userinfo();
			if (entries != null) {
				Iterator<Entry<String, Object>> iterator = entries.iterator();

				while (iterator.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					String key = entry.getKey().toString();
					Object value = entry.getValue();

					switch (key) {
					case "userid":
						userinfo.setUserid(value != null ? Integer.parseInt(value.toString()) : 0);
						break;
					case "username":
						userinfo.setUsername(value != null ? value.toString() : "");
						break;
					case "gender":
						userinfo.setGender(value != null ? value.toString() : "");
						break;
					case "phone":
						userinfo.setPhone(value != null ? value.toString() : "");
						break;
					case "currentaddress":
						userinfo.setCurrentaddress(value != null ? value.toString() : "");
						break;
					case "calendartype":
						userinfo.setCalendartype(value != null ? value.toString() : "");
						break;
					case "birthday":
						userinfo.setBirthday(value != null ? value.toString() : "");
						break;
					case "birthtime":
						userinfo.setBirthtime(value != null ? value.toString() : "");
						break;
					case "cakeprice":
						userinfo.setCakeprice(value != null ? value.toString() : "");
						break;
					case "cakesize":
						userinfo.setCakesize(value != null ? value.toString() : "");
						break;
					case "personnum":
						userinfo.setPersonnum(value != null ? value.toString() : "");
						break;
					case "birthplace":
						userinfo.setBirthplace(value != null ? value.toString() : "");
						break;
					case "fortunetype":
						userinfo.setFortunetype(value != null ? value.toString() : "");
						break;
					case "receiveduser":
						userinfo.setReceiveduser(value != null ? value.toString() : "");
						break;
					case "receivedphone":
						userinfo.setReceivedphone(value != null ? value.toString() : "");
						break;
					case "receivedaddress":
						userinfo.setReceivedaddress(value != null ? value.toString() : "");
						break;
					case "wx_openid":
						userinfo.setWxopenid(value != null ? value.toString() : "");
						break;
					case "createdate":
						userinfo.setCreatedate(value != null ? value.toString() : "");
						break;
					}
				}

				uList.add(userinfo);
			}
		}

		return uList;
	}

	public List<DataAnalysisDTO> getSummaryDataByDatetime(AnalysisType aType, String openid) {
		if (openid == null)
			return null;

		String sql = "";

		switch (aType) {
		case YearQuery:
			sql = "SELECT concat(year(createdate)) release_date ,sum(cakeprice) total_money ,count(*) total_count "
					+ " FROM guirencake.userinfo where wx_openid='" + openid + "' group by release_date";
			break;
		case MonthQuery:
			sql = "SELECT concat(year(createdate),'-',month(createdate)) release_date ,sum(cakeprice) total_money ,count(*) total_count "
					+ " FROM guirencake.userinfo where wx_openid='" + openid
					+ "' group by release_date order by release_date desc";
			break;
		case DayQuery:
			sql = "SELECT concat(year(createdate),'-',month(createdate),'-',day(createdate)) release_date ,sum(cakeprice) total_money ,count(*) total_count "
					+ " FROM guirencake.userinfo where wx_openid='" + openid
					+ "' group by release_date order by release_date desc";
			break;
		default:
			break;
		}

		List<DataAnalysisDTO> dadList = new ArrayList<DataAnalysisDTO>();
		List<Map<String, Object>> mapLists = jdbcTemp.queryForList(sql);

		for (Map<String, Object> map : mapLists) {
			Set<Entry<String, Object>> entries = map.entrySet();
			DataAnalysisDTO dad = new DataAnalysisDTO();
			if (entries != null) {
				Iterator<Entry<String, Object>> iterator = entries.iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					String key = entry.getKey().toString();
					Object value = entry.getValue();

					switch (key) {
					case "release_date":
						dad.setRelease_date(value != null ? value.toString() : "");
						break;
					case "total_money":
						dad.setTotal_money(value != null ? value.toString() : "");
						break;
					case "total_count":
						dad.setTotal_count(value != null ? value.toString() : "");
						break;
					}
				}
				dadList.add(dad);
			}
		}

		return dadList;
	}
	
}
