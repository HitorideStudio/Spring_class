package downloadBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class downloadview extends AbstractView{
	
	public downloadview() {
		setContentType("application/download;charset=UTF-8");
		
	}
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
	      File file = (File)model.get("downloadFile");
	      response.setCharacterEncoding(getContentType());
	      response.setContentLength((int)file.length());
	      
	      String fileName = java.net.URLEncoder.encode(file.getName(),"UTF-8");
	      response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");// 사용자 정보
	      response.setHeader("Content-Transfer-Encoding", "binary");
	      
	      OutputStream out = response.getOutputStream();
	      FileInputStream fis = null;
	      try{
	         fis = new FileInputStream(file);
	         FileCopyUtils.copy(fis, out);
	         
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         if(fis != null){try{fis.close();}catch(Exception e2){}}
	      }
	      out.flush();
	      
	   }
}
