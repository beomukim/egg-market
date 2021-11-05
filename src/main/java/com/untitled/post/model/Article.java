package com.untitled.post.model;

import java.util.List;

import com.untitled.comment.model.Comment;
import com.untitled.user.model.User;

public class Article {
	private User user;
	private Post post;
	private List<Comment> commentList;
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
}
