package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.post.bo.PostBO;
import com.memo.post.model.Post;

@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostBO postbo;
	
	@RequestMapping("/post_list_view")
	public String postListview(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			Model model, HttpServletRequest request) {
		// 글 목록들을 가져온다.
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		List<Post> postList = postbo.getList(userId, prevIdParam, nextIdParam);
		int prevId = 0;
		int nextId = 0;
		if (!CollectionUtils.isEmpty(postList)) {
			prevId = postList.get(0).getId();
			nextId = postList.get(postList.size() - 1).getId();
			
			// 이전이나 다음이 없는 경우 0으로 세팅한다. (jsp에서 0인지 검사)
			if (postbo.isLastPage(userId, nextId)) {
				nextId = 0;
			}
			
			if (postbo.isFirstPage(userId, prevId)) {
				prevId = 0;
			}
		}
		// 모델에 담는다.
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "/post/post_list");
		return "template/layout";
	}
	/**
	 * 글쓰기 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/post_create_view")
	public String postCreateView(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		model.addAttribute("viewName", "post/post_create");
		
		return "/template/layout";
	}
	
	@RequestMapping("/post_detail_view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			Model model) {
		model.addAttribute("viewName", "post/post_detail");
		
		model.addAttribute("post", postbo.getPost(postId));
		
		return "template/layout";
	}
}
