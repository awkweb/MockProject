package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.BlockDao;
import com.java.dao.OrderDao;
import com.java.pojo.Block;
import com.java.pojo.Order;
import com.java.pojo.User;

@Component
public class BlockManager {

	@Autowired
	BlockDao blockDao;
	
	public Block getBlockWithId(String blockId) {
		return blockDao.getBlockWithId(blockId);
	}
	
	public List<Block> getBlocksForUserWithStatus(User user, String status) {
		return blockDao.getBlocksForUserWithStatus(user, status);
	}
	
	public List<Block> getBlocksForUserExlcudingStatus(User user, String status) {
		return blockDao.getBlocksForUserExlcudingStatus(user, status);
	}
	
	public void saveBlock(Block block) {
		blockDao.saveBlock(block);
	}
	
	public Boolean setStatusForBlockWithBlockId(String blockId, String status) {		
		return blockDao.setStatusForBlockWithBlockId(blockId, status);
	}
	
	public List<Block> getBlocksForOrder(Order order) {
		return blockDao.getBlocksForOrder(order);
	}

	public void updateBlock(Block selectedBlock) {
		blockDao.updateBlock(selectedBlock);
		
	}
	
}
