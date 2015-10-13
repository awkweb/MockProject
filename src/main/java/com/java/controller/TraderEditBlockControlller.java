package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




import org.springframework.web.servlet.ModelAndView;


import com.java.pojo.Order;
import com.java.service.BlockManager;
import com.java.service.OrderManager;


@Controller
public class TraderEditBlockControlller {

	@Autowired
	private OrderManager orderManager;
	@Autowired
	private BlockManager blockManager;
	
	@RequestMapping(value="/Edit_Block", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBean(){
		//model.addAttribute("equity", new Equity());	
		return new ModelAndView("Edit_Block", "order", new Order()); //new Equity() passes a blank obj
 		
		// The same equity string is mentioned in
		// the modelAttribute tag of index_form
	}
	
	@RequestMapping(value="/editblock", method=RequestMethod.POST)
		public String showOrder( @ModelAttribute("order") Order order,@RequestParam("limitPrice") float limit_price, @RequestParam("stopPrice") float stop_price, Model model)
		
		{

			model.addAttribute(order);

			order.setLimitPrice((float)12.22);
			order.setStopPrice((float)123.34);
			order.setAllocQty(12);
			
			order.setOrderId("1");
			model.addAttribute("limitPrice",order.getLimitPrice());
			
			model.addAttribute("stopPrice",order.getStopPrice());
			
			model.addAttribute("greeting","u successfully entered the details");
			System.out.println("order"+order.toString());
			orderManager.getUserDetails(order.getOrderId());
			
			return "success";
		}
	
		
}
