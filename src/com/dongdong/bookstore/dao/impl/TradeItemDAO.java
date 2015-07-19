package com.dongdong.bookstore.dao.impl;

import java.util.Collection;
import java.util.Set;


public interface TradeItemDAO {

	
	public abstract void batchSave(Collection<TradeItem> items);

	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);

}

