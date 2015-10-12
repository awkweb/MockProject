package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.SecurityDao;
import com.java.pojo.Security;


@Component
public class SecurityManager {

	@Autowired
	SecurityDao securitydao;

	public Security getSecurityDetails(String symbol) {
		return securitydao.getSecurityDetails(symbol);
	}
}