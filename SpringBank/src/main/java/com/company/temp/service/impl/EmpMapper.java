package com.company.temp.service.impl;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
	public List<Map<String, Object>> getEmpList();
	//일별판매합계
	public List<Map<String, Object>> getChartData();
}
