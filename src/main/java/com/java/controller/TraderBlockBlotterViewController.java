package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
public class TraderBlockBlotterViewController {
	
	@Autowired
	private BlockManager blockManager;
	
	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/block-blotter")
	public String loadEmptyModelBean(HttpSession session) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<Block> blocks = blockManager.getBlocksForUser(user);
		user.setBlocks(blocks);
		for (Block block : user.getBlocks()) {
			List<Order> orders = orderManager.getOrdersForBlock(block);
			block.setOrders(orders);
		}
		return "block-blotter";
	}
	
	@RequestMapping(value = "/removeOrders", method = RequestMethod.POST)
	public String removeOrders(@RequestBody String json) {
		json = json.replace("[","");
		json = json.replace("]","");
		String[] orderIds = json.split(",");
		for (String str : orderIds) {
			System.out.println(str);
		}
		return "block-blotter";
	}

}
