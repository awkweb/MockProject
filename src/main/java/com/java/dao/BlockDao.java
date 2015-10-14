package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Block;

@Repository
public class BlockDao {
	
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

	public Block getBlockWithId(String blockId) {
		return entityManager.find(Block.class, blockId);
	}
	
	public List<Block> getBlocksWithTraderId(String traderId) {
		String sql = "FROM Block b WHERE b.traderId = :traderId";
		List<Block> blocks = entityManager.createQuery(sql, Block.class)
				.setParameter("traderId", traderId)
				.getResultList();
		return blocks;
	}
	
	@Transactional
	public void saveBlock(Block block) {
		entityManager.persist(block);
	}

}
