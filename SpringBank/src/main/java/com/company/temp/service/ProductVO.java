package com.company.temp.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//get,set 만드는거
@Builder
//모델 객체를 생성할 때 Builder를 자동으로 추가해 주는 Annotation
@NoArgsConstructor
// 파라미터가 없는 생성자를 생성
@AllArgsConstructor
//클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성
public class ProductVO {
	private String product_id;
	private String product_name;
	private String product_price;
	private String product_info;
	private String product_date;
	private String company;
	private String manager_id;	
}
