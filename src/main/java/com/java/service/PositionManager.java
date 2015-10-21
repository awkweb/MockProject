package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.PositionDao;
import com.java.pojo.PmPosition;

@Component
public class PositionManager {

	@Autowired
	PositionDao positionDao;
	
	public List<PmPosition> getPositionDetails(String pm_id) {
		
		return positionDao.getPositionsDetails(pm_id);
	}
	
	public List<String>  getportnames(){
		return positionDao.getPositionNames();
	}

	public List<PmPosition> getsingleportfoliodetails(String pm_id){
		return positionDao.getsinglePositionDetails(pm_id);
	}

}
