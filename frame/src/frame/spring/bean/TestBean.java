package frame.spring.bean;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/")


public class TestBean {
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("main.do")
	public String main() {
		System.out.println(sql);
		return "/test/main";
	}
	

}
