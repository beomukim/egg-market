package com.untitled.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.untitled.comment.model.Comment;

@Repository
public interface CommentDAO {
	public List<Comment> selectCommentList(int postId);
	
	public int insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("userNickName") String userNickName, 
			@Param("content") String content);
	
	public void deleteComment(int id);
}
