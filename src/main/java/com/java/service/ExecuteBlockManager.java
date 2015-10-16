package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.ExecuteBlockDao;
import com.java.pojo.ExecBlock;
import com.java.pojo.ExecuteBlock;

@Component
public class ExecuteBlockManager {

	//@Autowired
	ExecuteBlockDao executeblockDao;
	
	public List<ExecBlock> getUserDetails(int executeBlockId) {
		return executeblockDao.getExecuteBlockDetails1(executeBlockId);	
		}

	
}
