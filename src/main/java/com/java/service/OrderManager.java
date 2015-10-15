package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderDao;
import com.java.pojo.Block;
import com.java.pojo.Order;
<<<<<<< HEAD
import com.java.pojo.User;
=======
>>>>>>> 15c622984a11e3827ce883928fd391da0ec711fe

@Component
public class OrderManager {

	@Autowired
	OrderDao orderDao;
	
	public Order getOrderWithId(String orderId) {
		return orderDao.getOrderWithId(orderId);
<<<<<<< HEAD
	}
	
	public List<Order> getOrdersForBlock(Block block) {
		return orderDao.getOrdersForBlock(block);
	}
	
	public List<Order> getOrdersForTrader(User trader){
		return orderDao.getTraderOrders(trader);
=======
	}
	
	public List<Order> getOrdersForBlock(Block block) {
		return orderDao.getOrdersForBlock(block);
>>>>>>> 15c622984a11e3827ce883928fd391da0ec711fe
	}
	
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

}
