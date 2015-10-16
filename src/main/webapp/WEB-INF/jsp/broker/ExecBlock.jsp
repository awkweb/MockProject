<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<%-- <ul>
			<c:forEach var="Pmpos" items="${portfoliolist}">
				<li><c:out value="${Pmpos}" /></li>
			</c:forEach>
		</ul>
		 --%>
		<table border="2" cellpadding="10" width="100%" height ="100" align="top">
		<c:forEach items="${blocklist}" var="pmpos">
        <tr>
            <td width="25%"><c:out value="${pmpos.getSymbol()}"/></td>
               
            
        </tr>
    </c:forEach>
</table>
<br>

</body>
</html>