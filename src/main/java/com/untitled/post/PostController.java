package com.untitled.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
	
	@RequestMapping("/post")
	public String postList(@RequestParam(value=	"search", required=false) String search,
			@RequestParam(value="sort", required=false) String sort,
			Model model) {
		model.addAttribute("viewName", "post/post");
		model.addAttribute("sort", sort);
		
		return "template/layout";
	}
}
