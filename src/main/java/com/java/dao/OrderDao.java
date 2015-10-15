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
	
<<<<<<< HEAD
	public List<Order> getTraderOrders(User trader) {
		String sql = "FROM Order o WHERE o.user2 = :trader";
		List<Order> orders = entityManager.createQuery(sql, Order.class)
				.setParameter("trader", trader)
				.getResultList();
		return orders;
	}
	
	
	
	
=======
>>>>>>> 15c622984a11e3827ce883928fd391da0ec711fe
	@Transactional
	public void saveOrder(Order order) {
		entityManager.persist(order);
	}

}
