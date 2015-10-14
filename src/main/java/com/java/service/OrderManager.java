package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderDao;
import com.java.pojo.Order;

@Component
public class OrderManager {

	@Autowired
	OrderDao orderDao;
	
	public Order getOrderWithId(String orderId) {
		return orderDao.getOrderWithId(orderId);
	}
	
	public List<Order> getOrdersWithBlockId(String blockId) {
		return orderDao.getOrdersWithBlockId(blockId);
	}
	
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

}
