package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.PortfolioDao;
import com.java.pojo.Portfolios;

@Component
public class PortfolioManager {

	@Autowired
	PortfolioDao portfolioDao;
	
	public Portfolios getUserDetails(String portId) {
		return portfolioDao.getPortfolioDetails(portId);
	}

	
}
