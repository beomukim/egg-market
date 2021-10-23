package com.untitled.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.untitled.user.model.User;

@Repository
public interface UserDAO {
	
	public int existLoginId(String loginId);
	public int insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("nickName") String nickName, 
			@Param("address") String address,
			@Param("email") String email,
			@Param("imagePath") String imagePath);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
}
