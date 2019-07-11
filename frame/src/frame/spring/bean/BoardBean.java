package frame.spring.bean;

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
	public String deleteForm() {
		 return "/board/deleteForm";
	}
	@RequestMapping("deletePro.do")
	public String deletePro() {
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
	public String updateForm() {
		 return "/board/updateForm";
	}
	@RequestMapping("updatePro.do")
	public String updatePro() {
		 return "/board/updatePro";
	}
	@RequestMapping("writeForm.do")
	public String writeForm() {
		 return "/board/writeForm";
	}
	@RequestMapping("writePro.do")
	public String writePro() {
		 return "/board/writePro";
	}

}
