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
public class ForgotPasswordViewController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/forgot", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBean() {
		return new ModelAndView("forgot-password", "user", new User());
	}

	@RequestMapping(value="/reset", method = RequestMethod.POST)
	public String resetPassword( @ModelAttribute("user") User user, Model model) {
		if (userManager.updatePasswordForUsernameAndEmail(user.getUsername(),
				user.getEmail(), user.getPassword())) {
			return "login";
		} else {
			model.addAttribute("error", true);
			return "forgot-password";
		}
	}

}
