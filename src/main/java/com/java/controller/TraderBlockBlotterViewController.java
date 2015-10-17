package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String loadEmptyModelBean(HttpSession session, Model model) {
		User user = (User) session.getAttribute("authenticatedUser");
		List<Block> blocks = blockManager.getBlocksForUserWithStatus(user, "new");
		user.setBlocks(blocks);
		for (Block block : user.getBlocks()) {
			List<Order> orders = orderManager.getOrdersForBlock(block);
			block.setOrders(orders);
		}
		return "block-blotter";
	}

	@RequestMapping(value = "/remove-orders", method = RequestMethod.POST)
	public String removeOrders(@RequestBody String json) {
		String[] filteredJson = json.substring(1, json.length() - 1).split(",");
		List<String> orderIds = new ArrayList<String>();
		
		for(String id : filteredJson) {
			orderIds.add(id.substring(1, id.length() - 1));
		}
		for (String id : orderIds) {
			orderManager.removeOrderFromBlockWithOrderId(id);
		}
		return "block-blotter";
	}

}
