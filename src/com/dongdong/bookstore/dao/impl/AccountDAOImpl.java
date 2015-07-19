package com.dongdong.bookstore.dao.impl;

import java.sql.Connection;

import com.dongdong.bookstore.db.JdbcUtils;
import com.dongdong.bookstore.domain.Account;

public class AccountDAOImpl extends BaseDao<Account> implements AccountDAO{

	@Override
	public Account get(Integer accountId) {
			String sql="select accountId, balance from account where accountId=?";
			return query(sql, accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql="update account set balance=balance-? where accountId=?";
		update(sql,amount,accountId);
	}
	
}
