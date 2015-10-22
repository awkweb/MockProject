package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.java.pojo.PmPosition;

@Repository
public class PositionDao {
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

	public List<PmPosition> getPositionsDetails(String pmid) {

		List<PmPosition> pmposlist=new ArrayList<PmPosition>();
		List<Object[]> results1=new ArrayList<Object[]>();
		String sql = "select o.portfolio.name,o.security.symbol, SUM(o.totalQty),AVG(o.security.marketPrice)  "
				+ "FROM Order o where o.user1.userId=:pid "
				+ "GROUP BY o.security.symbol ORDER BY o.portfolio.portId ASC";
		results1 = (List<Object[]>)entityManager.createQuery(sql).setParameter("pid",pmid).getResultList();

		int i=0;
		while(i!=results1.size())
		{
			Object[] obj=new Object[4];
			obj=results1.get(i);
			i++;
			PmPosition pp=new PmPosition();
			pp.setPortfolioname(obj[0]);
			pp.setSecurity(obj[1]);
			pp.setQty(obj[2]);
			pp.setAvgprice(obj[3]);
			pmposlist.add(pp);


		}
		return pmposlist;

	}

	public List<String> getPositionNames(){

		List<String> posnames=new ArrayList<String>();
		
		String sql = "select distinct o.portfolio.name FROM Order o where o.user1.userId=:pid ORDER BY o.portfolio.name ASC ";
		posnames=entityManager.createQuery(sql)
				.setParameter("pid","1")
				.getResultList();

		for(int i=0;i<posnames.size();i++){

		}
		return posnames;

	}


	public List<PmPosition> getsinglePositionDetails(String pmid) {

		List<PmPosition> pmposlist=new ArrayList<PmPosition>();
		List<Object[]> results1=new ArrayList<Object[]>();
		String sql = "select o.portfolio.name,o.security.symbol, SUM(o.totalQty),AVG(o.stopPrice) "
				+ "FROM Order o where o.user1.userId=:pid and o.portfolio.name=:pname "
				+ "GROUP BY o.security.symbol ORDER BY o.portfolio.portId ASC";
		results1 = (List<Object[]>)entityManager.createQuery(sql)
				.setParameter("pid",pmid)
				.setParameter("pname","Technology")
				.getResultList();

		int i=0;
		while(i!=results1.size())
		{
			Object[] obj=new Object[4];
			obj=results1.get(i);
			i++;
			PmPosition pp=new PmPosition();
			pp.setPortfolioname(obj[0]);
			pp.setSecurity(obj[1]);
			pp.setQty(obj[2]);
			pp.setAvgprice(obj[3]);
			pmposlist.add(pp);


		}

		return pmposlist;

	}

}
