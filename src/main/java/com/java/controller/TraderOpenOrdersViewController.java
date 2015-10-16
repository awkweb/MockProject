package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.OrderManager;

@Controller
public class TraderOpenOrdersViewController {
	
	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/open-orders")
	public String openOrders(HttpSession session, Model model){
		User user = (User) session.getAttribute("authenticatedUser");
		List<Order> orders = orderManager.getOpenOrdersforUser(user);
		List<Block> proposedBlocks = orderManager.getProposedBlocksWithOrders(orders, user);
		
		session.setAttribute("proposedBlocks", proposedBlocks);
		return "open-orders";
	}

}
