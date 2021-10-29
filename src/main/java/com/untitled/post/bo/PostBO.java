package com.untitled.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.untitled.post.dao.PostDAO;
import com.untitled.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private PostBO postBO;
	
	public List<Post> getPostList(String search, String sort) {
		return postDAO.selectPostList(search, sort);
	}
}
