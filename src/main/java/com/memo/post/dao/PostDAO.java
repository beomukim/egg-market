package com.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.model.Post;

@Repository
public interface PostDAO {
	public List<Post> selectPostList(@Param("userId") int userId, @Param("direction") String direction,
			@Param("standardId") Integer standardId, @Param("limit") int limit);
	
	public int selectPostIdByUserIdAndSort(
			@Param("userId") int userId, 
			@Param("sort") String sort);

	public int insertPost(@Param("userId") Integer userId, @Param("subject") String subject,
			@Param("content") String content, @Param("imagePath") String imagePath);

	public Post selectPost(int postId);

	public int updatePost(@Param("id") int id, @Param("subject") String subject, @Param("content") String content,
			@Param("imagePath") String imagePath);

	public int deletePost(int id);
}
