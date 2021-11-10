package com.untitled.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.untitled.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/write")
	public Map<String, Object> postWrite(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("price") String price,
			@RequestPart("file") MultipartFile file,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("userLoginId");
		int userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		int row = postBO.createPost(loginId, userId, title, content, price, file);
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "error");
		}
		
		return result;
	}
	
	@RequestMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId) {
		
		Map<String, Object> result = new HashMap<>();
		int row = postBO.deletePost(postId);
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "error");
		}
		return result;
	}
	
}
