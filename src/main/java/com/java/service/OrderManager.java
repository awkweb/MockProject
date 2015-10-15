package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderDao;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.ProposedBlock;
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

	public List<Order> getOpenOrdersforUser(User user) {
		return orderDao.getOpenOrdersforUser(user);
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	public List<Block> getProposedBlocks(List<Order> allorders,User user) {
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
				Block newTemp = new Block(order.getSymbol(),order.getSide(),"Proposed" ,user ,new ArrayList<Order>());
				newTemp.getOrders().add(order);
				newTemp.setTotalQty(order.getTotalQty());
				pblocks.add(newTemp);
			}
			added = false;

		}
		return pblocks;
	}

	public void printpbList(List<Block> proposedOrders) {
		for (Block pb : proposedOrders) {
			System.out.println(pb.getSymbol() + "_" + pb.getSide());
			System.out.println(pb.getOrders());
		}

	}

}
