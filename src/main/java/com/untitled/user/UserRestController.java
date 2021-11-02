package com.untitled.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.untitled.common.EncryptUtils;
import com.untitled.user.bo.UserBO;
import com.untitled.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/idcheck")
	public Map<String, Object> idcheck(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		int existRowCount = userBO.existLoginId(loginId);
		if (existRowCount > 0) { // 이미 id가 존재하면 true
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		return result;
	}
	
	@RequestMapping("/signup") 
		public Map<String, Object> signup(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				@RequestParam("nickName") String nickName,
				@RequestParam("address") String address,
				@RequestParam(value="email", required = false) String email,
				@RequestParam(value = "file", required = false) MultipartFile file) {


		String encryptPassword = EncryptUtils.md5(password);
		int row = userBO.insertUser(loginId, encryptPassword, nickName, address, email, file);
		
		Map<String, Object> result = new HashMap<>();
		if (row == 1) {
			result.put("result", "success");
		} else {
			result.put("error", "입력 실패");
		}
		return result;
	}
	
	@RequestMapping("/signin")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		String encryptPassword = EncryptUtils.md5(password);
		User user = userBO.getUser(loginId, encryptPassword);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("result", "success");
			// 로그인 처리 - 세션에 저장(로그인 상태를 유지한다)
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId", user.getLoginId());	
			session.setAttribute("userId", user.getId());	
		} else {
			result.put("error", "입력 실패");
		}
		return result;
	}
	
}
