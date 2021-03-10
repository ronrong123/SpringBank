package com.company.temp.service;

import java.util.List;

public interface ProductService {
	
	//전체조회
	public List<ProductVO> getSearchProduct(ProductVO vo);
	//등록
	public void insertProduct(ProductVO vo);
	
}
