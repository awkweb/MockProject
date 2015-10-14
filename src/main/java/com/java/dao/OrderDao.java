package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Order;

@Repository
public class OrderDao {
	static{
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Order getOrderDetails(String orderId) {
		return entityManager.find(Order.class, orderId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDetails(Order order) {
		System.out.println("in dao");
		entityManager.persist(order);
		System.out.println("Order Added to DB");
		System.out.println(order.toString());
	}

}
