package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.User;
import com.java.service.UserManager;

@Controller
public class LoginViewController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBean() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public String loginUser( @ModelAttribute("user") User user, Model model) {
		User authenticatedUser = userManager.getUserWithUsername(user.getUsername());
		if (user.getUsername().equals(authenticatedUser.getUsername()) &&
				user.getPassword().equals(authenticatedUser.getPassword())) {
			model.addAttribute("username", authenticatedUser.getUsername());
			model.addAttribute("firstName", authenticatedUser.getFirstName());
			model.addAttribute("lastName", authenticatedUser.getLastName());
			model.addAttribute("role", authenticatedUser.getRole());
			
			switch(authenticatedUser.getRole()) {
			case "pm":
				return "landing";
			case "et":
				return "landing";
			default:
				return "choose-role";
			}
		} else {
			model.addAttribute("error", true);
			return "login";
		}
	}

}
