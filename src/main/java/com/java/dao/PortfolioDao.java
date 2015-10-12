package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.java.pojo.Portfolios;

public class PortfolioDao {

	static {
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

	public Portfolios getPortfolioDetails(String portId) {
		return entityManager.find(Portfolios.class, portId);
	}

}
