package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.dao.UserDao;
import com.java.pojo.User;

@Component
public class UserManager {

	@Autowired
	UserDao userdao;
	
	public User getUserWithId(String userId) {
		return userdao.getUserWithId(userId);
	}
	
	public User getUserWithUsername(String username) {
		return userdao.getUserWithUsername(username);
	}
	
	public Boolean updatePasswordForUsernameAndEmail(String username, String email, String password) {		
		return userdao.updatePasswordForUsernameAndEmail(username, email, password);
	}

	public void saveUser(User user) {
		userdao.saveUser(user);
	}
	
	public List<User> getTraders() {
		return userdao.getTraders();
	}
	
}
