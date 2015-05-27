package com.xcode.whz.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xcode.whz.bean.User;
import com.xcode.whz.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserService userService;
	

	@RequestMapping(method = RequestMethod.GET)
	public String toRegister(Model model){
		User user = new User();
		user = userService.selectUser("xcj");
		System.out.println(user.getPassword());
		model.addAttribute(user);
		return "user_index";
	}
	
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("user/hello");

		return "login/hello";
	}
	
	
	@RequestMapping(value="{username}", method=RequestMethod.GET)
	public String getView(@PathVariable String username, Model model) {
		User user = new User();
		user = userService.selectUser(username);
		model.addAttribute(user);
		return "user";
	}
}
