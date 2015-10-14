package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.ExecuteBlockDao;
import com.java.pojo.Executeblock;

@Component
public class ExecuteBlockManager {

	//@Autowired
	ExecuteBlockDao executeblockDao;
	
	public Executeblock getUserDetails(int executeBlockId) {
		return executeblockDao.getExecuteBlockDetails(executeBlockId);	
		}

	
}
