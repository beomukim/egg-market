package com.untitled.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("viewName", "user/login");
        return "template/layout";
    }
	@RequestMapping("/join")
	public String join(Model model) {
		model.addAttribute("viewName", "user/join");
		return "template/layout";
	}
}
