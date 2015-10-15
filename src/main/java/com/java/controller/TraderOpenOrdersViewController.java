package com.java.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.ProposedBlock;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.OrderManager;
import com.java.service.UserManager;

@Controller
public class TraderOpenOrdersViewController {
	
	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/open-order")
	public String testOrders(HttpSession session){
		User user = (User) session.getAttribute("authenticatedUser");
		List<Order> orders = orderManager.getOpenOrdersforUser(user);
		List<Block> proposedOrders = orderManager.getProposedBlocks(orders,user);
		orderManager.printpbList(proposedOrders);
		System.out.println("HERE");
		
		
		session.setAttribute("proposedblocks", proposedOrders);
		return "open-order";
	}
	

}
