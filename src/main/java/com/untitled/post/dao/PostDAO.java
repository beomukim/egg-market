package com.untitled.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.untitled.post.model.Post;

public interface PostDAO {
	public List<Post> selectPostList(@Param("search") String search, @Param("sort") String sort);
}
