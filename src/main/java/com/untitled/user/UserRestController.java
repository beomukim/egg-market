package com.untitled.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@RequestMapping("/idcheck")
	public Map<String, Object> idcheck(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("/signup") 
		public Map<String, Object> signup() {
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
}
