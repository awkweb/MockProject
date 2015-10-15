<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>


<html>
<body>
<h2>Welcome to SHOPNOW !!!!!!!!</h2>

<form:form action = "editblock" method = "post" modelAttribute="order">
<table>
<tr>
<td> <form:label path="limitPrice">limitPrice :  </form:label>
  <form:input  path="limitPrice"  name="limitPrice" />
  </td>
</tr>
<tr>
<td> <form:label path="stopPrice"> stopPrice : </form:label>
 <form:input  path="stopPrice" name="stopPrice" />
</td>
</tr>
<tr>
<td>
<input type="submit" value="edit"/>
</td>
</tr>
</table>
</form:form>

</body>
</html>
