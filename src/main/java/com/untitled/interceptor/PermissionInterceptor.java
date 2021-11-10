package com.untitled.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 세션이 있는지 확인한다. => 있으면 로그인 된 상태
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		// URI 확인(url path를 가져온다.)
		String uri = request.getRequestURI();
		
		if (userId == null && uri.startsWith("/post")) {
			// 비로그인 && 접근을 시도한 uri path가 /post 이면 로그인 페이지로 리다이렉트
			response.sendRedirect("/user/login");
			return false;
		} else if (userId != null && uri.startsWith("/user")) {
			// 로그인 && 접근을 시도한 uri path가 /user 이면 게시판으로 리다이렉트
			response.sendRedirect("/post");
			return false;
		}
		
		return true;
	}
	
}
