package com.java.service;

import org.springframework.stereotype.Component;

import com.java.dao.PositionDao;
import com.java.pojo.Position;

@Component
public class PositionManager {

	//@Autowired
	PositionDao positionDao;
	
	public Position getUserDetails(String positionId) {
		return positionDao.getPositionDetails(positionId);
	}

	
}
