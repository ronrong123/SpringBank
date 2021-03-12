package com.company.common;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component //등록을해야 나중에 Autowired를 쓸수있음
public class MovieAPI {

	public Map getBoxOffice() {
		String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20210311";
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Map.class); //넘어온 url을 map에 담음?
	}
	
	public Map getmovieInfo() {
		String url ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=20205144";
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Map.class);
	}
}
