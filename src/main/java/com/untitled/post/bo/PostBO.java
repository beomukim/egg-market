package com.untitled.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.untitled.common.FileManagerService;
import com.untitled.post.dao.PostDAO;
import com.untitled.post.model.Article;
import com.untitled.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<Post> getPostList(String sort) {
		return postDAO.selectPostList(sort);
	}

	public int createPost(String loginId, int userId, String title, String content, String price,
			MultipartFile file) {
		String imagePath = null;
		if (file != null) {
			try {
				imagePath = fileManagerService.saveFile(loginId, file); // 컴퓨터에 파일 업로드 후 URL path를 얻어낸다.
			} catch (IOException e) {
				logger.error("[파일업로드 에러] " + e.getMessage());
			}
		}
		
		return postDAO.insertPost(userId, title, content, price, imagePath);
	}
	
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	public int deletePost(int id) {
		// 파일이 있으면 파일도 삭제한다.
		Post post = getPost(id);
		if (post == null) {
			logger.warn("[update post] 수정할 메모가 존재하지 않습니다.");
			return 0;
		}
		
		if (post.getImagePath() != null) {
			try {
				fileManagerService.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("[파일 삭제중 에러] " + e.getMessage());
			}
		}
		
		return postDAO.deletePost(id);
	}
	
	
	public void setViewCount(int postId, int viewCount) {
		postDAO.setViewCount(postId, viewCount);
	}
	
	public List<Post> getPostListBySearch(String search) {
		return postDAO.selectPostListBySearch(search);
	}
}
