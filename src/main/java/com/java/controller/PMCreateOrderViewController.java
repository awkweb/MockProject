package com.java.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	Calendar calendar = Calendar.getInstance();

	@Autowired
	private OrderManager orderManager;
	
	@RequestMapping(value="/PMCreateOrder_form", method = RequestMethod.GET)
	public ModelAndView loadEmptyModelBeanOrder(Model model){
		model.addAttribute("pageHeader", "Hello, World!");
		return new ModelAndView("PMCreateOrder_form", "order", new Order());
			   
	}
	
	
	@RequestMapping(value="/orderdetails", method = RequestMethod.POST)
		public String addOrder( @ModelAttribute("order") Order order, Model model )
		
		{
		
		model.addAttribute("symbol", order.getSymbol());	
/*		model.addAttribute("side", order.getSide());
		model.addAttribute("ordertype", order.getOrdertype());
		model.addAttribute("qualifier", order.getQualifier());
		model.addAttribute("trader", order.getTraderId());
		model.addAttribute("accountType", order.getAccountType());
		model.addAttribute("portfolio", order.getPortId());
		model.addAttribute("qty", order.getTotalQty());
		model.addAttribute("stopPrice", order.getStopPrice());
		model.addAttribute("limitPrice", order.getLimitPrice());
		model.addAttribute("notes", order.getNotes());
		*/

		order.setStatus("New");
		order.setOrderId("2");
		order.setPortId("1");
		order.setTraderId("2");
		order.setPmId("1");
		order.setTimestamp(new java.sql.Timestamp(calendar.getTime().getTime()));
		
		System.out.println(order.toString());
		System.out.println("in controller");
		System.out.println(order==null);
		
		orderManager.saveOrderDetails(order);
			
			return "pmOrderForm_results";
		}
	
	@ModelAttribute("sideList")
	public List<String> provideEquitySide()
	{
		List<String> sideList = new ArrayList<String>();
		sideList.add("Buy");
		sideList.add("Sell");
		return sideList;

	}
	
	@ModelAttribute("orderTypeList")
	public List<String> provideEquityOrderType()
	{
		List<String> orderTypeList = new ArrayList<String>();
		orderTypeList.add("Market");
		orderTypeList.add("Limit");
		orderTypeList.add("Stop Limit");
		orderTypeList.add("Stop");
		return orderTypeList;

	}
	
	@ModelAttribute("qualifierTypeList")
	public List<String> provideEquityQualifierType()
	{
		List<String> qualifierTypeList = new ArrayList<String>();
		qualifierTypeList.add("Day Order");
		qualifierTypeList.add("GTC");
		return qualifierTypeList;

	}
	
/*	@ModelAttribute("TraderTypeList")
	public List<String> provideEquityTrader()
	{
		List<String> TraderTypeList = new ArrayList<String>();
		TraderTypeList.add("Day Order");
		TraderTypeList.add("GTC");
		return TraderTypeList;

	}
*/
	
	@ModelAttribute("accountTypeList")
	public List<String> provideEquityAccountType()
	{
		List<String> accountTypeList = new ArrayList<String>();
		accountTypeList.add("Cash");
		accountTypeList.add("Margin");
		return accountTypeList;

	}
	
	
/*	@ModelAttribute("portfolioTypeList")
	public List<String> provideEquityportfolioType()
	{
		List<String> portfolioTypeList = new ArrayList<String>();
		portfolioTypeList.add("Cash");
		portfolioTypeList.add("Margin");
		return portfolioTypeList;

	}
*/	
}
