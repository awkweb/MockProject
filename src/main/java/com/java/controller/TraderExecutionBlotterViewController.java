package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Block;
import com.java.pojo.Executeblock;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.ExecuteBlockManager;
import com.java.service.OrderManager;

@Controller
public class TraderExecutionBlotterViewController {
	
	@Autowired
	private BlockManager blockManager;
	
	@Autowired
	private ExecuteBlockManager executeBlockManager;

	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/execution-blotter")
	public String loadEmptyModelBean(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<Block> blocks = blockManager.getBlocksForUserWithStatus(user, "sent for execution");
		System.out.println("Blocks empty? " + blocks.isEmpty());
		List<Executeblock> executeBlocks = executeBlockManager.getExecuteBlocksForBlocks(blocks);
		
		Map<Block, List<Order>> ordersMap = new HashMap<Block, List<Order>>();
		for (Block block : blocks) {			
			List<Order> orders = orderManager.getOrdersForBlock(block);	
			if (!orders.isEmpty()) {
				ordersMap.put(block, orders);
			}
		}
		model.addAttribute("executeBlocks", executeBlocks);
		model.addAttribute("ordersMap", ordersMap);
		
		System.out.println("executeBlocks empty?" + executeBlocks.isEmpty());
		System.out.println("ordersMap.size() " + ordersMap.size());
		
		return "execution-blotter";
	}

}
