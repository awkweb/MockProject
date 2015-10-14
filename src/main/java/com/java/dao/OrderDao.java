package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
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

	public Order getOrderWithId(String orderId) {
		return entityManager.find(Order.class, orderId);
	}
	
	public List<Order> getOrdersWithBlockId(String blockId) {
		String sql = "FROM Order o WHERE o.blockId = :blockId";
		List<Order> orders = entityManager.createQuery(sql, Order.class)
				.setParameter("blockId", blockId)
				.getResultList();
		return orders;
	}
	
	@Transactional
	public void saveOrder(Order order) {
		entityManager.persist(order);
	}

}
