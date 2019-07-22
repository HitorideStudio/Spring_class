package spring_board.bean;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.BoardVO;

@Controller
@RequestMapping("/board/")
public class BoardBean {
	@Autowired
	private SqlSessionTemplate sql = null;
	@Autowired
	private BoardVO article = null;
	private ResultSet rs = null;
	
	  @RequestMapping("content.git") 
	  public String content(HttpServletRequest
	  request, HttpServletResponse response,int num, String pageNum) { 
		   
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  try{
		  article = sql.selectOne("board.getArticle",num);
		  sql.update("board.readCount",num);
		  int ref=article.getRef(); 
		  int re_step=article.getRe_step(); 
		  int re_level=article.getRe_level();
		  request.setAttribute("num", num); 
		  request.setAttribute("pageNum", pageNum);
		  request.setAttribute("sdf", sdf); 
		  request.setAttribute("ref", ref);
		  request.setAttribute("re_step", re_step); 
		  request.setAttribute("re_level", re_level); 
		  request.setAttribute("article", article);
	  
	  	}catch(Exception e) { 
	  		e.printStackTrace(); 
	  		} 
	  	return "/board/content"; 
	  }
	  
	  @RequestMapping("download.git")
	  public ModelAndView download(String newname, HttpServletRequest request) {
		  try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		  System.out.println("다운로드깃");
		  String path = request.getRealPath("imgs");
		  File f = new File(path+"//"+newname);
		  ModelAndView mv = new ModelAndView("downloadView","downloadFile", f);
		  //controller의 Id viewBean의 아들, downloadView의 get 파일로 매개변수, File의 f
		  return mv;
	  }
	  
	@RequestMapping("list.git")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		int pageSize = 10;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
              pageNum = "1";
          }
       System.out.println(pageNum);
           int currentPage = Integer.parseInt(pageNum);
           int startRow = (currentPage - 1) * pageSize + 1;
           int endRow = currentPage * pageSize;
           int count = 0;
           int number=0;
           int wid=0; 
           int startPage = (int)(currentPage/10)*10+1;
           int pageBlock=10;
           int endPage = startPage + pageBlock-1;
    
           try {
          List articleList = null;   
          ArrayList list = new ArrayList();
          list.add(startRow); 
          list.add(endRow);
          
             count = (Integer)sql.selectOne("board.getArticleCount");
             System.out.println(count);
             if (count > 0) {
                 articleList = sql.selectList("board.getArticles",list);
                 
             }
             int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
             if (endPage > pageCount) {endPage = pageCount;}
             number=count-(currentPage-1)*pageSize;
             
             request.setAttribute("pageSize", pageSize);
             request.setAttribute("count",count);
             request.setAttribute("sdf",sdf);
             request.setAttribute("wid",wid);
             request.setAttribute("articleList", articleList);
             request.setAttribute("number", number);
             request.setAttribute("currentPage", currentPage);
             request.setAttribute("pageCount",pageCount );
             request.setAttribute("startPage",startPage );
             request.setAttribute("pageBlock", pageBlock);
             request.setAttribute("endPage", endPage);
          }catch (Exception e) {
     
        	  e.printStackTrace();
         }
		 return "/board/list";
	}

	@RequestMapping("writeForm.git")
	public String writeForm(HttpServletRequest request, HttpServletResponse response) {
		int num=0,ref=1,re_step=0,re_level=0;
		  try{  
			
		    if(request.getParameter("num")!=null){
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		    }
		  
		    request.setAttribute("num", num);
		    request.setAttribute("ref", ref);
		    request.setAttribute("re_step", re_step);
		    request.setAttribute("re_level", re_level);
		    
		  	}catch(Exception e){
		    	
		    }
		
		 return "/board/writeForm";
	}
	@RequestMapping(value="writePro.git", method=RequestMethod.POST)
	public String writePro( MultipartHttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level= Integer.parseInt(request.getParameter("re_level"));
		//int number = sql.selectOne("board.getNumber");
		int number = 0;
		
		try {
			
			request.setCharacterEncoding("UTF-8");
			
		if(sql.selectOne("board.getNumber")==null) {
			number=1;
		}else {
			number = sql.selectOne("board.getNumber");
		}
		
		
	    request.setCharacterEncoding("UTF-8");
	    MultipartFile mf = request.getFile("save");
	    String orgname = mf.getOriginalFilename();
	    System.out.println("orgname==="+orgname);
	 
		
	    if(mf.getOriginalFilename() == "") {
			article.setNewname("");
			article.setOrgname("");
			
		}else{
			String imgs = request.getRealPath("imgs");
			//DB연결 후 번호 받아온다. 시퀀스 증가 후 받아오기
			sql.insert("board.getNumInsert");
		    int numi = sql.selectOne("board.getNum");
		    String ext = orgname.substring(orgname.lastIndexOf('.'));
		    String newname = "images" + numi +ext;
			File copyFile = new File( imgs+"//" +newname);
			mf.transferTo(copyFile);
			article.setNewname(newname);
			article.setOrgname(orgname);
			System.out.println("newname==="+newname);
		}
	    
		if(num !=0) {
			sql.update("insertStep",num);
			
			re_step=re_step+1;
			re_level=re_level+1;
		}else{ 
			ref=number;
			re_step=0;
			re_level=0;
		}
			
	    article.setNum(num);
	    article.setWriter(request.getParameter("writer"));
	    article.setSubject(request.getParameter("subject"));
	    article.setEmail(request.getParameter("email"));
	    article.setContent(request.getParameter("content"));
	    article.setPasswd(request.getParameter("passwd"));
	    article.setRef(ref);
	    article.setRe_level(re_level);
	    article.setRe_step(re_step);
	    article.setReg_date(new Timestamp(System.currentTimeMillis()));
	    article.setIp(request.getRemoteAddr());
	    
	    sql.insert("board.insertArticle",article);
	   
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		 return "/board/writePro";
}

	@RequestMapping("updateForm.git")
	public String updateForm(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		try {
			article =sql.selectOne("board.getArticle",num);
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);	
		} catch (Exception e) {	
			e.printStackTrace();
		}	
		 return "/board/updateForm";
	}
	
	@RequestMapping(value="updatePro.git",method=RequestMethod.POST)
	public String updatePro(MultipartHttpServletRequest request,HttpServletResponse response) {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		article = sql.selectOne("board.getArticle",num);
		
		try {
			request.setCharacterEncoding("UTF-8");
			 MultipartFile mf = request.getFile("save"); 
			 String orgname = mf.getOriginalFilename();
			 String formpw = request.getParameter("passwd");
				//DB연결 후 번호 받아온다. 시퀀스 증가 후 받아오기
			 int x= -1;
			 String dbpw = sql.selectOne("board.check",num);
				if(dbpw.equals(formpw)) {
					
					if(mf.getOriginalFilename() == "") {
					
					} else {
						String imgs = request.getRealPath("imgs");
						File f = new File(imgs+"//"+article.getNewname());
						f.delete();
						int numi = sql.selectOne("board.getNum");
						String ext = orgname.substring(orgname.lastIndexOf('.'));
					    String newname = "images" + numi +ext;
						File copyFile = new File( imgs+"//" +newname);
						mf.transferTo(copyFile);
						article.setNewname(newname);
						article.setOrgname(orgname);
				}
					article.setWriter(request.getParameter("writer"));
					article.setEmail(request.getParameter("email"));
					article.setSubject(request.getParameter("subject"));
					article.setPasswd(request.getParameter("passwd"));
					article.setContent(request.getParameter("content"));
					article.setNum(num);

					sql.update("board.updateArticle",article);
					x= 1;
			} else {
				x= 0 ;
			}
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return "/board/updatePro";
	}

	  @RequestMapping("deleteForm.git") 
	  public String deleteForm(HttpServletRequest
	  request, HttpServletResponse response)throws Exception {
		  
	  int num =Integer.parseInt(request.getParameter("num")); 
	  String pageNum =request.getParameter("pageNum"); 
	  article =sql.selectOne("board.getArticle",num);
	  request.setAttribute("num", num); 
	  request.setAttribute("pageNum", pageNum);
	  request.setAttribute("newname", article.getNewname()); 
		  
	  return"/board/deleteForm"; 
	  
	  }
	  
	  @RequestMapping("deletePro.git")
	  public String deletePro(HttpServletRequest request) {
	  
		  String newname = request.getParameter("newname");
		  String imgs = request.getRealPath("imgs");
		  System.out.println(newname);
		  int num = Integer.parseInt(request.getParameter("num"));
		  String pageNum = request.getParameter("pageNum");
		  String passwd = request.getParameter("passwd");
		  int x= -1;
		  try {
			  request.setCharacterEncoding("UTF-8");
			  File f = new File( imgs+"//" +newname);
			  f.delete();
			  String dbpw = sql.selectOne("board.check",num);
			  if(dbpw.equals(passwd)) {
				  sql.update("board.deleteArticle",num);
				  x=1;
			  }else {
				  x=0;
			  }
			  request.setAttribute("check", x);
			  request.setAttribute("pageNum",pageNum);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	 
	  		return "/board/deletePro";
	  	}	  
}	