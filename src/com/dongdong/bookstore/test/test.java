package com.dongdong.bookstore.test;


import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.dongdong.bookstore.dao.impl.BaseDao;
import com.dongdong.bookstore.dao.impl.BookDaoImpl;
import com.dongdong.bookstore.domain.Book;
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
}
