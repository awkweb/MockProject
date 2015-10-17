package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.pojo.Block;
import com.java.pojo.Executeblock;

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

	public Executeblock getExecuteBlock(String executeBlockId) {
		return entityManager.find(Executeblock.class, executeBlockId);
	}

	public List<Executeblock> getExecuteBlocksForBlocks(List<Block> blocks) {
		String sql = "FROM Executeblock e WHERE e.block = :block";
		List<Executeblock> executeBlocks = new ArrayList<Executeblock>();
		for (Block block : blocks) {
			List<Executeblock> result = entityManager.createQuery(sql, Executeblock.class)
					.setParameter("block", block)
					.getResultList();
			executeBlocks.addAll(result);
		}
		return  executeBlocks;
	}

	@Transactional
	public void saveDetails(Executeblock user) {
		entityManager.persist(user);
	}

}
