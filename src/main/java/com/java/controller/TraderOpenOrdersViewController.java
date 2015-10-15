package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.OrderManager;
import com.java.service.UserManager;

@Controller
@RequestMapping(value = "/")
public class TraderOpenOrdersViewController {

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value = "order")
	public String showOrders(Model model) {
		List<Order> ordersList = new ArrayList<Order>();
		ordersList = orderManager.getOrderByStatusAndSide("Open","Sell");

		/*for (Order o : ordersList) {
			System.out.println(o.toString());
		
			model.addAttribute("orderId", o.getOrderId());
			model.addAttribute("orderSide", o.getSide());
			model.addAttribute("orderSymbol", o.getSymbol());
			model.addAttribute("type", o.getLimitPrice());			
		}*/
		
		Order o = orderManager.getOrderDetails("1");
		System.out.println(o);
		
		

		return "testOrderView";
	}

}
