package com.dongdong.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.websocket.server.PathParam;

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
		String sql="update mybooks set salesamount=salesamount+?, storenumber=storenumber-? where id=?";
		List<ShoppingCartItem> list=new ArrayList<>(items);
		Object[][] params=null;
		params=new Object[items.size()][3];
		for(int i=0;i<items.size();i++){
			params[i][0]=list.get(i).getQuantity();
			params[i][1]=list.get(i).getQuantity();
			params[i][2]=list.get(i).getBook().getId();
			System.out.println(list.get(i).getBook().getTitle());
		}
		batch(sql, params);
	}

	
	
}
