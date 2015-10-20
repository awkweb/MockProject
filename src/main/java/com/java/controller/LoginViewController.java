package com.java.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.User;
import com.java.service.UserManager;

@Controller
@SessionAttributes("authenticatedUser")
public class LoginViewController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBean() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user, Model model) {
		User authenticatedUser = userManager.getUserWithUsername(user.getUsername());

		if (authenticatedUser != null) {
			if (user.getUsername().equals(authenticatedUser.getUsername()) &&
					user.getPassword().equals(authenticatedUser.getPassword())) {
				model.addAttribute("authenticatedUser", authenticatedUser);

				switch(authenticatedUser.getRole()) {
				case "pm":
					return "landing";
				case "et":
					return "redirect:/block-blotter";
				default:
					return "forward:choose-role";
				}
			}
		}
		model.addAttribute("error", true);
		return "login";
	}

	@RequestMapping("/choose-role")
	public String showChooseRole() {
		return "choose-role";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session ) {
		session.invalidate();
		return "redirect:/";
	}

}
