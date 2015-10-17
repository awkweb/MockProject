package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.ExecuteBlockDao;
import com.java.pojo.Block;
import com.java.pojo.Executeblock;

@Component
public class ExecuteBlockManager {

	@Autowired
	ExecuteBlockDao executeblockDao;

	public Executeblock getExecuteBlock(String executeBlockId) {
		return executeblockDao.getExecuteBlock(executeBlockId);	
	}
	
	public List<Executeblock> getExecuteBlocksForBlocks(List<Block> blocks) {
		return executeblockDao.getExecuteBlocksForBlocks(blocks);
	}

}
