package com.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;

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
	
	public List<Block> getBlocksForUserWithStatus(User user, String status) {
		String sql = "FROM Block b WHERE b.user = :user "
				+ "AND b.status = :status";
		List<Block> blocks = entityManager.createQuery(sql, Block.class)
				.setParameter("user", user)
				.setParameter("status", status)
				.getResultList();
		return blocks;
	}
	
	public List<Block> getBlocksForUserExlcudingStatus(User user, String status) {
		String sql = "FROM Block b WHERE b.user = :user "
				+ "AND b.status != :status "
				+ "AND b.status != 'cancelled'";
		List<Block> blocks = entityManager.createQuery(sql, Block.class)
				.setParameter("user", user)
				.setParameter("status", status)
				.getResultList();
		return blocks;
	}
	
	public List<Block> getBlocksForOrder(Order order) {
		String sql = "FROM Block b WHERE b.side = :side "
				+ "AND b.symbol = :symbol "
				+ "AND b.status = 'new'";
		List<Block> blocks = entityManager.createQuery(sql, Block.class)
				.setParameter("side", order.getSide())
				.setParameter("symbol", order.getSymbol())
				.getResultList();
		return blocks;
	}
	
	@Transactional
	public void saveBlock(Block block) {
		entityManager.persist(block);
	}
	public void updateBlock(Block block) {
		entityManager.merge(block);
	}
	
	@Transactional
	public Boolean setStatusForBlockWithBlockId(String blockId, String status) {		
		String sql = "UPDATE Block "
				+ "SET status = :status "
				+ "WHERE blockId = :blockId";
		int result = entityManager.createQuery(sql)
		.setParameter("status", status)
		.setParameter("blockId", blockId)
		.executeUpdate();
		Boolean success = result != 0;
		return success;
	}
	@Transactional
	public Boolean setQtyForBlockWithBlockId(String blockId, Integer qty) {	
		int temp =getBlockWithId(blockId).getTotalQty();
		System.out.println(temp);
		System.out.println(blockId);
		String sql = "UPDATE Block "
				+ "SET totalQty = :qty "
				+ "WHERE blockId = :blockId";
		int result = entityManager.createQuery(sql)
		.setParameter("qty", temp+qty)
		.setParameter("blockId", blockId)
		.executeUpdate();
		Boolean success = result != 0;
		return success;
	}

}
