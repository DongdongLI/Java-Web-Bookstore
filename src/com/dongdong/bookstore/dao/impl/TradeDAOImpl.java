package com.dongdong.bookstore.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.dongdong.bookstore.domain.Trade;

public class TradeDAOImpl extends BaseDao<Trade> implements TradeDAO {

	@Override
	public void insert(Trade trade) {
		String sql="Insert into trade (userid,tradetime) values (?,?)";
		long tradeid=insert(sql, trade.getUserId(),trade.getTradeTime());
		trade.setTradeId((int)tradeid);
	}

	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		String sql="select * from trade where userId=? order by tradeTime desc";
		return new LinkedHashSet(queryForList(sql, userId)); // it is a set with order
	}
	
}
