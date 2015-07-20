package com.dongdong.bookstore.dao.impl;

import com.dongdong.bookstore.domain.User;

public class UserDAOImpl extends BaseDao<User> implements UserDAO{

	@Override
	public User getUser(String username) {
		String sql="select * from userinfo where username=?";
		return query(sql, username);
	}

}
