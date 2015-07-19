package com.dongdong.bookstore.dao.impl;

import java.util.Collection;
import java.util.List;

import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCartItem;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;


public interface BookDAO {

	//get book based on id
	public abstract Book getBook(int id);

	//
	public abstract Page<Book> getPage(CriteriaBook cb);

	// get the number of book based on the criteria
	public abstract long getTotalBookNumber(CriteriaBook cb);

	
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);

	
	public abstract int getStoreNumber(Integer id);

	//change book's storeNumber
	//change book's saleNumber
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}