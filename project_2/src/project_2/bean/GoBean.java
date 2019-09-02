package project_2.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/finder/")
public class GoBean {
	
	@RequestMapping("main.go")
	public String main() {
		return "/finder/main";
	}
	@RequestMapping("map.go")
	public String map() {
		return "/finder/map";
	}
	@RequestMapping("cluster.go")
	public String cluster() {
		return "/finder/cluster";
	}

}
