package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.java.pojo.Positions;

public class PositionDao {
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

	public Positions getPositionDetails(String positionId) {
		return entityManager.find(Positions.class, positionId);
	}
	
}
