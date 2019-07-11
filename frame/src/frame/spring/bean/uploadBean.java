package frame.spring.bean;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import frame.spring.dao.FileDAO;
import frame.spring.vo.FileVO;

@Controller
@RequestMapping("/upload/")
public class uploadBean {
	@Autowired
	private FileDAO dao =null;
	
	@Autowired
	private FileVO vo = null;
	
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
	@RequestMapping("download.do")
	   public ModelAndView download(String newname , HttpServletRequest request) {
	      String path = request.getRealPath("imgs");
	      File f = new File(path+"//"+newname);
	      
	      ModelAndView mv = new ModelAndView("downloadView","downloadFile",f);
	      // controller 의 Id viewBean의 이름 , downloadView의 get 파일로 매게변수, File 의 f
	      
	      return mv;
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
	@RequestMapping("delete.do")
	public String deleteImg(String newname, HttpServletRequest request, HttpServletResponse response) {
		String imgs = request.getRealPath("imgs");
		try {
			File f = new File( imgs+"//" +newname);
			f.delete();
			int check = dao.deleteImg(newname);
			request.setAttribute("check", check);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return "/0710/delete";
		}
	
	}














