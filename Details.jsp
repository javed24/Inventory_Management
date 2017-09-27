<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Item details</title>
</head>
<body>
	
			<table border ="1">
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Details</th>
				<th>Quantity</th>
			</tr>

			<c:forEach items="${ items }" var="item">
				<tr>
							<td>
								${item.name}
							</td>
							<td>
								${item.price}
							</td>
							<td>
								${item.details}
							</td>
							<td>
								
									 <form action = "ShoppingCart" method ="post">
										<input type="text" name="quantity" value="1"/><br/>
										<input type = "hidden" name ="name" value = "${item.name}"/>
										<input type = "hidden" name ="id" value = "${item.id}"/>
										<input type = "hidden" name ="price" value = "${item.price}"/>
							</td>
							
							<td>
							<a href="Details?id=${item.id}"><button type="submit" class="btn btn-default">Add to cart</button></a>
							</form>
							</td>
				</tr>
			</c:forEach>
		</table>
			<p>Number of items in your cart: <c:if test="${empty numberOfItems}">0</c:if>${numberOfItems}</p><br/>
	<a href = "ShoppingCart">View my cart</a><br/>
	<a href = "Store">Go back</a>
</body>
</html>