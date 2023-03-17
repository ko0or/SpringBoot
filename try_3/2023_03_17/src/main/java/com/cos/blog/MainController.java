package com.cos.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/blog")
	@ResponseBody
	public String index() {
		return "둠칫- 두둠칫 !";
	}
	
	@GetMapping("/")
	public String root() {
		/*	root 접속시 /question/list 사이트로 이동시킨다 .. !	 */
		return "redirect:/question/list";
	}
	
	
	
}
