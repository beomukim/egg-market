package com.untitled.user.bo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.untitled.common.FileManagerService;
import com.untitled.user.dao.UserDAO;
import com.untitled.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileManagerService fileManagerService;
	
	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	public int insertUser(String loginId, String password, String nickname, String address, String email, MultipartFile file) {
		String imagePath = null;
		if (file != null) {
			try {
				imagePath = fileManagerService.saveFile(loginId, file); // 컴퓨터에 파일 업로드 후 URL path를 얻어낸다.
			} catch (IOException e) {
				logger.error("[파일업로드 에러] " + e.getMessage());
			} 
		}
		
		if (imagePath == null) {
			imagePath = "http://localhost/images/basic.jpg";
		}
		return userDAO.insertUser(loginId, password, nickname, address, email, imagePath);
	}
	
	public User getUser(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
}
