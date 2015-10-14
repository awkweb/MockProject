package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	public Order getOrderDetails(String orderId) {
		return entityManager.find(Order.class, orderId);
	}
	
	@Transactional
	public void saveDetails(Order order) {
		System.out.println("in dao");
		entityManager.persist(order);
	}
	
	public List<User> getTraders() {
		String sql = "FROM User u WHERE u.role = :role";
		List<User> users = entityManager.createQuery(sql, User.class)
				.setParameter("role", "et")
				.getResultList();
		return users;
	}

}
