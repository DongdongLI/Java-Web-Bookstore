package com.dongdong.bookstore.service;

import com.dongdong.bookstore.dao.impl.BookDAO;
import com.dongdong.bookstore.dao.impl.BookDaoImpl;
import com.dongdong.bookstore.domain.Book;
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
}
