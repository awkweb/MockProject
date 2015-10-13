package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Order;
import com.java.pojo.PmPositions;
import com.java.pojo.User;
import com.java.service.PmPositionsManager;
import com.java.service.UserManager;

@Controller
public class PmPositionsViewController {
	
	@Autowired
	private PmPositionsManager pos;

	@RequestMapping(value="/PmPositionsDashboard")
	public String LoadDashBoard(Model model) {
		List<PmPositions> pmposlist=new ArrayList<PmPositions>();
		pmposlist= pos.getPositionDetails("1");

	
		model.addAttribute("portfoliolist", pmposlist);
		return "pmPositionDashboardView";
	}
	
	

}
