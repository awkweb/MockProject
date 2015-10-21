package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.java.pojo.Portfolio;
import com.java.pojo.User;

@Repository
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

	public Portfolio getPortfolioDetails(String portId) {
		return entityManager.find(Portfolio.class, portId);
	}
	
	public List<Portfolio> getPortfolios(String id) {
		User pm = entityManager.find(User.class, id);
		String sql = "FROM Portfolio p WHERE p.user = :user";
		try {
			List<Portfolio> Portfolios = entityManager.createQuery(sql,
					Portfolio.class)
					.setParameter("user", pm)
					.getResultList();
			
			for(Portfolio port : Portfolios){
				System.out.println(port.getName()+" "+port.getPmID());
			}
			return Portfolios;
		} catch (NoResultException e) {
			System.out.println("gets null");
			return null;
		}
	}

}
