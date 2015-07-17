package com.dongdong.bookstore.dao.impl;

import java.util.Collection;
import java.util.List;

import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCartItem;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;

public class BookDaoImpl extends BaseDao<Book> implements BookDAO{

	@Override
	public Book getBook(int id) {
		String sql="select * from mybooks where id=?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page<Book> page=new Page<>(cb.getPageNo());
		
		page.setTotalItemNum(getTotalBookNumber(cb));
		//make sure pageNo is valid
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		return page;
	}

	// number of records based on the criteria
	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql="select count(id) from mybooks where price >=? and price<= ?";
		return (long)getSingleVal(sql, cb.getMinPrice(),cb.getMaxPrice());
	}

	//MySQL uses limit to divide page 
	// limit ?,?: first ?,where to start, second ?, how many show in each page
	//get the list of books and ready to put in a "Page" object
	
	//start from which index, and how many to put in each page
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql="select * from mybooks where price >=? and price<= ? limit ?,?";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql="select storenumber from mybooks where id=?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		// TODO Auto-generated method stub
		
	}

	
	
}
