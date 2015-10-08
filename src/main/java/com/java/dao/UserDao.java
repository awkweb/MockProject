package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.User;

@Repository
public class UserDao {
	
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

	public User getUserDetails(String userId) {
		return entityManager.find(User.class, userId);
	}
	
	@Transactional
	public void saveDetails(User user) {
		entityManager.persist(user);
	}

}
