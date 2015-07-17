package com.dongdong.bookstore.domain;


public class ShoppingCartItem {
	// what is the book
	private Book book;
	// how many
	private int quantity;
	
	public ShoppingCartItem(Book book) {
		this.book = book;
		this.quantity = 1;
	}
	
	public Book getBook() {
		return book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// how much in total for this product
	public float getItemMoney(){
		return book.getPrice() * quantity;
	}
	
	
	public void increment(){
		quantity++;
	}
	
	
}
