package com.company.board.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JsonUtil {
	
	public String toJson(Map<String,Object> map) {
		StringBuilder result =new StringBuilder();
		result.append("{");
		Iterator<String> keys = map.keySet().iterator(); //맵에들어있는 키만 읽어서 리턴값이 set으로 넘어옴
		//set : 수학의 집합개념과같음 중복값을 허용안함 순서X   iterator화 시켜야 순서대로 값을 읽어낼수있음
		boolean start = true;
		while(keys.hasNext()) {
			String key = keys.next();
			String value = (String)map.get(key);
			if(!start) {
				result.append(",");
			}else {
				start =false;
			}
			result.append("\"");
			result.append(key);
			result.append("\"");
			result.append(":");
			result.append("\"");
			result.append(value);
			result.append("\"");
		}
		result.append("}");
		
		return result.toString();
	}
	
	public String toJson(List<Map<String,Object>> map) {
		StringBuilder result =new StringBuilder();
		boolean start = true;
		
		
		return result.toString();
	}
	public String toObjectJson(Object vo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String result ="";
		//to do
		Field[] fields = vo.getClass().getDeclaredFields(); //vo에 모든 필드list를 출력
		for(Field field : fields) {
			String fieldName = field.getName();
			String method = field.getName().substring(0,1).toUpperCase()
							+field.getName().substring(1);
			Method m = vo.getClass().getDeclaredMethod(method, null);
			String value = (String) m.invoke(vo, null);
			System.out.println(fieldName + ":" + value);
			
			System.out.println(field.getName());
		}
		return result;
	}
	
	public String toObjectJson(List<Object> vo) {
		String result ="";
		//to do
		return result;
	}
	
}
