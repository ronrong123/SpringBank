package com.company.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.company.temp.service.BankVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@Component
public class BankAPI {
	String host="https://testapi.openbanking.or.kr";
	String client_id ="28bfeff9-60d2-4fab-9600-79a2fe99c2b9";
	String client_secret = "170ddd4c-c756-43d2-bb91-be8ca991aec6";
	String redirect_uri ="http://localhost/bank/callback";
	String orf_access_token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJNMjAyMTExNjc2Iiwic2NvcGUiOlsib29iIl0sImlzcyI6Imh0dHBzOi8vd3d3Lm9wZW5iYW5raW5nLm9yLmtyIiwiZXhwIjoxNjIzMzA2NDk1LCJqdGkiOiI3ZjcxYzBlNS00ZTg1LTQyY2EtODRlOC1kYzg2MTFlZWU1N2YifQ.-tqxOAKcb973yRHGjfKrKXNHVNoG8u9LYra0OGN5t9s";
	
	public Map<String, Object> getOrgAccessTokenRestTemplate() {
		String reqURL = host + "/oauth/2.0/token";           
        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("client_id", client_id);
        param.add("client_secret", client_secret);
        param.add("scope", "oob");
        param.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> request = 
        		new HttpEntity<MultiValueMap<String, String>>(param, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        Map map = restTemplate.postForObject(reqURL,request,Map.class);
        return map;
	}
	// ?????????
	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = host + "/oauth/2.0/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST ????????? ?????? ???????????? false??? setDoOutput??? true???
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST ????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + client_id);
            sb.append("&client_secret=" + client_secret);
            sb.append("&redirect_uri=" + redirect_uri);
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            //    ?????? ????????? 200????????? ??????
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    ????????? ?????? ?????? JSON????????? Response ????????? ????????????
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            //    Gson ?????????????????? ????????? ???????????? JSON?????? ?????? ??????
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return access_Token;
    }
	
	// ???????????? ????????????
		public Map<String,Object> getOrgAccessToken() {
	        String reqURL = host + "/oauth/2.0/token";
	        Map<String, Object> map = new HashMap<String, Object>();
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            //    POST ????????? ?????? ???????????? false??? setDoOutput??? true???
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	            //head?????? ???????????? ??????
	            
	            //    POST ????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("client_id=" + client_id);
	            sb.append("&client_secret=" + client_secret);
	            sb.append("&scope=oob");
	            sb.append("&grant_type=client_credentials");
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //    ?????? ????????? 200????????? ??????
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);
	 
	            //    ????????? ?????? ?????? JSON????????? Response ????????? ????????????
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            
	            //    Gson ?????????????????? ????????? ???????????? JSON?????? ?????? ??????
				Gson gson = new Gson();
				map = gson.fromJson(result, HashMap.class);
	            
	            
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	        
	        return map;
	    }
	// get
	public HashMap<String, Object> getAccountList (String access_Token, String use_num) {
	    
	    // ???????????? ????????????????????? ?????? ????????? ?????? ??? ????????? HashMap???????????? ??????
	    HashMap<String, Object> map = new HashMap<>();
	    String reqURL = host + "/v2.0/account/list";
	    StringBuilder qstr = new StringBuilder();
	    String user_seq_no = "1100770532";
	    String include_cancel_yn ="y";
	    String sort_order="D";
		qstr.append("user_seq_no=" + user_seq_no)
			.append("&include_cancel_yn=" + include_cancel_yn)
			.append("&sort_order="+sort_order);
	    try {
	        URL url = new URL(reqURL + "?" + qstr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        // ????????? ????????? Header??? ????????? ??????
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        // ???????????? ?????? 200?????? ????????????
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        //bufferReader??? ?????? conn??? byte????????? ???????????
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        
	        //String?????? map??? ?????? ??????
			Gson gson = new Gson();
			map = gson.fromJson(result, HashMap.class);
	        
	    
	        //userInfo.put("email", email);
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return map;
	}
	
	
	//????????????
	public String getDate() {
		String str = "";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		str = format.format(date);
		return str;
	}
	
	//32????????? ??????
	public String getRand32() {
		long time = System.currentTimeMillis(); 
		String str = Long.toString(time);		
		return str.substring(str.length()-32);
	}
	
	//9????????? ??????
	public String getRand() { 
		long time = System.currentTimeMillis(); 
		String str = Long.toString(time);		
		return str.substring(str.length()-9); //????????? 9???????????? ??????		
	}
	
	//????????????	
	public HashMap<String, Object> getBalance (BankVO vo) {
	    
	    // ???????????? ????????????????????? ?????? ????????? ?????? ??? ????????? HashMap???????????? ??????
	    HashMap<String, Object> map = new HashMap<>();
	    String reqURL = host + "/v2.0/account/balance/fin_num";
	    StringBuilder qstr = new StringBuilder();
	    
	    //??????????????????
	    String use_org_code="M202111676";
		qstr.append("bank_tran_id=" + use_org_code + "U" + getRand())
			.append("&fintech_use_num=" + vo.getFintech_use_num())
			.append("&tran_dtime="+getDate());
	    try {
	        URL url = new URL(reqURL + "?" + qstr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        // ????????? ????????? Header??? ????????? ??????
	        conn.setRequestProperty("Authorization", "Bearer " + vo.getAccess_Token());
	        // ???????????? ?????? 200?????? ????????????
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        //bufferReader??? ?????? conn??? byte????????? ???????????
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        
	        //String?????? map??? ?????? ??????
			Gson gson = new Gson();
			map = gson.fromJson(result, HashMap.class);
	        
	    
	        //userInfo.put("email", email);
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return map;
	}

}
