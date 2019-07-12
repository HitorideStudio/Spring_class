package frame.spring.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.BoardDBBean;
import board.BoardDataBean;

@Controller
@RequestMapping("/board/")
public class BoardBean {
	@Autowired
	private BoardDataBean article = null;
	@Autowired
	private BoardDBBean dao = null;
	
	@RequestMapping("content.do")
	public String content(HttpServletRequest request, HttpServletResponse response) {
		   int num = Integer.parseInt(request.getParameter("num"));
		   String pageNum = request.getParameter("pageNum");

		   SimpleDateFormat sdf = 
		        new SimpleDateFormat("yyyy-MM-dd HH:mm");

		   try{
		    
		      article =  dao.getArticle(num);
		  
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
	@RequestMapping("deleteForm.do")
	public String deleteForm(HttpServletRequest request, HttpServletResponse response) {
		  int num = Integer.parseInt(request.getParameter("num"));
		  String pageNum = request.getParameter("pageNum");
		  
		  request.setAttribute("num", num);
		  request.setAttribute("pageNum", pageNum);
		 return "/board/deleteForm";
	}
	@RequestMapping("deletePro.do")
	public String deletePro(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("passwd");
		try {
			int check = dao.deleteArticle(num, passwd);
			request.setAttribute("check", check);
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		 return "/board/deletePro";
	}
	@RequestMapping("list.do")
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
   
             count = dao.getArticleCount();
             if (count > 0) {
                 articleList = dao.getArticles(startRow, endRow);
             }
             int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
             if (endPage > pageCount) {endPage = pageCount;}
             number=count-(currentPage-1)*pageSize;
             
            System.out.println(count);
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
            // TODO: handle exception
         }
		
		
		 return "/board/list";
	}
	@RequestMapping("updateForm.do")
	public String updateForm(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		try {
			article =dao.updateGetArticle(num);
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("article", article);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		 return "/board/updateForm";
	}
	@RequestMapping("updatePro.do")
	public String updatePro(HttpServletRequest request,HttpServletResponse response) {
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		
		try {
			request.setCharacterEncoding("UTF-8");
			int check = dao.updateArticle(article);
			article.setWriter(request.getParameter("writer"));
			article.setEmail(request.getParameter("email"));
			article.setSubject(request.getParameter("subject"));
			article.setPasswd(request.getParameter("passwd"));
			article.setContent(request.getParameter("content"));
			article.setNum(num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", check);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return "/board/updatePro";
	}
	@RequestMapping("writeForm.do")
	public String writeForm(HttpServletRequest request, HttpServletResponse response) {
		int num=0,ref=1,re_step=0,re_level=0;
		  try{  
			
		    if(request.getParameter("num")!=null){
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		    }
		    request.setCharacterEncoding("UTF-8");
		    request.setAttribute("num", num);
		    request.setAttribute("ref", ref);
		    request.setAttribute("re_step", re_step);
		    request.setAttribute("re_level", re_level);
		    
		  	}catch(Exception e){
		    	
		    }
		
		 return "/board/writeForm";
	}
	@RequestMapping("writePro.do")
	public String writePro( HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		int re_level= Integer.parseInt(request.getParameter("re_level"));
		
	    try {
	    	request.setCharacterEncoding("UTF-8");
	    
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
	    
	    
	    
	    dao.insertArticle(article);
	   
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		 return "/board/writePro";
	}

}
