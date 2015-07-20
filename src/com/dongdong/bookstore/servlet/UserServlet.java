package com.dongdong.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongdong.bookstore.domain.User;
import com.dongdong.bookstore.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService=new UserService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		
		User user=userService.getUserWithTrades(username);
		if(user==null)
		{
			System.out.println("it is null");
			request.getRequestDispatcher(request.getServletPath()+"/WEB-INF/pages/error.jsp");
			return;
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);
	}

}
