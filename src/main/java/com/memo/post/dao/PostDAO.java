package com.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.model.Post;

@Repository
public interface PostDAO {
	public List<Post> selectPostList(Integer userId);
	public int insertPost(
			@Param("userId") Integer userId,
			@Param("subject") String subject,
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
}
