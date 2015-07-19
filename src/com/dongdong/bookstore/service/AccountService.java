package com.dongdong.bookstore.service;

import com.dongdong.bookstore.dao.impl.AccountDAO;
import com.dongdong.bookstore.dao.impl.AccountDAOImpl;
import com.dongdong.bookstore.domain.Account;

public class AccountService {
	AccountDAO accountDAO=new AccountDAOImpl();
	
	public Account getAccount(int accountId) 
	{
		return accountDAO.get(accountId);
	}
}
