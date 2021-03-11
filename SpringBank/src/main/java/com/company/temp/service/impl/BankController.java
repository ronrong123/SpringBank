package com.company.temp.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.common.BankAPI;
import com.company.temp.service.BankVO;

@Controller
public class BankController {
	
	@Autowired BankAPI bankAPI;
	
	@RequestMapping("/auth")
	public String auth() throws Exception {
		String reqURL="https://testapi.openbanking.or.kr/oauth/2.0/authorize_account";
		String response_type="code";
		String client_id="28bfeff9-60d2-4fab-9600-79a2fe99c2b9";
		String redirect_uri = "http://localhost/bank/callback";
		String scope = "login inquiry transfer";
		String state = "01234567890123456789012345678932";
		String auth_type="0";

		StringBuilder qstr = new StringBuilder();
		qstr.append("response_type=" + response_type)
			.append("&client_id=" + client_id)
			.append("&redirect_uri="+redirect_uri)
			.append("&scope="+scope)
			.append("&state=" + state)
			.append("&auth_type=" + auth_type);
		return "redirect:" + reqURL + "?" + qstr.toString();
	}
	
	@RequestMapping("/callback")
	public String callback(@RequestParam Map<String, Object> map, HttpSession session) {
		System.out.println(map.get("code"));
		String code = (String)map.get("code");
		//access_token 받기
		String access_token = bankAPI.getAccessToken(code);
		System.out.println("access_token" + access_token);	
		session.setAttribute("access_token", access_token);
		
		//session
		//userinfo받기
		return "home";
	}
	
	String access_token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzcwNTMyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MjMxNDExNDksImp0aSI6ImE1ZDE5ZmZkLTQyYjMtNDA1Zi04Nzk1LWM3ZTE4OTIyNGY2OSJ9.bvnlo2PuS8qtrNlXd9Gbjs9qgVYz-CA9spwDrfVF47s";
	@RequestMapping("/getAccountList")
	public String getAccountList(HttpSession session, HttpServletRequest request, Model model) {
		//String access_token = (String)session.getAttribute("access_token");
		//String access_token = request.getParameter("access_token");
		String use_num ="1100770532";
		Map<String, Object> map = bankAPI.getAccountList(access_token, use_num);
		System.out.println("userinfo=" + map);
		model.addAttribute("list", map);
		return "bank/getAccountList";
	}
	@RequestMapping("/getBalance")
	public String getBalance(BankVO vo, Model model) {
		vo.setAccess_Token(access_token);
		Map<String, Object> map = bankAPI.getBalance(vo);
		System.out.println("작액조회=" + map);
		model.addAttribute("balance", map);
		return "bank/getBalance";
	}
	
	@ResponseBody //자동으로 json을 해줌 또는 상단에 @RestController를 하면 json구조로 변경시켜줌, 일반으로 넘기는건 @Controller
	@RequestMapping("/ajacGetBalance")
	public Map ajacGetBalance(BankVO vo, Model model) {
		vo.setAccess_Token(access_token);
		Map<String, Object> map = bankAPI.getBalance(vo);
		return map;
	}
	@RequestMapping("/getOrgAuthorize")
	public String getOrgAuthorize() {
		Map<String, Object> map = bankAPI.getOrgAccessToken();
		System.out.println("access_token : " + map.get("access_token"));
		return "home";
	}
	
}
