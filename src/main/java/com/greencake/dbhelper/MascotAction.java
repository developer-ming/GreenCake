package com.greencake.dbhelper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greencake.domain.Mascot;

@Service
public class MascotAction {
//	@Autowired
//	private JdbcTemplate jdbcTemp;
	
	public List<Mascot> allMascots(){
		List<Mascot> mascots = new ArrayList<Mascot>();

		return mascots; 
	}
	
	public Mascot getMascotByZodiac() {
		
		return new Mascot();
	}
}
