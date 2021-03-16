package com.company.board.service;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FboardVO {
	private String seq;
	private String title;
	private String writer;
	private String content;
	private String filename;
	private MultipartFile[] uploadFile;
	//input type=file일때 MultipartFile로 받아야함
}
