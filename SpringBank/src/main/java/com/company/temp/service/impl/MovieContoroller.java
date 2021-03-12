package com.company.temp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.common.MovieAPI;

@Controller
public class MovieContoroller {
	
	@Autowired MovieAPI movieAPI;
	
	@RequestMapping("/boxOffice")
	@ResponseBody
	public List<String> boxOffice() {		
		List<String> list = new ArrayList<String>();
		Map map =  movieAPI.getBoxOffice();
		Map boxOfficeResult = (Map)map.get("boxOfficeResult");
		List<Map> dailyBoxOfficeList = (List<Map>)boxOfficeResult.get("dailyBoxOfficeList");
		for(Map movie : dailyBoxOfficeList) {
			list.add((String)movie.get("movieNm") + ":" + (String)movie.get("movieCd"));
		}
		return list;
	}
	
	@RequestMapping("/movieActor")
	@ResponseBody
	public List<String> movieActor() {		
		List<String> list = new ArrayList<String>();
		Map map =  movieAPI.getmovieInfo();
		Map movieInfoResult = (Map)map.get("movieInfoResult");
		Map movieInfo = (Map)movieInfoResult.get("movieInfo");
		List<Map> actors = (List<Map>)movieInfo.get("actors");
		for(Map movie : actors) {
			list.add((String)movie.get("peopleNm"));
		}
		
		return list;
	}
}
