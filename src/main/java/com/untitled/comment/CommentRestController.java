package com.untitled.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.untitled.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentBO commentBO;

	/**
	 * 댓글 쓰기
	 * 
	 * @param postId
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/write")
	public Map<String, Object> create(@RequestParam("postId") int postId, @RequestParam("content") String content,
			HttpServletRequest request) {

		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String userNickName = (String) session.getAttribute("userNickName");
		
		int row = commentBO.createComment(postId, userId, userNickName, content);
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "error");
			logger.error("[댓글쓰기] 댓글쓰기 중 실패하였습니다.");
		}
		return result;
	}
}
