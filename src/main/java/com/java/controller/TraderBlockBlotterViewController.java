package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.User;
import com.java.service.UserManager;

@Controller
public class TraderBlockBlotterViewController {
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/")
	public String loadIndex(Model model) {
		User user = userManager.getUserDetails("1");
		System.out.println(user.toString());
	
		model.addAttribute("pageHeader", "Hello, World!");
		return "traderBlockBlotterView";
	}

}
