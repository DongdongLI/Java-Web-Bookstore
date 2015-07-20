package com.dongdong.bookstore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.dongdong.bookstore.dao.impl.AccountDAO;
import com.dongdong.bookstore.dao.impl.AccountDAOImpl;
import com.dongdong.bookstore.dao.impl.BookDAO;
import com.dongdong.bookstore.dao.impl.BookDaoImpl;
import com.dongdong.bookstore.dao.impl.TradeDAO;
import com.dongdong.bookstore.dao.impl.TradeDAOImpl;
import com.dongdong.bookstore.dao.impl.TradeItemDAO;
import com.dongdong.bookstore.dao.impl.TradeItemDAOImpl;
import com.dongdong.bookstore.dao.impl.UserDAO;
import com.dongdong.bookstore.dao.impl.UserDAOImpl;
import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCart;
import com.dongdong.bookstore.domain.ShoppingCartItem;
import com.dongdong.bookstore.domain.Trade;
import com.dongdong.bookstore.domain.TradeItem;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;

public class BookService {
	private BookDAO bookDAO=new BookDaoImpl();
	private AccountDAO accountDAO=new AccountDAOImpl();
	private TradeDAO tradeDAO=new TradeDAOImpl();
	private UserDAO userDAO=new UserDAOImpl();
	private TradeItemDAO tradeItemDAO=new TradeItemDAOImpl();
	
	
	public Page<Book> getPage(CriteriaBook cb){
		return bookDAO.getPage(cb);
	}

	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	public boolean addToCart(int id, ShoppingCart cart) {
		Book book=bookDAO.getBook(id);
		if(book!=null){
			cart.add(book);
			return true;
		}
		return false;
	}
	
	public void removeItemFromShoppingCart(ShoppingCart cart,int id){
		cart.removeItem(id);
	}
	
	public void clearShoppingCart(ShoppingCart cart){
		cart.clear();
	}

	public void cash(ShoppingCart shoppingCart, String username, String accountId) {
		//1 update mybook table
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		//2 update balance in account table
		accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
		//3 insert record to trade table
		Trade trade=new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDAO.getUser(username).getUserId());
		tradeDAO.insert(trade);
		//4 insert multiple ones to tradeItem
		Collection<TradeItem> items=new ArrayList<>();
		for(ShoppingCartItem item:shoppingCart.getItems()){
			TradeItem tradeItem=new TradeItem();
			tradeItem.setBookId(item.getBook().getId());
			tradeItem.setQuantity(item.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		//5 clear shopping cart
		shoppingCart.clear();
	}
}
