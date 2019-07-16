package frame.aop.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class LoginAdvice {
	@Around("execution(public String login*(..))")
	public Object around(ProceedingJoinPoint pj)throws Throwable{
		/*Object[] arg = pj.getArgs();
		HttpServletRequest request = null;
		for(Object o:arg) {
			if(o instanceof HttpServletRequest) {
			request = (HttpServletRequest)o;
			System.out.println(request);
			}
		}*/
		
		
		
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		Object pro = "/member/main";
		HttpServletRequest request =sra.getRequest();
		HttpSession session = request.getSession();
		if(session != null) {
			String memId = (String)session.getAttribute("memId");
			if(memId != null && !memId.equals("")) {
				pro = pj.proceed();
				
			}System.out.println(pj);
		}
		
		return pro;
		
		
	}
}
