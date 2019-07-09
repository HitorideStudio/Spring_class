package test.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sun.security.jca.GetInstance;
import test.vo.memberDAO;
import test.vo.memberVO;

@Controller
public class testBean {
	@RequestMapping("loginForm.hot")
	public String loginForm() {
		return "/log/loginForm.jsp";
	}
	@RequestMapping("loginPro.hot")
	public ModelAndView loginPro(String id, String passwd) {
		
		memberDAO member =memberDAO.getInstance();
		ModelAndView mv = new ModelAndView();
		try {
		int x = member.userCheck(id, passwd);
		
		mv.addObject("id",x);
		mv.setViewName("/log/loginPro.jsp");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return mv;
	}
}
