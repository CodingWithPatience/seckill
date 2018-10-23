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
 * register handler
 * @author zzh
 * 2018年9月30日
 */
@Controller
@RequestMapping("")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String register() {
		return "fore/register";
	}

	@RequestMapping(value="register", method=RequestMethod.POST)
	public String doRegister(User user, Model model, HttpSession session) {
		String name = user.getName();
		boolean exist = userService.isNameExist(name);
		if(exist) {
			String errMsg = "用户名已存在！";
			model.addAttribute("errMsg", errMsg);
			return "fore/register";
		}
		else {
			userService.insert(user);
			return "redirect:/registerResult";
		}
	}
	
	@RequestMapping(value="registerResult", method=RequestMethod.GET)
	public String registerResult() {
		return "fore/registerSuccess";
	}
}
