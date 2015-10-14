package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.service.BlockManager;
import com.java.service.OrderManager;

@Controller
public class TraderBlockBlotterViewController {
	
	@Autowired
	private BlockManager blockManager;
	
	@Autowired
	private OrderManager orderManager;

	@RequestMapping(value="/block-blotter")
	public String loadEmptyModelBean() {
		List<Block> blocks = blockManager.getBlocksWithTraderId("2");
		for (Block block : blocks) {
			System.out.println(block.toString());
			List<Order> orders = orderManager.getOrdersWithBlockId(block.getBlockId());
			block.setOrders(orders);
		}

		return "block-blotter";
	}

}
