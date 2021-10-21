package com.untitled.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
	
	@RequestMapping("/post")
	public String postList(Model model) {
		model.addAttribute("viewName", "post/post");
		return "template/layout";
	}
}
