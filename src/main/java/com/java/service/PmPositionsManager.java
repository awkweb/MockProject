package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import com.java.dao.PmPositionsDao;
import com.java.pojo.Position;

@Component
public class PmPositionsManager {

	@Autowired
	PmPositionsDao positiondao;
	
	public List<Position>getPositionDetails(String pm_id) {
	//	return positiondao.getPositionsDetails(pm_id);
		return null;
	}

	
}
