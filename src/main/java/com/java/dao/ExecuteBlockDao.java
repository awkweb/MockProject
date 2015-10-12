package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.ExecuteBlock;

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

	public ExecuteBlock getExecuteBlockDetails(int executeBlockId) {
		return entityManager.find(ExecuteBlock.class, executeBlockId);
	}
	
	@Transactional
	public void saveDetails(ExecuteBlock user) {
		entityManager.persist(user);
	}
	
	

}
