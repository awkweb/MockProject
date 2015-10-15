package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;

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
	
	public List<Order> getOrdersForBlock(Block block) {
		String sql = "FROM Order o WHERE o.block = :block";
		List<Order> orders = entityManager.createQuery(sql, Order.class)
				.setParameter("block", block)
				.getResultList();
		return orders;
	}
	public List<Order> getOpenOrdersforUser(User user){
		String sql = "FROM Order o WHERE o.user2 = :user and o.status = 'Open' and o.block= null";
		List<Order> orders = entityManager.createQuery(sql, Order.class)
				.setParameter("user", user)
				.getResultList();
		return orders;
	}
	
	@Transactional
	public void saveOrder(Order order) {
		entityManager.persist(order);
	}

}
