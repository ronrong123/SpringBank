package com.company.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.temp.service.impl.BoardVO;

@Controller
public class BoardController {
	
	//1. 파라미터를 vo에 담고 결과페이지로 넘어가 title, writer출력하는 핸들러 작성
	@RequestMapping("/insertBoard")
	public String insertBoard(BoardVO vo) {
		//return값이 view페이지면 String 아니면 넘겨주는 객체타입
		return "insertBoardResult";
	}
	
	//2. 파라미터를 vo에 담고 결과페이지로 가는게아니라 vo를 응답하는 페이지만들기
	@RequestMapping("/ajaxInsertBoard")
	@ResponseBody //넘어가는게 view페이지가 아니면 적음 String(view)이 아니면 무조건 적는다고 생각 
	public BoardVO ajaxInsertBoard(BoardVO vo) {
		//return값이 view페이지면 String 아니면 넘겨주는 객체타입
		return vo;
	}
	
	
}
