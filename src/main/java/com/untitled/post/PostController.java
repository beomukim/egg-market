package com.untitled.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String postList(@RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request, 
			Model model) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/login";
		}
		
		model.addAttribute("viewName", "post/post");
		model.addAttribute("sort", sort);

		List<Article> ArticleList = articleBO.getArticleList(sort);
		model.addAttribute("articleList", ArticleList);

		return "template/layout";
	}

	@GetMapping("post/write")
	public String postWrite(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/login";
		}

		model.addAttribute("viewName", "post/write");

		return "template/layout";
	}

	@GetMapping("post/{postId}")
	public String postDetail(@PathVariable("postId") int postId, Model model, HttpServletRequest request) {
		model.addAttribute("viewName", "post/content");

		Article article = articleBO.getArticle(postId);
		model.addAttribute("article", article);
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/login";
		}
		model.addAttribute("userId", userId);
		
		int viewCount = article.getPost().getViewCount();
		viewCount++;
		postBO.setViewCount(postId, viewCount);
		return "template/layout";

	}
	
	@GetMapping("/post/search/{search}")
	public String postListBySearch(@PathVariable("search") String search,
			HttpServletRequest request, 
			Model model) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/login";
		}
		
		model.addAttribute("viewName", "post/post");

		List<Article> articleList = articleBO.getArticleListBySearch(search);
		model.addAttribute("articleList", articleList);

		return "template/layout";
	}

}
