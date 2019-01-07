package com.greencake.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greencake.domain.Additionalinfo;
import com.greencake.domain.Fortune;
import com.greencake.domain.Greeting;
import com.greencake.domain.Mascot;
import com.greencake.utils.CalenderConvertor;

@RestController
public class GreenCakeController {
	private static String template = "Hello,%s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private JdbcTemplate jdbcTemp;

	// @RequestMapping("/greeting")
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/getMascot")
	public Mascot getMascotByZodiac(@RequestParam(value = "mascottype", defaultValue = "mouse") String mascottype) 
	{
		Mascot mascot = new Mascot();
		
		String sql = "select * from mascot where mascottype='"+mascottype+"'";
		List<Map<String, Object>> list = jdbcTemp.queryForList(sql);
		
		for (Map<String, Object> map : list) {
			Set<Entry<String, Object>> entries = map.entrySet();
			if (entries != null) {
				Iterator<Entry<String, Object>> iterator = entries.iterator();
				
				while (iterator.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					String key = entry.getKey().toString();
					Object value = entry.getValue();
					
					switch(key)
					{
						case "mascotid":
							mascot.setMascotid(value != null ? Integer.parseInt(value.toString()) : 0);
							break;
						case "mascottype":
							mascot.setMascottype(value != null ? value.toString() : "");
							break;
						case "mascotluckycontent":
							mascot.setMascotluckycontent(value != null ? value.toString() : "");
							break;
						case "generalnotes":
							mascot.setGeneralnotes(value != null ? value.toString() : "");
							break;
					}
				}
			}
		}
		return mascot;
	}
	
	@GetMapping("/getFortuneByFortuneDatetime")
	public Fortune getFortune(@RequestParam String fortuneDatetime) {
		Fortune fortune = new Fortune();
		
		String sql = "SELECT * FROM guirencake.fortune  where   FortuneDatetime = '"+fortuneDatetime+"'";
		
		List<Map<String, Object>> list = jdbcTemp.queryForList(sql);
		for (Map<String, Object> map : list) {
			Set<Entry<String, Object>> entries = map.entrySet();
			if (entries != null) {
				Iterator<Entry<String, Object>> iterator = entries.iterator();
				
				while (iterator.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					String key = entry.getKey().toString();
					Object value = entry.getValue();
					
					switch(key)
					{
						case "FortuneID":
							fortune.setFortuneID(value != null ? Integer.parseInt(value.toString()): 0);
							break;
						case "FortuneDatetime":
							fortune.setFortuneDatetime(value != null ? value.toString() : "");
							break;
						case "HoroscopeTheory":
							fortune.setHoroscopeTheory(value != null ? value.toString() : "");
							break;
						case "FiveElements":
							fortune.setFiveElements(value != null ? value.toString() : "");
							break;
						case "FiveNumbers":
							fortune.setFiveNumbers(value != null ? value.toString() : "");
							break;
						case "FiveScore":
							fortune.setFiveScore(value != null ? value.toString() : "");
							break;
						case "FiveIndividuality":
							fortune.setFiveIndividuality(value != null ? value.toString() : "");
							break;
						case "HoroscopeLove":
							fortune.setHoroscopeLove(value != null ? value.toString() : "");
							break;
						case "HoroscopeEnterprise":
							fortune.setHoroscopeEnterprise(value != null ? value.toString() : "");
							break;
						case "HoroscopeEnterprise2":
							fortune.setHoroscopeEnterprise2(value != null ? value.toString() : "");
							break;
						case "HoroscopeEmotion":
							fortune.setHoroscopeEmotion(value != null ? value.toString() : "");
							break;
						case "HoroscopeMoney":
							fortune.setHoroscopeMoney(value != null ? value.toString() : "");
							break;
						case "CompositiveAnalysis":
							fortune.setCompositiveAnalysis(value != null ? value.toString() : "");
							break;
						case "HealthAnalysis":
							fortune.setHealthAnalysis(value != null ? value.toString() : "");
							break;
					}
				}
			}
		}
		return fortune;
	}
	
	@GetMapping("/getAdditionalInfo")
	public Additionalinfo getAdditionalInfo(@RequestParam String elementtype) {
		Additionalinfo adinfo = new Additionalinfo();
		String sql = "SELECT * FROM guirencake.additionalinfo where elementtype = '"+elementtype+"'";
		List<Map<String, Object>> list = jdbcTemp.queryForList(sql);
		
		for (Map<String, Object> map : list) {
			Set<Entry<String, Object>> entries = map.entrySet();
			if (entries != null) {
				Iterator<Entry<String, Object>> iterator = entries.iterator();
				
				while (iterator.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					String key = entry.getKey().toString();
					Object value = entry.getValue();
					
					switch(key)
					{
						case "additionalinfoid":
							adinfo.setAdditionalinfoid(value != null ? Integer.parseInt(value.toString()) : 0);
							break;
						case "elementtype":
							adinfo.setElementtype(value != null ? value.toString() : "");
							 
							break;
						case "luckynumber":
							adinfo.setLuckynumber(value != null ? value.toString() : "");
							break;
						case "cakecolor":
							adinfo.setCakecolor(value != null ? value.toString() : "");
							break;
						case "cakeshape":
							adinfo.setCakeshape(value != null ? value.toString() : "");
							break;
					}
				}
			}
		}
		return adinfo;
	}
	
	@GetMapping("/lunarToSolar")
	public String lunarToSolar(@RequestParam String lunarDate) throws Exception {
		return CalenderConvertor.lunarToSolar(lunarDate, false);
	}
	

	
	
}