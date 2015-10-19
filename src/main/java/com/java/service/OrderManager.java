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

	public List<Order> findOrdersWithIds(List<Integer> idList) {
		List<Order> orders = new ArrayList<Order>();
		for (Integer id : idList) {
			Order temp = getOrderWithId(id + "");
			orders.add(temp);
		}
		return orders;
	}
	
	public void updateOrder(Order order){
		orderDao.updateOrder(order);
	}
	
	public List<Block> getProposedBlocksWithOrders(List<Order> allorders, User user) {
		List<Block> pblocks = new ArrayList<Block>();
		boolean added = false;
		for (Order order : allorders) {

			for (Block pb : pblocks) {
				if (pb.getSide().equals(order.getSide()) && pb.getSymbol().equals(order.getSymbol())) {
					pb.getOrders().add(order);
					pb.setTotalQty(pb.getTotalQty() + order.getTotalQty());
					added = true;
					break;
				}
			}
			if (!added) {
				Block newTemp = new Block(order.getSymbol(), order.getSide(), "Proposed", user,new ArrayList<Order>());
				newTemp.getOrders().add(order);
				newTemp.setTotalQty(order.getTotalQty());
				pblocks.add(newTemp);
			}
			added = false;

		}
		return pblocks;
	}
	
}
