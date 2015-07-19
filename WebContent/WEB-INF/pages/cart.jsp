<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
var flag=false;
$(function(){
	$(".delete").click(function(){
		var $tr = $(this).parent().parent();
		var title = $.trim($tr.find("td:first").text());
		flag = confirm("Are you sure you want to delete" + title+"?");
		
		if(flag){
			return true;
		}
		
		return false;
		});
	
		$("a").each(function(){
			this.onclick = function(){
				var serializeVal = $(":hidden").serialize();
				var href = this.href ;
				//alert(this.href);
				//alert(href.indexOf("maxPrice")>=0);
				if(href.indexOf("maxPrice")<0)
					this.href=this.href + "&" + serializeVal;
				
			};
		});
		
		//change amount
		//1 get all the text, add onchange function, add confirm
		$(":text").change(function(){
			var quantityValue=$.trim(this.value);
			var flag = false;
			
			var reg = /^\d+$/g;
			var quantity = -1;
			if(reg.test(quantityValue)){
				quantity = parseInt(quantityValue);
				if(quantity >= 0){
					flag = true;
				}
			}
			
			
			if(!flag){alert("Invalid quantity");$(this).val($(this).attr("class")); return;}
				
			
			
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			
			if(quantity==0){
				var flag2=confirm("Do you want to delete "+title+"?")
				if(flag2){
					var href=$tr.find("td:last").find("a").attr("href");
					window.location.href=href;
					return;
				}
			}
			
			
			var flag=confirm("Do you want to change the amount of "+title+"?");
			if(!flag){
				$(this).val($(this).attr("class"));
				return;
			}
			//2 request address: bookServlet
			var url="bookServlet";
			var idVal=$.trim(this.name);
			var args={"method":"updateItemQuantity","id":idVal,"quantity":quantityValue,"time":new Date()};
			//3. params are method:updateItemQuantity, id:xx, quantity:val, time:new Date()
			$.post(url,args,function(data){
				var bookNumber=data.bookNumber;
				var totalMoney=data.totalMoney;
				//alert(bookNumber);
				//alert(totalMoney);
				$("#totalMoney").text("$"+totalMoney+" in total.");
				$("#totalNum").text("There are "+bookNumber+" books in total.");
			},"JSON");
			//4 return json (bookNumber, totalMoney)
		});
		
	})
</script>
</head>
<body>
	
	<input type="hidden" name="minPrice" value="${param.minPrice }"/>
	<input type="hidden" name="maxPrice" value="${param.maxPrice }"/>
	
	<center>
		<div id="totalNum">There are ${sessionScope.ShoppingCart.bookNumber } books in total.</div> <br><br>
		<table cellpadding="10">
			<tr>
				<td>Title</td>
				<td>Amount</td>
				<td>Price</td>
				<td>&nbsp;</td>
			</tr>
			
			<c:forEach items="${sessionScope.ShoppingCart.items}" var="item">
			<tr>
				<td>${item.book.title }</td>
				<!-- <td>${item.quantity }</td> -->
				<td>
					<input class="${item.quantity}" type="text" size="4" name="${item.book.id}" value="${item.quantity}"/>
				</td>
				<td>${item.book.price }</td>
				<td><a class="delete" href="bookServlet?method=remove&pagNo=${param.pageNo}&id=${item.book.id}">Delete</a></td>
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4" id="totalMoney">$${sessionScope.ShoppingCart.totalMoney} in total.</td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">Continue Shopping</a>
					&nbsp;
					<a href="bookServlet?method=clear">Clear Shopping cart</a>
					&nbsp;
					<a href="bookServlet?method=forwardPage&page=cash.jsp">Check out</a>
				</td>
			</tr>
		</table>
		
	</center>
</body>
</html>