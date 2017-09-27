<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Transaction History</title>
</head>
<body>
<div class="container">
			<div class="page-header">
				<h2>All Transactions</h2>
			</div>
					
				<table class="table table-bordered table-striped table-hover">
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Price</th>
				</tr>

			<c:forEach items="${transactions}" var="transaction">
				<tr>
					
					<td>${transaction.name}</td>
					<td>${transaction.quantity}</td>
					<td>${transaction.price}</td>
					
				</tr>
			</c:forEach>
		</table>
		<p>Total transaction: $ ${allTotalObj}</p>
	
	</div>
</body>
</html>