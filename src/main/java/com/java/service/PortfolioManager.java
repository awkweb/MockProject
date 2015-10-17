package com.java.service;

import org.springframework.stereotype.Component;

import com.java.dao.PortfolioDao;
import com.java.pojo.Portfolio;

@Component
public class PortfolioManager {

	//@Autowired
	PortfolioDao portfolioDao;
	
	public Portfolio getUserDetails(String portId) {
		return portfolioDao.getPortfolioDetails(portId);
	}

	
}
