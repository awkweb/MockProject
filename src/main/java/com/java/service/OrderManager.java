package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderDao;
import com.java.pojo.Order;


@Component
public class OrderManager {

	//@Autowired
	OrderDao orderDao;
	
	public Order getOrderDetails(String orderId) {
		return orderDao.getOrderDetails(orderId);
	}
	
	public void saveOrderDetails(Order order){
		 orderDao.saveDetails(order);
	}

	
}
