<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="store" class="assignment.ItemModel" scope="session"></jsp:useBean>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Store Front</title>
</head>
<body>
<div class="container">
			<div class="page-header">
				<h2>
					Store Items
				</h2>
			</div>
			<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Action</th>
			</tr>

			<c:forEach items="${ items }" var="item">
				<tr>
					<c:choose>
						<c:when test="${item.quantity gt '0'}">
							<td>
								<a href="Details?id=${item.id}">${item.name}</a>
							</td>
							<td>
								${item.price}
							</td>
							<td>
								<!-- <div class="form-group">
  									<label><input type="text" class="form-control" name = "quantity" value ="1"></label>
								</div> -->
									 <form action = "ShoppingCart" method ="POST">
										<input type="text" name="quantity" value="1"/><br/>
										<input type = "hidden" name ="name" value = "${item.name}">
										<input type = "hidden" name ="id" value = "${item.id}">
										<input type = "hidden" name ="price" value = "${item.price}">
							</td>
							<td>
							<a href="Store"><button type="submit" class="btn btn-default">Add to cart</button></a>
							</form>
							</td>
								
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	<p>Number of items in your cart: <c:if test="${empty numberOfItems}">0</c:if>${numberOfItems}</p>
	<form action = "ShoppingCart" method ="GET"><button type="submit" class="btn btn-default">View My Cart</button></form>
	</div>
</body>
</html>