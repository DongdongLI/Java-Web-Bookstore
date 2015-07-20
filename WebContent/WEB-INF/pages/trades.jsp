<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<br><br>
		User: ${user.username}
		<br><br>
			<table>
			<tr>
				<td>
				<c:forEach items="${user.trades}" var="trade">
				<table cellpadding="10" cellspacing="0" border="1">
				<tr>
					<td colspan="3">Time: ${trade.tradeTime}</td>
				</tr>
				
				<c:forEach items="${trade.items}" var="item">
					<tr>
						<td>Title: ${item.book.title}</td>
						<td>Price: ${item.book.price}</td>
						<td>Quantity: ${item.quantity}</td>
						
					</tr>
					
				</c:forEach>
				</table>
				<br><br>
			</c:forEach>
			</td>
			</tr>
		</table>
	</center>
</body>
</html>