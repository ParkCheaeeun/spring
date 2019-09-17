package kr.or.ddit.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("mvc")
@Controller
public class SpringMvcController {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringMvcController.class);
	
	/**
	* Method : view
	* 작성자 : PC-09
	* 변경이력 :
	* @return
	* Method 설명 : mvc 어노테이션 테스트 view
	*/
	@RequestMapping("view")
	public String view() {
		return "mvc/view";
	}
	
	/**
	* Method : requestParam
	* 작성자 : PC-09
	* 변경이력 :
	* @param userId
	* @param page
	* @return
	* Method 설명 : @requestParam 어노테이션 테스트
	*/
	@RequestMapping("requestParam")
	public String requestParam(String userId, @RequestParam(defaultValue = "10") int page) {
		
		logger.debug("userId : {} " , userId);
		logger.debug("page : {} " , page);
		return "mvc/view";
	}
	
	// mvc/
	@RequestMapping("/path/{subpath}")
	public String requestPath(@PathVariable(name = "subpath") String subpath) {
		logger.debug("subpath : {} " , subpath);
		return "mvc/view";
	}
}
