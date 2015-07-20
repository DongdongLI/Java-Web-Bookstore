package com.dongdong.bookstore.service;

import java.util.Set;

import com.dongdong.bookstore.dao.impl.BookDAO;
import com.dongdong.bookstore.dao.impl.BookDaoImpl;
import com.dongdong.bookstore.dao.impl.TradeDAO;
import com.dongdong.bookstore.dao.impl.TradeDAOImpl;
import com.dongdong.bookstore.dao.impl.TradeItemDAO;
import com.dongdong.bookstore.dao.impl.TradeItemDAOImpl;
import com.dongdong.bookstore.dao.impl.UserDAO;
import com.dongdong.bookstore.dao.impl.UserDAOImpl;
import com.dongdong.bookstore.domain.Trade;
import com.dongdong.bookstore.domain.TradeItem;
import com.dongdong.bookstore.domain.User;

public class UserService {
	private UserDAO userDao=new UserDAOImpl();
	private TradeDAO tradeDAO=new TradeDAOImpl();
	private TradeItemDAO tradeItemDAO=new TradeItemDAOImpl();
	private BookDAO bookDAO=new BookDaoImpl();
	
	public User getUserByUserName(String username){
		return userDao.getUser(username);
	}
	
	public User getUserWithTrades(String username){
		User user=userDao.getUser(username);
		if(user==null){
			return null;
		}
		int userId=user.getUserId();
		Set<Trade> trades=tradeDAO.getTradesWithUserId(userId);
		if(trades!=null)
			for(Trade trade:trades){
				int tradeId=trade.getTradeId();
				Set<TradeItem> items=tradeItemDAO.getTradeItemsWithTradeId(tradeId);
				if(items!=null){
					for(TradeItem item:items){
						item.setBook(bookDAO.getBook(item.getBookId()));
					}
				}
				
				trade.setItems(items);
			}
		user.setTrades(trades);
		return user;
	}
}
