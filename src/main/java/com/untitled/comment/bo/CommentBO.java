package com.untitled.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untitled.comment.dao.CommentDAO;
import com.untitled.comment.model.Comment;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	
	public List<Comment> getCommentList(int postId) {
		return commentDAO.selectCommentList(postId);
	}
}
