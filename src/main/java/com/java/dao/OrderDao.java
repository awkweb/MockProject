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
		List<Order> orders;
		orders = entityManager.createQuery(sql, Order.class)
				.setParameter("block", block)
				.getResultList();
		return orders;
	}

	public List<Order> getOpenOrdersForUser(User user){
		String sql = "FROM Order o WHERE o.user2 = :user "
				+ "AND o.status = 'Open' "
				+ "AND o.block = null";
		List<Order> orders = entityManager.createQuery(sql, Order.class)
				.setParameter("user", user)
				.getResultList();
		return orders;
	}

	@Transactional
	public void saveOrder(Order order) {
		entityManager.persist(order);
	}

	@Transactional
	public void updateOrder(Order order) {
		entityManager.merge(order);
	}

	@Transactional
	public Boolean removeOrderFromBlockWithOrderId(String orderId) {
		String sql = "UPDATE Order "
				+ "SET blockid = :blockid "
				+ "WHERE order_id = :orderid";
		int result = entityManager.createQuery(sql)
				.setParameter("blockid", null)
				.setParameter("orderid", orderId)
				.executeUpdate();
		Boolean success = result != 0;
		return success;
	}

	public static void updateOrder(Block selectedBlock) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public boolean updateOrderPriceForIdAndType(String id, String type, float price) {
		String sql;
		switch (type) {
		case "Stop":
			sql = "UPDATE Order "
					+ "SET stopPrice = :price "
					+ "WHERE orderId = :orderId";
			break;
		default:
			sql = "UPDATE Order "
					+ "SET limitPrice = :price "
					+ "WHERE orderId = :orderId";
			break;
		}
		
		int result = entityManager.createQuery(sql)
				.setParameter("price", price)
				.setParameter("orderId", id)
				.executeUpdate();
		Boolean success = result != 0;
		return success;
	}

}
