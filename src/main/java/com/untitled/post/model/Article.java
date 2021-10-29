package com.untitled.post.model;

import com.untitled.user.model.User;

public class Article {
	private User user;
	private Post post;
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
