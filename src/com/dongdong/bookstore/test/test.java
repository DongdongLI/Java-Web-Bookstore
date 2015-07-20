package com.dongdong.bookstore.test;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.dongdong.bookstore.dao.impl.AccountDAO;
import com.dongdong.bookstore.dao.impl.AccountDAOImpl;
import com.dongdong.bookstore.dao.impl.BaseDao;
import com.dongdong.bookstore.dao.impl.BookDaoImpl;
import com.dongdong.bookstore.dao.impl.TradeDAO;
import com.dongdong.bookstore.dao.impl.TradeDAOImpl;
import com.dongdong.bookstore.dao.impl.TradeItemDAO;
import com.dongdong.bookstore.dao.impl.TradeItemDAOImpl;
import com.dongdong.bookstore.dao.impl.UserDAOImpl;
import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCartItem;
import com.dongdong.bookstore.domain.Trade;
import com.dongdong.bookstore.domain.TradeItem;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;

public class test {
	// this file contains the test of BookDaoImpl and BaseDao
	private BookDaoImpl bookDaoImpl=new BookDaoImpl();
	private BaseDao baseDao=new BaseDao();
	
	@Test
	public void testInsert(){//pass
		String sql="insert into trade(userid,tradetime) values(?,?)";
		long id=baseDao.insert(sql, 1,new Date(new java.util.Date().getTime()));
		System.out.println(id);
	}
	
	@Test
	public void testUpdate()
	{
		String sql= "update mybooks set salesamount=? where id=?";
		bookDaoImpl.update(sql, 10,4);
	}
	
	@Test
	public void testQuery ()
	{
		String sql= "select * from mybooks where id=?";
		Book book=bookDaoImpl.query(sql,4);
		System.out.println(book);
	}
	
	@Test
	public void testQueryForList ()
	{
		String sql= "select * from mybooks where id<?";
		List<Book> books=bookDaoImpl.queryForList(sql,4);
		System.out.println(books);
	}
	
	@Test
	public void testGetSingleVal ()
	{
		String sql= "select count(id) from mybooks";
		long count=bookDaoImpl.getSingleVal(sql);
		System.out.println(count);
	}
	@Test
	public void testBatch(){
		String sql="update mybooks set salesamount=?, storeNumber=? where id=?";
		bookDaoImpl.batch(sql, new Object[]{1,1,1},new Object[]{2,2,2},new Object[]{3,3,3});
	}
	
	@Test
	public void testGetBook(){
		Book book=bookDaoImpl.getBook(5);
		System.out.println(book);
	}
	
	@Test
	public void testGetPage(){
		CriteriaBook cb=new CriteriaBook(50, 60, 3);
		Page<Book> page=bookDaoImpl.getPage(cb);
		System.out.println(page.getPageNo());
		System.out.println(page.getTotalPageNum());
		System.out.println(page.getList());
		System.out.println(page.getPrevPage());
		System.out.println(page.getNextPage());
		
	}
	
	@Test
	public void testGetStoreNumber(){
		int storeNumber=bookDaoImpl.getStoreNumber(5);
		System.out.println(storeNumber);
	}
	
	AccountDAO accountDao=new AccountDAOImpl();
	@Test
	public void testUpdateAccount(){
		int accountId=1;
		System.out.println(accountDao.get(accountId));
	}
	@Test
	public void testUpdateBalance(){
		accountDao.updateBalance(1, 50);
	}
	@Test
	public void testGetUser(){
		System.out.println(new UserDAOImpl().getUser("AAA"));
	}
	@Test
	public void testBatchUpdateStore(){
		Collection<ShoppingCartItem> items=new ArrayList<>();
		
		Book book=bookDaoImpl.getBook(1);
		ShoppingCartItem i=new ShoppingCartItem(book);
		i.setQuantity(1);
		items.add(i);
		
		book=bookDaoImpl.getBook(2);
		i=new ShoppingCartItem(book);
		i.setQuantity(1);
		items.add(i);
		
		 book=bookDaoImpl.getBook(3);
		i=new ShoppingCartItem(book);
		i.setQuantity(1);
		items.add(i);
		bookDaoImpl.batchUpdateStoreNumberAndSalesAmount(items);
	}
	@Test
	public void testTradeInsert(){
		Trade trade=new Trade();
		trade.setUserId(3);
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		new TradeDAOImpl().insert(trade);
	}
	@Test
	public void testGetSetofTrade(){
		Set<Trade> trades=new TradeDAOImpl().getTradesWithUserId(1);
		System.out.println(trades);
	}
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	@Test
	public void testBatchSaveTradeItemDaoImpl(){
		Collection<TradeItem> items = new ArrayList<>();
		
		items.add(new TradeItem(null,4, 7, 12));
		items.add(new TradeItem(null, 5, 8, 13));
		
		tradeItemDAO.batchSave(items);
	}
	@Test
	public void testGetTradeItemWithTradeId(){
		Set<TradeItem> items=tradeItemDAO.getTradeItemsWithTradeId(16);
		System.out.println(items);
	}
}
