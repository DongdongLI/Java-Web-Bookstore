package com.dongdong.bookstore.dao.impl;

import com.dongdong.bookstore.domain.Account;

public interface AccountDAO {

	
	public abstract Account get(Integer accountId);

	//after checking out
	public abstract void updateBalance(Integer accountId, float amount);

}