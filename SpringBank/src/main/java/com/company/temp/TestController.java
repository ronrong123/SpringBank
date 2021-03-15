package com.company.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.temp.service.TestVO;

@Controller //bean등록
public class TestController {
	
	//get방식 , 커맨드객체(VO)
	@RequestMapping("/getTest1")
	//@RequestMapping : get과 post 모두 처리
	public String getTest1(TestVO vo) {
		System.out.println(vo);
		return "test";
	}
	
	// 파라미터 = request.getParameter()
	@RequestMapping("/getTest2")
	//@RequestMapping : get과 post 모두 처리
	public String getTest2(@RequestParam String firstName, @RequestParam int salary) { //넘겨주는 파라미터값과 동일해야함
		System.out.println(firstName + " : " + salary);
		return "test";
	}
	

	// 배열 파라미터 request.getParameterValues()
	@RequestMapping("/getTest3")
	//@RequestMapping : get과 post 모두 처리
	public String getTest3(@RequestParam("hobby") String[] hobbies) { 
		//넘겨주는 파라미터값과 동일해야함 만약 변수명과 파라미터값이 일치하지않으면 RequestParam(파라미터명)을 적어야함
		//배열은 for문이나 list(Arrays.aslist())로 변경해서 출력함
		System.out.println(Arrays.asList(hobbies));		
		for(int i=0;i<hobbies.length;i++) {
			System.out.println(hobbies[i]);
			}
		return "test";
	}
	
	
	@RequestMapping("/getTest4")
	//@RequestMapping : get과 post 모두 처리
	public String getTest4(@RequestBody List<Map> vo) {  //json방식으로 넘길떄는 @RequestBody(요청파라미터)를 사용함
		//배열이면 List에 Vo를 담거나 Map을 담아넘김
		System.out.println(vo);		
		return "test";
	}
	
	@RequestMapping("/getTest5/{firstName}/{salary}")
	public String getTest5(@PathVariable String firstName, @PathVariable int salary, @ModelAttribute("tvo") TestVO vo,Model model) { //url뒤에 {}를 넣으려면 @pathVariable을 사용 
		vo.setFirstName(firstName);
		//이런식으로 값을 vo에 넘겨줄수도있음 위의 괄호안에 선언도 가능
		System.out.println(firstName +" : " + salary);	
		model.addAttribute("firstName", firstName);
		//자동으로 TestVO가 testVO(첫글자소문자)로 model에 들어감(괄호안에 객체) TestVO이름을 tvo로 불러오고싶을때 쓰는게 @ModelAttribute에서 지정해주면됨 (forward)
		//ModelAttribute가 없다면 testVO로 불러올수있음 
		return "test";
	}

	@RequestMapping("/getTest6/{firstName}/{salary}")
	public ModelAndView getTest6(@PathVariable String firstName, @PathVariable int salary, @ModelAttribute("tvo") TestVO vo) {  
		//ModelAndView : model과 view를 다 가지고있는 객체 (model을 따로 지정하지않아도됨)
		vo.setFirstName(firstName);
		System.out.println(firstName +" : " + salary);	
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("firstName", firstName);
//		mv.setViewName("test");
		//return타입이 String이 아니라 ModelAndView임
		//ModelAndView : 위에 세개와 같은 기능
		return new ModelAndView("test", "firstName", firstName); 
	}
	
	//json으로 결과넘기기(응답결과 json)
	@RequestMapping("/getTest7/{firstName}/{salary}")
	@ResponseBody //return값을 json으로 보내줌
	public TestVO getTest7(@PathVariable String firstName, @PathVariable String salary, TestVO vo) {  
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		return vo;
	}
	
	@RequestMapping("/getTest8")
	@ResponseBody //view페이지가 아닌 경우 사용
	public List<Map> getTest8(){
		List list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("name", "park");
		map.put("sal", "1000");
		list.add(map);	
		
		map = new HashMap<>();
		map.put("name", "kim");
		map.put("sal", "2000");
		list.add(map);	
		//list는 대괄호 map,vo는 중괄호
		return list;
	}
	
}
