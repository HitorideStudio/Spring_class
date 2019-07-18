package frame.spring.bean;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import frame.spring.vo.TestVO;

@Controller
@RequestMapping("/active/")
public class ActiveBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("active.do")
	public String active() {
		TestVO vo = new TestVO();
		vo.setId("test1");
		int count = (Integer)sql.selectOne("active.count",vo);
		System.out.println(count);
		return "/0718/active";
		
	}
	@RequestMapping("active2.do")
	public String active2() {
		ArrayList list = new ArrayList();
		list.add("java");
		list.add("mfboy19");
		list.add("javo");
		list.add("test2");
		
		int count = (Integer)sql.selectOne("active.selectIn",list);
		System.out.println(count);
		return "/0718/active";
		
	}

}
