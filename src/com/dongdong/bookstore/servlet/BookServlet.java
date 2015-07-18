package com.dongdong.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongdong.bookstore.domain.Book;
import com.dongdong.bookstore.domain.ShoppingCart;
import com.dongdong.bookstore.service.BookService;
import com.dongdong.bookstore.web.BookStoreWebUtils;
import com.dongdong.bookstore.web.CriteriaBook;
import com.dongdong.bookstore.web.Page;
import com.google.gson.Gson;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService=new BookService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName=request.getParameter("method");
		try {
			Method method=getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr=request.getParameter("pageNo");
		String minPriceStr=request.getParameter("minPrice");
		String maxPriceStr=request.getParameter("maxPrice");
		
		int pageNo=1;
		int minPrice=0;
		int maxPrice=Integer.MAX_VALUE;
		
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			
		}
		try {
			minPrice=Integer.parseInt(minPriceStr);
		} catch (Exception e) {
			
		}
		try {
			maxPrice=Integer.parseInt(maxPriceStr);
		} catch (Exception e) {
			
		}
		CriteriaBook cb=new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page =bookService.getPage(cb);
		
		request.setAttribute("bookpage", page);
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
	}
	public void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr=request.getParameter("id");
		int id=-1;
		Book book=null;
		try {
			id=Integer.parseInt(idStr);
		} catch (Exception e) {
			
		}
		if(id>0)
			book=bookService.getBook(id);
		
		if(book==null){
			response.sendRedirect(request.getContextPath()+"/error-1.jsp");
			return;
		}
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}
	public void  addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0 get book id
		String idStr=request.getParameter("id");
		int id=-1;
		boolean flag=false;
		
		try{
			id=Integer.parseInt(idStr);
		}catch(Exception e){
			
		}
		if(id>0){
			//1 get the cart
			ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
			//2 BookService.addToCart
			flag=bookService.addToCart(id,cart); 
		}
		if(flag){
			//4 getBooks()
			getBooks(request, response);
			return;
		}
		else{
			response.sendRedirect(request.getContextPath()+"/error-1.jsp");
		}
		
	}
	
	public void  toCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idStr=request.getParameter("id");
		int id=-1;
		
		try {
			id=Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		bookService.removeItemFromShoppingCart(cart, id);
		if(cart.isEmpty()){
			request.getRequestDispatcher("/WEB-INF/pages/empty.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
		}
		
		
	}
	
	public void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(cart);
		
		request.getRequestDispatcher("/WEB-INF/pages/empty.jsp").forward(request, response);
	}
	
	public void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String idStr=request.getParameter("id");
		String quantityStr=request.getParameter("quantity");
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		
		int id=-1;
		int quantity=-1;
		
		try {
			id=Integer.parseInt(idStr);
			quantity=Integer.parseInt(quantityStr);
		} catch (Exception e) {
		}
		if(id>0 && quantity>0)
			cart.updateItemQuantity(id, quantity);
		
		Map<String, Integer> result=new HashMap<>();
		result.put("bookNumber", cart.getBookNumber());
		result.put("totalMoney", (int)cart.getTotalMoney());
		Gson gson=new Gson();
		String jsonStr=gson.toJson(result);
		response.setContentType("text/javascript");
		response.getWriter().print(jsonStr);
	}
}
