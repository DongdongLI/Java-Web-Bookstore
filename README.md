# Java-Web-Bookstore

****This is a Java Web Online Bookstore Application

## workflow:


##Implementation detail:
Adapted MVC architectural pattern:
  Model: Plain Old Java Object
  
  Controller: Servlet
  
  View: JSP+EL+JSTL

##Techonology  
  Database: MySQL
  
  Datasource: c3p0
  
  JDBC tool: DBUtils
  
  Transaction solution: Filter+ThreadLocal
  
  Ajax solution: jQuery, JavaScript, gson
  
##tables in db

  account: accountId, balance
  
  myBooks: id, author, title, price, publishingDate, salesAmount, storeNumber, remark
  
  trade: tradeId, userId, tradeTime
  
  tradeItem: itemId, bookId, quantity, tradeId
  
  userinfo: userId, username, accountId

##web content
  Servlets: BookServlet, UserServlet
  
  JSPs: 
  
  Filters: EncodingFilter, TransactionFilter
