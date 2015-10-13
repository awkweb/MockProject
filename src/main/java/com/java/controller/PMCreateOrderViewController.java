package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.pojo.Order;
import com.java.service.OrderManager;

@Controller
public class PMCreateOrderViewController {

	@Autowired
	private OrderManager orderManager;
	
	@RequestMapping(value="/PMCreateOrder_form", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBeanOrder(Model model){
		//model.addAttribute("equity", new Equity());
		model.addAttribute("pageHeader", "Hello, World!");
		return new ModelAndView("PMCreateOrder_form", "order", new Order());
			   
	}
	
	
	@RequestMapping(value="/orderdetails", method = RequestMethod.POST)
		public String addOrder( @ModelAttribute("order") Order order, Model model )
		
		{
		
		model.addAttribute("symbol", order.getSymbol());	
		model.addAttribute("side", order.getSide());
		model.addAttribute("ordertype", order.getOrdertype());
		model.addAttribute("qualifier", order.getQualifier());
		model.addAttribute("trader", order.getTraderId());
		model.addAttribute("accountType", order.getAccountType());
		model.addAttribute("portfolio", order.getPortId());
		model.addAttribute("qty", order.getTotalQty());
		model.addAttribute("stopPrice", order.getStopPrice());
		model.addAttribute("limitPrice", order.getLimitPrice());
		model.addAttribute("notes", order.getNotes());
		
//		order.setSymbol(order.getSymbol());
//		order.setSide(order.getSide());
//		order.setOrdertype(order.getOrdertype());
//		order.setQualifier(order.getQualifier());
//		order.setTraderId(order.getTraderId());
//		order.setAccountType(order.getAccountType());
//		order.setPortId(order.getPortId());
//		order.setTotalQty(order.getTotalQty());
//		order.setStopPrice(order.getStopPrice());
//		order.setLimitPrice(order.getLimitPrice());
//		order.setTraderId(order.getNotes());
		//orderManager.
		
		System.out.println(order.toString());
			
			return "pmOrderForm_results";
		}
	
	@ModelAttribute("sideList")
	public List<String> provideEquityType()
	{
		List<String> sideList = new ArrayList<String>();
		sideList.add("Buy");
		sideList.add("Sell");
		return sideList;

	}
	
}
