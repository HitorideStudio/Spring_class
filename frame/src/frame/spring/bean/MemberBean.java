package frame.spring.bean;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch11.logon.LogonDBBean;
import ch11.logon.LogonDataBean;



@Controller
@RequestMapping("/member/")
public class MemberBean {
	
	@Autowired
	private LogonDBBean manager = null;
	
	@Autowired
	private LogonDataBean member =null;
	
	@RequestMapping("main.do")
	public String main() {
		return "/member/main";
	}
	@RequestMapping("loginForm.do")
	public String loginForm() {
		return "member/loginForm";
	}
	@RequestMapping("loginPro.do")
	public String loginPro(String id, String passwd,
			HttpSession session, Model model) {
		try {
		int check =manager.userCheck(id, passwd);
		if(check==1) {
			session.setAttribute("memId",id);
		}
		model.addAttribute("check",check);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "/member/loginPro";
	}
	@RequestMapping("logout.do")
	public String loginout(HttpSession session) {
		session.invalidate();
		return "/member/logout";
	}
	@RequestMapping("inputForm")
	public String inputForm() {
		return "/member/inputForm";
	}
	@RequestMapping("inputPro.do")
	public String inputPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("euc-kr");
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setJumin1(request.getParameter("jumin1"));
		member.setJumin2(request.getParameter("jumin2"));
		member.setEmail(request.getParameter("email"));
		member.setBlog(request.getParameter("blog"));
		member.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		
			manager.insertMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/member/inputPro";
	}
	@RequestMapping("confirmId.do")
	public String confirmId(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			int check = manager.confirmId(id);
			request.setAttribute("check", check);
			request.setAttribute("id", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/member/confirmId";
	}
	@RequestMapping("modify.do")
	public String modify() {
		return "/member/modify";
	}
	@RequestMapping("modifyForm.do")
	public String modifyForm(HttpSession session, Model model,HttpServletRequest request) {
		String id = (String)session.getAttribute("memId");
		try {
			
			member = manager.getMember(id);
			model.addAttribute("c", member);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "/member/modifyForm";
	}
	@RequestMapping("modifyPro.do")
	public String modifyPro(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
		String id = (String)session.getAttribute("memId");
	
	   
		try {
			request.setCharacterEncoding("euc-kr");
		member.setId(id);
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setBlog(request.getParameter("blog"));
		
			manager.updateMember(member);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/member/modifyPro";
	}
	@RequestMapping("deleteForm.do")
	public String deleteForm() {
		return "/member/deleteForm";
	}
	@RequestMapping("deletePro.do")
	public String deletePro(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String id = (String)session.getAttribute("memId");
		String passwd = request.getParameter("passwd");
		try {
			int check = manager.deleteMember(id, passwd);
			if(check ==1) {
				session.invalidate();
			}
			model.addAttribute("check", check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/deletePro";
	}
	
	
	
	
	
	
	
	
	
}