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
	public void updatestatuscancel(List<String> choice){
		int i=0;

		System.out.println(choice);
		for(String orderid : choice){
			Order order=entityManager.find(Order.class, orderid);

			System.out.println(order.getOrderId());
			order.setStatus("Cancelled");
			System.out.println(order.getStatus());
			entityManager.merge(order);
			i++;
			System.out.println();

		}


	}


}
