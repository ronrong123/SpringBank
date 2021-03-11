package com.company.common;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;

public class GsonTest {
	public static void main(String[] args) throws JsonProcessingException {
		//json개념으로 map과 vo는 같은개념임
		Map<String, Object> map = new HashMap<>();
		map.put("name", "홍길동");
		map.put("age", 20);
		
		//자바객차 ->  String(json) 할떄 사용하는게 Gson
		Gson gson = new Gson();
		String str = gson.toJson(map);
					//자바객체를 넣으면 자동으로 json타입으로 변경
		System.out.println(str);
		
		str = "{\"name\":\"홍길동\",\"age\":20, \"dept\":\"개발\"}";
		//(String(json) -> java객체) json구조의 String을 java객체로 받을때는 fromJson을 사용
		map = gson.fromJson(str, Map.class); //첫번째인수에 String, 내가 변환하고자하는 class타입
		System.out.println(map.get("dept"));		
		
		//jackson : spring에서 제공하는 표준 spring json library @ResponseBody @RequseBody
		//자바객체 -> String(json)
		JsonMapper mapper = new JsonMapper();
		String str2 = mapper.writeValueAsString(map);
		System.out.println("jackson: " + str2);
		
		//String(json) -> 자바객체
		map = mapper.readValue(str2, Map.class);
		System.out.println("jackson: " + map);
		
	}
}
