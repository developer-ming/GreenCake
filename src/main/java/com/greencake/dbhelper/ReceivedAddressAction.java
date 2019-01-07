package com.greencake.dbhelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.greencake.domain.Receivedaddress;

@Service
public class ReceivedAddressAction {
	@Autowired
	private JdbcTemplate jdbcTemp;
	
	public void InsertAddress(Receivedaddress recAdds) {
		try {
			String sql = "INSERT INTO `guirencake`.`receivedaddress`(`receiveduser`,`receivedphone`,`receivedaddress`,`wx_openid`)VALUES('"+recAdds.getReceiveduser()+"','"+recAdds.getReceivedphone()+"','"+recAdds.getReceivedaddress()+"','"+recAdds.getWx_openid()+"')";
			jdbcTemp.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}