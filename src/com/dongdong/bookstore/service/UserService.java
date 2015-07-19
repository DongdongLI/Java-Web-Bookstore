package com.dongdong.bookstore.service;

import com.dongdong.bookstore.dao.impl.User;
import com.dongdong.bookstore.dao.impl.UserDAO;
import com.dongdong.bookstore.dao.impl.UserDAOImpl;

public class UserService {
	private UserDAO userDao=new UserDAOImpl();
	
	public User getUserByUserName(String username){
		return userDao.getUser(username);
	}
}
