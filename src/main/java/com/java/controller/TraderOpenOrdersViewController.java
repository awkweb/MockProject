package com.java.controller;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Collection;
import java.util.HashMap;
>>>>>>> fc5a99a4a1b7583099248764a903b13df9761130
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
=======
import org.springframework.ui.Model;
>>>>>>> fc5a99a4a1b7583099248764a903b13df9761130
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Block;
import com.java.pojo.Order;
<<<<<<< HEAD
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
// @RequestMapping(value = "/")
public class TraderOpenOrdersViewController {

	@Autowired
	private BlockManager blockManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value = "/open-orders")
	public String loadEmptyModelBean(HttpSession session) {
		User user = (User) session.getAttribute("authenticatedUser");
		// get orders from Orders table with trader id
		// add orders to user.orders2 list
		// get user.getOrders2
		List<Order> ordersList = orderManager.getOrdersForTrader(user);
		List<Block> proposedBlocks = new ArrayList<Block>();
		List<Block> tradersBlocks = blockManager.getBlocksForUser(user);
		user.setBlocks(tradersBlocks);
		
		for (Order o : ordersList) {
			
			if (o.getStatus().equals("Open") && o.getSide().equals("Buy") && o.getBlock() == null){		
				Block pBlock = new Block();
				pBlock.setStatus("New");
				pBlock.setTotalQty(o.getTotalQty());
				pBlock.setLimitPrice(o.getLimitPrice());
				pBlock.setStopPrice(o.getStopPrice());
				pBlock.setExecutedQty(o.getAllocQty());
				pBlock.setOpenQty(o.getOpenQty());
				proposedBlocks.add(pBlock);
				System.out.println(o.toString());
				
			}
				
		}

		/*
		 * List<Block> blocks = blockManager.getBlocksForUser(user);
		 * user.setBlocks(blocks); 
		 * for (Block block : user.getBlocks()) {
		 * List<Order> orders = orderManager.getOrdersForBlock(block);
		 * block.setOrders(orders); }
		 */
		return "open-orders";
	}

	/*
	 * @RequestMapping(value="/open-orders") public String
	 * createProposedBlock(HttpSession session) { User user = (User)
	 * session.getAttribute("authenticatedUser"); List<Order> ordersList =
	 * user.getOrders2();
	 * 
	 * for(Order o : ordersList){ System.out.println(o.toString()); }
	 * 
	 * List<Block> blocks = blockManager.getBlocksForUser(user);
	 * user.setBlocks(blocks); for (Block block : user.getBlocks()) {
	 * List<Order> orders = orderManager.getOrdersForBlock(block);
	 * block.setOrders(orders); } return "open-orders"; }
	 */
=======
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
	
>>>>>>> fc5a99a4a1b7583099248764a903b13df9761130

}
