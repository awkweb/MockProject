package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.java.pojo.Security;

@Repository
public class SecurityDao {

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

	public Security getSecurityDetails(String symbol) {
		return entityManager.find(Security.class, symbol);
	}

	public List<Security> getSecurities() {
		String sql = "FROM Security s ORDER BY s.symbol";
		try {
			List<Security> securities = entityManager
					.createQuery(sql, Security.class)
					.getResultList();
			return securities;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Security getSecurityDetailsFromName(String name) {
		String sql = "FROM Security s WHERE s.name = :name";
		try {
			Security Securities = entityManager.createQuery(sql,Security.class)
					.setParameter("name", name)
					.getSingleResult();
			return Securities;
		} catch (NoResultException e) {
			return null;
		}
	}

}
