package com.dongdong.bookstore.dao.impl;

import java.util.Set;

import com.dongdong.bookstore.domain.Trade;


public interface TradeDAO {

	public abstract void insert(Trade trade);

	public abstract Set<Trade> getTradesWithUserId(Integer userId);

}