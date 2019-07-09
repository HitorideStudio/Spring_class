package frame.spring.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import frame.spring.vo.Bar;
import frame.spring.vo.Foo;
import frame.spring.vo.FormVO;

@Controller
public class HelloBean {
	@Autowired
	private Foo f =null;
	@Autowired
	private Bar b =null;
	
	@RequestMapping("hello.do")
	public String hello() {
		
		System.out.println(f.getDay());
		System.out.println(f.getId());
		System.out.println(f.getPw());
		
		return "/0708/hello.jsp";
	}
	
	
	@RequestMapping("form.do")
	public String form() {
		System.out.println("form bean");
		return "/0708/form.jsp";
	}
	@RequestMapping("pro.do")
	public ModelAndView pro(FormVO vo){
		//System.out.println("===="+vo.getId());
		//System.out.println("===="+vo.getPw());
		//System.out.println("===="+request);
		//System.out.println("===="+response);
		//System.out.println("===="+session);
		//return "/0708/pro.jsp";
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", vo.getId());
		mv.setViewName("/0708/pro.jsp");
		return mv;
	}

	

}
