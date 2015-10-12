package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.BlockDao;
import com.java.dao.PortfolioDao;
import com.java.pojo.Block;
import com.java.pojo.Portfolio;

@Component
public class BlockManager {

	@Autowired
	BlockDao blockDao;
	
	public Block getUserDetails(String blockId) {
		return blockDao.getBlockDetails(blockId);
	}

	
}
