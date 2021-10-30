package com.untitled.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.untitled.post.bo.ArticleBO;
import com.untitled.post.bo.PostBO;
import com.untitled.post.model.Article;

@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private ArticleBO articleBO;
	
	@GetMapping("/post")
	public String postList(@RequestParam(value=	"search", required=false) String search,
			@RequestParam(value="sort", required=false) String sort,
			Model model) {
		model.addAttribute("viewName", "post/post");
		model.addAttribute("sort", sort);
		
		List<Article> ArticleList = articleBO.getArticleList(search, sort);
		model.addAttribute("articleList", ArticleList);
		
		return "template/layout";
	}
}
