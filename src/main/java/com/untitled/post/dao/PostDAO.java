package com.untitled.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.untitled.post.model.Post;

@Repository
public interface PostDAO {
	public List<Post> selectPostList(String sort);

	public int insertPost(
			@Param("userId") int userId,
			@Param("title") String title, 
			@Param("content") String content, 
			@Param("price") String price, 
			@Param("imagePath") String imagePath);
	
	public Post selectPost(int postId);
	
	public void setViewCount(
			@Param("postId") int postId,
			@Param("viewCount") int viewPoint);
	
	public List<Post> selectPostListBySearch(String search);
}
