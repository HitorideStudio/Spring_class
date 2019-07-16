package frame.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aop/")

public class AopBean {
	
	@RequestMapping("test1.do")
	public String aopTest1() {
		System.out.println("aopTest1 mapping");
		return "/0716/test1";
	}
	@RequestMapping("test2.do")
	public String gitTest1() {
		System.out.println("gitTest1 mapping");
		return "/0716/test1";
	}

}
