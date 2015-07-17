package com.dongdong.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dongdong.bookstore.domain.ShoppingCart;

// get the cart from session (1) if it doesnt exist, create one
public class BookStoreWebUtils {
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		HttpSession session=request.getSession();
		ShoppingCart cart=(ShoppingCart)session.getAttribute("ShoppingCart");
		if(cart==null){
			cart=new ShoppingCart();
			session.setAttribute("ShoppingCart", cart);
		}
		return cart;
	}
}
