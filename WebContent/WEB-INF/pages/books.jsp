<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="scripts/jquery-1.11.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#pageNo").change(function(){
			var val=$(this).val();
			val=$.trim(val);
			
			var flag=false;
			var reg=/^\d+$/g;
			var pagNo=0;
			
			if(reg.test(val)){
				pageNo=parseInt(val);
				if(pageNo>=1 && pageNo<=parseInt(${bookpage.totalPageNum}))
					flag=true;
			}
			if(!flag){
				alert("Invalid page number!");
				$(this).val("");
				return;
			}
			
			var href="bookServlet?method=getBooks&pageNo="+pageNo+"&"+$(":hidden").serialize();
			window.location.href=href;
		});
		
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
		<c:if test="${param.title!=null}">You have put <font color="red">${param.title}</font> into cart.</c:if><br><br>
		<c:if test="${!empty sessionScope.ShoppingCart.books}">
			You have ${sessionScope.ShoppingCart.bookNumber} items in your cart. <a href="bookServlet?method=toCartPage&pageNo=${bookpage.pageNo}">Procceed to cart</a>
		</c:if>
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			Price: <input type="text" size="4"	name="minPrice" />
			<input type="text" size="4"	name="maxPrice" />
			<input type="submit" value="Submit" />
		</form>
		
	<table cellpadding="10">
		<c:forEach items="${bookpage.list}" var="book">
			<tr>
				<td>
					<a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo}&id=${book.id}">${book.title}</a>
					<br>
					${book.author}
				</td>
				<td>${book.price}</td>
				<td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo}&id=${book.id}&title=${book.title}">Add to Cart</a></td>
			</tr>
		</c:forEach>
	</table>
		
	<br><br>
	${bookpage.totalPageNum} pages in Total.
	&nbsp;&nbsp;
	page ${bookpage.pageNo}
	&nbsp;&nbsp;
	<c:if test="${bookpage.hasPrev}">
		<a href="bookServlet?method=getBooks&pageNo=1">First</a>&nbsp;&nbsp;
		<a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPage}">Previous</a>
	</c:if>
	<c:if test="${bookpage.hasNext}">&nbsp;&nbsp;
		<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage}">Next</a>
		<a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNum}">Last</a>
	</c:if>
	&nbsp;&nbsp;
	Jump to Page <input type="text" size="4" id="pageNo" />		
	</center>
</body>
</html>