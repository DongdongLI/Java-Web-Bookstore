package com.dongdong.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	private Map<Integer, ShoppingCartItem> books=new HashMap<>();
	// add a new book
	public void add(Book book){
		// check if this book is in the cart, if there is, then increase the amount
		ShoppingCartItem item=books.get(book.getId());
		if(item==null){
			item=new ShoppingCartItem(book);
			books.put(book.getId(), item);
		}else{
			item.increment();
		}
	}
	// has one particular book?
	public boolean hasBook(int id){
		return books.containsKey(id);
	}

	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}
	

	// get all shoppingCartItems 
	public Collection<ShoppingCartItem> getItems(){
		return books.values();
	}
	
	// how many bookd in total
	public int getBookNumber(){
		int total=0;
		for(ShoppingCartItem i:books.values()){
			total+=i.getQuantity();
		}
		return total;
	}
	
	// how much money intatal
	public float getTotalMoney(){
		float total=0;
		for(ShoppingCartItem i:books.values()){
			total+=i.getItemMoney();
		}
		return total;
	}
	
	public boolean isEmpty(){
		return books.isEmpty();
	}
	public void clear(){
		books.clear();
	}
	public void removeItem(Integer id){
		books.remove(id);
	}
	public void updateItemQuantity(int id,int quantity){
		ShoppingCartItem i=books.get(id);
		if(i!=null){
			i.setQuantity(quantity);
		}
	}
}
