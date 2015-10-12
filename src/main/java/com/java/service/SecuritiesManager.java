package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.SecuritiesDao;
import com.java.pojo.Securities;


@Component
public class SecuritiesManager {

	@Autowired
	SecuritiesDao securitiesdao;

	public Securities getSecuritiesDetails(String symbol) {
		return securitiesdao.getSecuritiesDetails(symbol);
	}
}