package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Executeblock;

@Repository
public class ExecuteBlockDao {
	
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

	public Executeblock getExecuteBlockDetails(int executeBlockId) {
		return entityManager.find(Executeblock.class, executeBlockId);
	}
	
	@Transactional
	public void saveDetails(Executeblock user) {
		entityManager.persist(user);
	}
	
	

}
