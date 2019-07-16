package frame.aop.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Advice {
	@Before("execution(public String git*(..))")
	public void before() {
		System.out.println("before");
	}
}
