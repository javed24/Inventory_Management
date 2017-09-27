<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
	<p>Hello, admin!</p></br>
	<form action="Inventory" method="post">
		Item name: <input type="text" name="name" /><br/>
		Price: <input type="text" name="price" /><br/>
		Details: <input type="text" name="details" /><br/>
		Quantity: <input type="text" name="quantity" /><br/>
		<input type="submit" name="add" value="Add" />	
	</form>
	
	<table border ="1">
		<tr><th>Id</th><th>Name</th><th>Quantity</th><th>Price</th><th>Description</th><th>Action</th></tr>
		<c:forEach items = "${items}" var = "item">
			<tr>
				<td>${item.id}</td>
				<td><a href = "Details?id=${item.id}">${item.name}</a></td>
				<td>${item.quantity}</td>
				<td>${item.price}</td>
				<td>${item.details}</td>
				<td><a href = "RemoveItem?id=${item.id}">Remove</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="Store"> Go to Store</a>
	<a href="Inventory">Add new item</a>
</body>

</html>