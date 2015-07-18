package com.dongdong.bookstore.service;

import com.dongdong.bookstore.dao.impl.BookDAO;
import com.dongdong.bookstore.dao.impl.BookDaoImpl;
import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCart;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;

public class BookService {
	private BookDAO bookDAO=new BookDaoImpl();
	
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
}
