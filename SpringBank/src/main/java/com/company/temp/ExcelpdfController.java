package com.company.temp;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.temp.service.impl.EmpMapper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ExcelpdfController {

	@Autowired
	EmpMapper empMapper;
	@Autowired
	DataSource dataSource;

	@RequestMapping("/getChartData")
	@ResponseBody
	public List<Map<String, Object>> getChartData() {		
		return empMapper.getChartData();
	}

	@RequestMapping("/getEmpExcel")
	public String getEmpExcel(Model model) {
		List<Map<String, Object>> list = empMapper.getEmpList();
		System.out.println(list.get(0));
		model.addAttribute("filename", "empList");
		model.addAttribute("headers", new String[] { "firstName", "salary" });
		model.addAttribute("datas", list);
		// jsp가 아니라 common으로 가도록 servlet-context에서 순서를 지정해줌
		return "commonExcelView";
		// employees파일을 엑셀파일로 내려받음?
	}

	@RequestMapping("/pdf/empList")
	public String getPdfEmpList(Model model) {
		model.addAttribute("filename", "/reports/empList.jasper");
		return "pdfView";
	}

	@RequestMapping("/pdf/empList2")
	public String getPdfEmpList2(Model model, @RequestParam String dept) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("P_DEPARTMENT_ID", dept);

		model.addAttribute("param", map);
		model.addAttribute("filename", "/reports/empList2.jasper");

		return "pdfView";
	}

	@RequestMapping("/pdf/empList3")
	public void getPdfEmpList3(HttpServletResponse response) throws Exception {
		Connection conn = dataSource.getConnection();
		// 소스파일 컴파일하여 저장 : compileReportToFile
		String jrxmlFile = getClass().getResource("/reports/Blank_A4.jrxml").getFile();
		String jasperFile = JasperCompileManager.compileReportToFile(jrxmlFile);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, null, conn);
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
}
