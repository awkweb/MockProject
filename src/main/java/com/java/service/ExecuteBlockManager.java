package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.ExecuteBlockDao;
import com.java.pojo.ExecuteBlock;

@Component
public class ExecuteBlockManager {

	@Autowired
	ExecuteBlockDao executeblockDao;
	
	public ExecuteBlock getUserDetails(int executeBlockId) {
		return executeblockDao.getExecuteBlockDetails(executeBlockId);	
		}

	
}
