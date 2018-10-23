/**
 * 
 */
package com.zhihao.seckill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhihao.seckill.pojo.User;
import com.zhihao.seckill.service.UserService;

/**
 * login handler
 * @author zzh
 * 2018年9月30日
 */
@Controller
@RequestMapping("")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root() {
		return "redirect:/login";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return "welcome";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "fore/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(User user, HttpSession session, Model model) {
		User authUser = userService.get(user);
		if(authUser!=null) {
			session.setAttribute("user", authUser);
			return "redirect:/list";
		}
		else {
			String errMsg = "用户名或密码错误!";
			model.addAttribute("errMsg", errMsg);
			return "fore/login";
		}
	}
}
