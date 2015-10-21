package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.PortfolioDao;
import com.java.pojo.Portfolio;

@Component
public class PortfolioManager {

	@Autowired
	PortfolioDao portfolioDao;

	public Portfolio getUserDetails(String portId) {
		return portfolioDao.getPortfolioDetails(portId);
	}

	public List<Portfolio> getPortfolios(String id) {
		return portfolioDao.getPortfolios(id);
	}

}
