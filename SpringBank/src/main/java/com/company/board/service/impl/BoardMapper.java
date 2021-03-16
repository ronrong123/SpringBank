package com.company.board.service.impl;

import com.company.board.service.FboardVO;

public interface BoardMapper {
	
	public int insertFboard(FboardVO vo);
	
	public FboardVO getBoard(FboardVO vo);
}
