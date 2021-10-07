package com.memo.post.bo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final int POST_MAX_SIZE = 3;
	
	@Autowired
	private PostDAO postdao;

	@Autowired
	private FileManagerService filemanagerservice;

	public List<Post> getList(Integer userId, Integer prevId, Integer nextId) {
		String direction = null; // null || next || prev
		Integer standardId = null;
		if (prevId != null) {
			direction = "prev";
			standardId = prevId;
			
			List<Post> postList = postdao.selectPostList(userId, direction, standardId, POST_MAX_SIZE);
			Collections.reverse(postList);
			return postList;
		}
		if (nextId != null) {
			direction = "next";
			standardId = nextId;
			
		}
		
		return postdao.selectPostList(userId, direction, standardId, POST_MAX_SIZE);
	}
	
	// 다음 기준으로 마지막 페이지인가?
	public boolean isLastPage(int userId, int nextId) {
		return nextId == postdao.selectPostIdByUserIdAndSort(userId, "ASC");
	}
	
	public boolean isFirstPage(int userId, int prevId) {
		return prevId == postdao.selectPostIdByUserIdAndSort(userId, "DESC");
	}

	public int createPost(String loginId, int userId, String subject, String content, MultipartFile file) {

		String imagePath = null;
		if (file != null) {
			try {
				imagePath = filemanagerservice.saveFile(loginId, file); // 컴퓨터에 파일 업로드 후 URL path를 얻어낸다.
			} catch (IOException e) {
				imagePath = null;
			}
		}
		return postdao.insertPost(userId, subject, content, imagePath);
	}

	public Post getPost(int postId) {
		return postdao.selectPost(postId);
	}
	
	public int updatePost(int postId, String loginId, String subject, String content, MultipartFile file) {
		Post post = getPost(postId);
		
		
		// file이 있으면 수정하고 없으면 수정하지 않는다.
		String imagePath = null;
		if (file != null) {
			try {
				imagePath = filemanagerservice.saveFile(loginId, file); // 컴퓨터에 파일 업로드 후 URL path를 얻어낸다.
				
				if (imagePath != null && post.getImagePath() != null) {
					// 기존에 이미지가 있었으면 파일을 제거한다. -- 업로드가 실패할 수 있으므로 업로드가 성공 후 제거
					filemanagerservice.deleteFile(post.getImagePath());
				}
			} catch (IOException e) {
				logger.error("[파일 수정중 에러] " + e.getMessage());
			}
		}
		
		return postdao.updatePost(postId, subject, content, imagePath);
	}
	
	public int deletePost(int id) {
		// 파일이 있으면 파일도 삭제한다.
		Post post = getPost(id);
		
		
		if (post.getImagePath() != null) {
			try {
				filemanagerservice.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("[파일 삭제중 에러] " + e.getMessage());
			}
		}
		
		return postdao.deletePost(id);
	}
	
}
