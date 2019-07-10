package frame.spring.bean;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import frame.spring.dao.FileDAO;
import frame.spring.vo.FileVO;

@Controller
@RequestMapping("/upload/")
public class uploadBean {
	@Autowired
	private FileDAO dao =null;
	
	@RequestMapping("uploadForm.do")
	public String uploadForm() {
		return "/0710/uploadForm";
	}

	@RequestMapping(value="uploadPro.do",method=RequestMethod.POST)
	public String uploadPro(FileVO vo,MultipartHttpServletRequest request,Model model) {
			
		try {
			request.setCharacterEncoding("utf-8");
		
			MultipartFile mf = request.getFile("save");
			String imgs = request.getRealPath("imgs");
			
			String orgName = mf.getOriginalFilename();
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			//DB연결 후 번호 받아온다. 시퀀스 증가 후 받아오기
			int num = dao.getNum();
			String newName = "images" + num +ext;
		
			File copyFile = new File( imgs+"//" +newName);
			
			mf.transferTo(copyFile);
			vo.setNewname(newName);
			vo.setOrgname(orgName);
			dao.fileInsert(vo); //DB profile테이블에 저장
			
			
			List list = dao.getList();
			
			model.addAttribute("list",list);
			model.addAttribute("img",newName);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "/0710/uploadPro";
	}

	@RequestMapping("imgList.do")
	public String imgList(FileVO vo, Model model) {
		try {
		List list = dao.getList();
		
		model.addAttribute("list",list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/0710/imgList";
		}
	@RequestMapping("deleteImg.do")
	public String deleteImg() {
		
		
		return "/0710/deleteImg";
		}
	
	}














