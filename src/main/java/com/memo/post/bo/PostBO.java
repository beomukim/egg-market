package com.memo.post.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.dao.PostDAO;
import com.memo.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postdao;
	private FileManagerService filemanagerservice;
	
	public List<Post> getList(Integer userId) {
		return postdao.selectPostList(userId);
	}
	
	public int createPost(String loginId, int userId, String subject, String content, MultipartFile file) {
		// content 줄바꿈 적용
		content = content.replace("\n", "<br>");
		
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
}
