package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.PmPosition;
import com.java.pojo.User;
import com.java.service.PositionManager;

@Controller
public class PmPositionsViewController {

	@Autowired
	private PositionManager positionManager;

	@RequestMapping(value="/positions")
	public ModelAndView LoadDashBoard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<PmPosition> positions = positionManager.getPositionDetails(user.getUserId());
		ModelAndView modelAndView = new ModelAndView("positions");
		modelAndView.addObject("portfoliolist", positions);
		
		positions= positionManager.getsingleportfoliodetails(user.getUserId());
		dropdown();
		List<String> pnames = positionManager.getportnames();

		modelAndView.addObject("pnames", pnames);
		return modelAndView;
	}

	//Need to get signed in users ID HERE
	public ModelAndView LoadSinglePortfolio(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<PmPosition> pmposlist=new ArrayList<PmPosition>();
		pmposlist= positionManager.getsingleportfoliodetails(user.getUserId());
		ModelAndView modelAndView = new ModelAndView("positions");
		modelAndView.addObject("portfoliolist", pmposlist);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST,value="/foo")
	public void foo(@RequestParam("dropDown") String value) {
		System.out.println("in foo");
		System.out.println(value);
	} 

	public List<String> dropdown(){
		List<String> pnames=new ArrayList<String>();
		pnames=positionManager.getportnames();
		return pnames;
	}

}
