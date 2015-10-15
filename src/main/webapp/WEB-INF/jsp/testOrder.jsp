<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<br> ${orderId}
	<br> ${orderSymbol}
	<br> ${orderSide}
	<br> ${type}

	<c:forEach var="List" items="${proposedblocks}">
		<div>
			<p>Block</p>
			<c:forEach var="order" items="${List}">
				<p>value="${order.symbol}"></p>

			</c:forEach>
		</div>
	</c:forEach>



</body>
</html>