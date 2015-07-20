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
	<c:if test="${requestScope.error!=null}">
		<font color="red">${requestScope.error}</font>
	</c:if>
	<center>
		<br><br>
		<h4>You have purchased ${sessionScope.ShoppingCart.bookNumber} books.</h4>
		<h4>$${sessionScope.ShoppingCart.totalMoney} in total.</h4>
		<form action="bookServlet?method=cash" method="post">
			<table cellspacing="10">
				<tr>
					<td>Name on Card</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Account</td>
					<td><input type="text" name="accountid" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Place the order" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>