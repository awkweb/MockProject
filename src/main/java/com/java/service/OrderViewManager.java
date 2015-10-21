package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.OrderViewDao;
import com.java.pojo.Order;

@Component
public class OrderViewManager {

	@Autowired
	OrderViewDao orderdao;

	public List<Order> getPmOrderDetails(String pm_id,String status) {
		return orderdao.getOrderDetails(pm_id,status);
	}

	public void updateorder(List<String> choice){
		System.out.println("in man");
		orderdao.updatestatus(choice);
	}

	public Boolean updateOrderToStatus(String id, String status) {
		return orderdao.updateOrderToStatus(id, status);
	}

}
