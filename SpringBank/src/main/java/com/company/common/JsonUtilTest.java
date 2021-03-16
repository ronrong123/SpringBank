package com.company.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.board.controller.JsonUtil;

public class JsonUtilTest {
	public static void main(String[] args) {
		JsonUtil json = new JsonUtil();
		Map<String,Object> map = new HashMap<>();
		map.put("username", "홍길동");
		map.put("age", "30");
		map.put("dept", "개발");
		String result = json.toJson(map);
		System.out.println(result);  //
		//vo = map 같은개념임 둘다 {} vo대신 map을 쓰기도함
		
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map2 = new HashMap<>();
		map2.put("username", "홍길동2");
		map2.put("age", "31");
		map2.put("dept", "개발2");
		list.add(map);
		list.add(map2);
		String result2 =  json.toJson(list);
		System.out.println(result2);
	}
}
