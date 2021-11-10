package com.untitled.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.untitled.post.model.Article;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId != null) {
			return "redirect:/post";
		}
        model.addAttribute("viewName", "user/login");
        return "template/layout";
    }
	@RequestMapping("/join")
	public String join(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId != null) {
			return "redirect:/post";
		}
		model.addAttribute("viewName", "user/join");
		return "template/layout";
	}
	
	@RequestMapping("/logout")
	public String signOut(HttpServletRequest request) {
		// 로그아웃
		HttpSession session = request.getSession();
		session.removeAttribute("userLoginId");	
		session.removeAttribute("userId");	
		session.removeAttribute("userNickName");	
		return "redirect:/user/login";
	}
	
	
}
