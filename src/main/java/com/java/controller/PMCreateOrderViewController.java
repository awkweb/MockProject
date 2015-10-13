package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.Order;
import com.java.pojo.Position;
import com.java.service.OrderManager;

@Controller
public class PMCreateOrderViewController {

	@Autowired
	private OrderManager orderManager;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBeanOrder(Model model){
		//model.addAttribute("equity", new Equity());
		model.addAttribute("pageHeader", "Hello, World!");
		return new ModelAndView("PMCreateOrder_form2", "order", new Order());
			   
	}
	
	
	@RequestMapping(value="/orderdetails", method = RequestMethod.POST)
		public String showOrder( @ModelAttribute("order") Order order, Model model )
		
		{
		model.addAttribute("symbol", order.getSymbol());	
		model.addAttribute("side", order.getSide());
		model.addAttribute("ordertype", order.getOrdertype());
		model.addAttribute("qualifier", order.getQualifier());
		model.addAttribute("trader", order.getTraderId());
		model.addAttribute("accountType", order.getAccountType());
//		model.addAttribute("portfolio", order.getPortfolioID());
		model.addAttribute("qty", order.getTotalQty());
		model.addAttribute("stopPrice", order.getStopPrice());
		model.addAttribute("limitPrice", order.getLimitPrice());
		model.addAttribute("notes", order.getNotes());
		
		System.out.println(order.toString());
			
			return "pmOrder_results";
		}
	
	/*	@RequestMapping(value="/PMCreateOrder_form", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBeanPosition(){
		//model.addAttribute("equity", new Equity());	
		return new ModelAndView("PMCreateOrder_form", "position", new Position());
			   
	}
	
	
	@RequestMapping(value="/orderdetails", method = RequestMethod.POST)
	public String showOrder( @ModelAttribute("position") Position position, Model model )
	
	{
	model.addAttribute("port_id", position.getPortfolio_id());
	model.addAttribute("", position.getOrder_id());

	
	System.out.println(position.toString());
		
		return "pmview_form";
	}*/
	
}
