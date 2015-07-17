<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").click(function(){
			var serializeVal = $(":hidden").serialize();
			//alert(serializeVal);
			var href=this.href+"&"+serializeVal;
			window.location.href=href;
			return false;
		});
	});
	
</script>
</head>
<body>
	<input type="hidden" name="minPrice" value="${param.minPrice}" />
	<input type="hidden" name="maxPrice" value="${param.maxPrice}" />
	
	<center>
		<br><br>
		<h2>Title: ${book.title }</h2>
		<h2>Author: ${book.author }</h2>
		<h2>Publishing Date: ${book.publishingDate }</h2>
		<h2>Comment: ${book.remark }</h2>
		<br><br>
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">Continue Shopping</a>
	</center>
</body>
</html>