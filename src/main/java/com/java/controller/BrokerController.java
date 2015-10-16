package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.ExecBlock;
import com.java.pojo.ExecuteBlock;
import com.java.service.ExecuteBlockManager;

@Controller
public class BrokerController {


	@Autowired
	private ExecuteBlockManager executeBlockManager;
/*
	@RequestMapping(value="/")
	public String loadIndex(Model model) {
		ExecuteBlock execblock = executeBlockManager.getUserDetails(1);
		System.out.println(execblock.toString());
	
		model.addAttribute("pageHeader", "Hello, World!");
		return "Broker";
	}
	*/
	

		@RequestMapping(value="/ExecBlock")
		public ModelAndView LoadDashBoard(Model model) {

			List<ExecBlock> pmposlist=new ArrayList<ExecBlock>();
			pmposlist= executeBlockManager.getUserDetails(1);

		System.out.println("in controller");
		ModelAndView model1 = new ModelAndView("ExecBlock");
			model1.addObject("blocklist", pmposlist);
			
			return model1;
		}

	
}
