package frame.spring.bean;

import java.io.File;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import frame.spring.vo.TestVO;

@Controller
@RequestMapping("/test/")


public class TestBean {
	
	@Autowired
	private SqlSessionTemplate sql = null;
	
	@RequestMapping("form.do")
	public String main() {
		System.out.println(sql);
		return "/test/form";
	}
	@RequestMapping("formPro.do")
	public String formPro(MultipartHttpServletRequest request, String id, String pw, int age, String name) {
		System.out.println(id);
		MultipartFile mf = request.getFile("img");
		String path = request.getRealPath("img");
		String org = mf.getOriginalFilename();
		String ext = org.substring(org.lastIndexOf("."));
		String img = id+ext;
		File f = new File(path+"//"+img);
		try {
			mf.transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		TestVO vo= new TestVO();
		vo.setAge(age);
		vo.setId(id);
		vo.setImg(img);
		vo.setPw(pw);
		vo.setName(name);
		//mybatis insert½ÇÇà
		sql.insert("test.insertTest",vo);
		
		
		
		return "/test/formPro";
	}

}
