package com.java.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.ExecBlock;
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
	
	
	public List<ExecBlock> getExecuteBlockDetails1(int executeBlockId) {
		System.out.println("in dao");
		
		List<Object[]> sidelist=new ArrayList();
		List<Object[]> securitylist=new ArrayList();
		List<Object[]> qtylist=new ArrayList();
		List<Object[]> pricelist=new ArrayList();
		List<Object[]> time=new ArrayList();
		List<Object[]> blockid=new ArrayList();
		List<Object[]> status=new ArrayList();
	
		
		List<ExecBlock> blocklist=new ArrayList<ExecBlock>();
sidelist = entityManager.createQuery( "select b.side FROM ExecuteBlock b where b.execid=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
securitylist = entityManager.createQuery( "select b.symbol FROM ExecuteBlock b where b.execid.userId=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
qtylist = entityManager.createQuery( "select b.executedQty FROM ExecuteBlock b where b.user1.execid=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
pricelist = entityManager.createQuery( "select b.tradePrice FROM ExecuteBlock b where b.user1.execid=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
time = entityManager.createQuery( "select b.transactionTime FROM ExecuteBlock b where b.user1.execid=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
blockid = entityManager.createQuery( "select b.blockId FROM ExecuteBlock b where b.user1.execid=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
status = entityManager.createQuery( "select b.status FROM ExecuteBlock b where b.user1.execid=:pid ExecuteBlock BY b.blockId ASC").setParameter("pid",executeBlockId).getResultList();
int i=0;

while(i!=sidelist.size()){
	
	ExecBlock pp=new ExecBlock();
//	pp.setPortfolioname(portfolionamelist.get(0).toString());
	
	pp.setSide(sidelist.get(i));
	

	pp.setSymbol(securitylist.get(i));
	
	
	pp.setQty(qtylist.get(i));
	

	pp.setTrasacprice(pricelist.get(i));
	pp.setTrasactiontime(time.get(i));
	pp.setBlockid(blockid.get(i));
	pp.setStatus(status.get(i));
    blocklist.add(pp);

	
	i=i+1;
	
}
	
	
	
	
	
	
	
System.out.println("leaving dao dhfksdghkjdgh");
System.out.println(blocklist);
		return blocklist;
		
	}


}
