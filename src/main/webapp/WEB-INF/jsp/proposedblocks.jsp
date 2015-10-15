<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<c:forEach var="pb" items="${proposedblocks}">
		<tr>
			<p>BLOCK</p>
			<td>"${pb.symbol}"</td>
			<td>"${pb.side}"</td>
			<td>"${pb.totalQty}"</td>
		</tr>
		<c:forEach var="order" items="${pb.orders}">
			<table style="width: 100%">
				<tr >
					<td>"${order.symbol}"</td>
					<td>"${order.side}"</td>
					<td>"${order.totalQty}"</td>
				</tr>
			</table>
		</c:forEach>

	</c:forEach>



</body>
</html>