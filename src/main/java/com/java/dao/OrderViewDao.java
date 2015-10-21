package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Order;

@Repository
public class OrderViewDao {
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

	@PersistenceContext

	private EntityManager entityManager1;

	public void setEntityManager1(EntityManager entityManager) {
		this.entityManager1 = entityManager;
	}

	public List<Order> getOrderDetails(String pmid,String status) {

		List<Order> pmorderlist=new ArrayList<Order>();
		String sql =  "FROM Order o where o.user1.userId=:pid and o.status=:status";
		pmorderlist = entityManager.createQuery(sql)
				.setParameter("pid",pmid)
				.setParameter("status", status)
				.getResultList();
		return pmorderlist;

	}

	@Transactional
	public void updatestatus(List<String> choice){
		int i=0;

		System.out.println("in dao");
		while(i<choice.size()){
			Order order=entityManager.find(Order.class, choice.get(i));
			order.setStatus("Open");
			i++;
			entityManager.merge(order);



		}


	}

	@Transactional
	public Boolean updateOrderToStatus(String id, String status) {	
		System.out.println("dao updateOrderToStatus");
		String sql = "UPDATE Order "
				+ "SET status = :status "
				+ "WHERE orderId = :orderid";
		int result = entityManager.createQuery(sql)
				.setParameter("status", status)
				.setParameter("orderid", id)
				.executeUpdate();
		Boolean success = result != 0;
		return success;
	}

}
