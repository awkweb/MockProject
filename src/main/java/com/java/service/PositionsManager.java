package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.PositionDao;
import com.java.pojo.Positions;

@Component
public class PositionsManager {

	@Autowired
	PositionDao positionsDao;
	
	public Positions getUserDetails(String positionId) {
		return positionsDao.getPositionDetails(positionId);
	}

	
}
