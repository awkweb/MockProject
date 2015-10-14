package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.BlockDao;
import com.java.pojo.Block;

@Component
public class BlockManager {

	@Autowired
	BlockDao blockDao;
	
	public Block getBlockWithId(String blockId) {
		return blockDao.getBlockWithId(blockId);
	}
	
	public List<Block> getBlocksWithTraderId(String traderId) {
		return blockDao.getBlocksWithTraderId(traderId);
	}
	
	public void saveBlock(Block block) {
		blockDao.saveBlock(block);
	}
	
}
