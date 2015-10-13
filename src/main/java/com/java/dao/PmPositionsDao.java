package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Order;
import com.java.pojo.PmPositions;
import com.java.pojo.Portfolio;

@Repository
public class PmPositionsDao {
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

	public List<PmPositions> getPositionsDetails(String pmid) {
		
		List plist=new ArrayList();
		
		plist=entityManager.createQuery("select p.name,s.symbol,o.total_qty,s.market_price from   mockproject.portfolios p inner join mockproject.order o on p.port_id=o.portfolioid inner join securities s on o.symbol=s.symbol where pmid=\'?1\';").setParameter(1, pmid).getResultList();
		List<PmPositions> poslist=new ArrayList<PmPositions>();
		int j=0;
		int i;
		while(!plist.isEmpty())
		{	PmPositions p=new PmPositions();
		for(i=0;i<4;i++){
		
			
			p.setPort_name((String)plist.get(j));
			j+=1;
			p.setSecurity((String)plist.get(j));
			j+=1;
			p.setQty((int)plist.get(j));
			j+=1;
			p.setAvg_price((float)plist.get(j));
			j+=1;
			
		}
		poslist.add(p);
			
		}
		
		return poslist;
		
	}
	
	

}
