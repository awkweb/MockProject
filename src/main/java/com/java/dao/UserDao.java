package com.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	public User getUserWithId(String userId) {
		return entityManager.find(User.class, userId);
	}

	public User getUserWithUsername(String username) {
		String sql = "FROM User u WHERE u.username = :username";
		try {
			User user = entityManager.createQuery(sql, User.class)
					.setParameter("username", username)
					.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Transactional
	public Boolean updatePasswordForUsernameAndEmail(String username, String email, String password) {		
		String sql = "UPDATE User "
				+ "SET password = :password "
				+ "WHERE username = :username AND email = :email";
		int result = entityManager.createQuery(sql)
		.setParameter("password", password)
		.setParameter("username", username)
		.setParameter("email", email)
		.executeUpdate();
		Boolean success = result != 0;
		return success;
	}

	@Transactional
	public void saveUser(User user) {
		entityManager.persist(user);
	}

}
