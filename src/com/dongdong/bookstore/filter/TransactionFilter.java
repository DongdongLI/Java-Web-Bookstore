package com.dongdong.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongdong.bookstore.db.JdbcUtils;
import com.dongdong.bookstore.web.ConnectionContext;

/**
 * Servlet Filter implementation class TransactionFilter
 */
@WebFilter("/*")
public class TransactionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransactionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String servletPath=((HttpServletRequest)request).getServletPath();
//		System.out.println(servletPath);
//		if(servletPath.contains("index")){
//			System.out.println("true");
//			((HttpServletRequest)request).getRequestDispatcher(((HttpServletRequest)request).getContextPath() + "/bookServlet?method=getBooks");
//			return;
//		}
		
		Connection conn=null;
		try{
		
		// get connection
			conn=JdbcUtils.getConnection();
		// start transacton
			conn.setAutoCommit(false);
		// utilize ThreadLocal to bind connection with current Thread
			ConnectionContext.getInstance().bind(conn);
		// send the request to target servlet
			chain.doFilter(request, response);
		// submit transaction
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			// rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			HttpServletResponse resp=(HttpServletResponse)response;
			HttpServletRequest req=(HttpServletRequest)request;
			resp.sendRedirect(req.getContextPath()+"/WEB-INF/pages/error.jsp");
		}
		finally {
			// unbind
			ConnectionContext.getInstance().remove();
			// close connection
			JdbcUtils.releaseConnection(conn);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
