package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderDao;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;

@Component
public class OrderManager {

	@Autowired
	OrderDao orderDao;

	public Order getOrderWithId(String orderId) {
		return orderDao.getOrderWithId(orderId);
	}

	public List<Order> getOrdersForBlock(Block block) {
		return orderDao.getOrdersForBlock(block);
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	public Boolean removeOrderFromBlockWithOrderId(String orderId) {		
		return orderDao.removeOrderFromBlockWithOrderId(orderId);
	}

	public List<Order> getOpenOrdersforUser(User user) {
		return orderDao.getOpenOrdersForUser(user);
	}

	public List<Block> getProposedBlocksWithOrders(List<Order> orders, User user) {
		List<Block> proposedBlocks = new ArrayList<Block>();
		boolean orderAdded = false;
		for (Order order : orders) {
			for (Block block : proposedBlocks) {
				if (block.getSide().equals(order.getSide()) 
						&& block.getSymbol().equals(order.getSymbol())) {
					block.getOrders().add(order);
					block.setTotalQty(block.getTotalQty() + order.getTotalQty());
					orderAdded = true;
					break;
				}
			}
			if (!orderAdded) {
				Block tempBlock = new Block(order.getSymbol(), order.getSide(),
						"Proposed", user, new ArrayList<Order>());
				tempBlock.getOrders().add(order);
				tempBlock.setTotalQty(order.getTotalQty());
				proposedBlocks.add(tempBlock);
			}
			orderAdded = false;
		}
		return proposedBlocks;
	}

}
