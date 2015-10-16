package com.java.controller;

import java.util.List;

import javax.persistence.NoResultException;
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
		for (String id : orderIds) {
			Boolean bool = orderManager.removeOrderFromBlockWithOrderId(id);
			System.out.println(id + " " + bool);

			Order order = null;
			try {
				order = orderManager.getOrderWithId(id);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			} finally {
				if (order != null) {
					System.out.println(order.toString());
					System.out.println(order.getBlock().getBlockId());
					order.setBlock(null);
					System.out.println(order.getBlock().getBlockId());
				} else {
					System.out.println("Order is null");
				}
			}
		}
		return "block-blotter";
	}

}
