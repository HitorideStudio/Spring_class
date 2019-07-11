
/*@RequestMapping(value= "pro.do") // params= {"id=java","!pw"} id=java, pw 없어야 여야 실행, 다중 벨류 가능 
 * value="pro.do","test.do" 다중벨류 가능 
 * @RequestParam(value="id" , required=false, defaultValue="hong") String ai)
 * 메소드 안에서 유효성 검사 가능 value id를 넣어주면 String ai 에 대입됨 다중 대입 가능
 * 필수 조건 걸수있음 required 
 * defaultValue null일 경우 기본값 설정 가능.
 * @ModelAttribute("vo") FormVO vo  pw 값을 그대로 전달 4.3버젼 기준,String 타입은 불가
 * */
package frame.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import frame.spring.vo.FormVO;

@Controller
@RequestMapping("/param/")
public class ParamBean {
   
   @RequestMapping("form.do")
   public String form() {
      return "/0710/form";
   }
   
   @RequestMapping(value= "pro.do") //params= {"id=java","!pw"} id=java, pw 없어야 여야 실행, 다중 벨류 가능 
   public String pro(
         @RequestParam(value="id" , required=false, defaultValue="hong") String ai,
         @ModelAttribute("vo") FormVO vo ) {
      System.out.println(ai);
       return "/0710/pro";
   }
}