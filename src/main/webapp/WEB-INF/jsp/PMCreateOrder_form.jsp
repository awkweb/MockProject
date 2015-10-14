<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Order</title>
</head>
<body>
	<h2>PM Create Order</h2>

	<!-- 	Check to see if names need to match DB -->

	<table cellspacing="10" frame="box" style="width: 100%">

		<tr>
			<td style="padding: 3%;"><form:form action="orderdetails"
					method="post" modelAttribute="order">
					<div style="float: left; width: 50%">
						<table cellspacing="10" style="width: 100%" style="height: 100%">

							<tr>
								<td style="width: 30%"><form:label path="symbol">Equity Symbol: </form:label></td>
								<td style="width: 31%"><form:input path="symbol" name="symbol" /></td>
								<td align="left" style="width: 39%"><a href="#">Symbol Lookup</a></td>
							</tr>
							
							

						<%-- 	<tr>
								<td><form:label path="securityName">Security Name: </form:label></td>
								<td><form:input path="securityName" name="securityName" value="Security Name" /></td>
							</tr> --%>

							<tr>
								<td><form:label path="side">Side: </form:label></td>
								<td><form:select path="side" name="side">
										<form:options items="${sideList}" />
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="ordertype">Order Type: </form:label></td>
								<td><form:select path="ordertype" name="ordertype">
										<form:options items="${orderTypeList}" />
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="qualifier">Qualifier: </form:label></td>
								<td><form:select path="qualifier" name="qualifier">
										<form:options items="${qualifierTypeList}" />
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="traderId">Trader: </form:label></td>
								<td><form:select path="traderId" name="traderId">
										<form:option value="traderId">Trader 1</form:option>
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="accountType">Account Type: </form:label></td>
								<td><form:select path="accountType" name="accountType">
										<form:options items="${accountTypeList}" />
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="portId">Portfolio: </form:label></td>
								<td><form:select path="portId" name="portfolio">
										<form:option value="portId" selected="selected">Port1</form:option>
									</form:select></td>
							</tr>
						</table>
					</div>

					<div style="float: right; width: 50%">
						<table cellspacing="20" style="width: 80%" style="height:100%">
							<tr>
								<td style="width: 50%"><form:label path="totalQty">Quantity: </form:label></td>
								<td><form:input type="number" min="0" step="1" path="totalQty" name="totalQty" /></td>
							</tr>

							<tr>
								<td><form:label path="stopPrice">Stop Price: </form:label></td>
								<td><form:input type="number" min="0" step="0.01" path="stopPrice" name="stopPrice" /></td>
							</tr>

							<tr>
								<td><form:label path="limitPrice">Limit Price: </form:label></td>
								<td><form:input type="number" min="0" step="0.01" path="limitPrice" name="limitPrice" /></td>
							</tr>

							<tr>
								<td><form:label path="notes">Notes: </form:label></td>
								<td><form:input type="text" path="notes" name="notes"
										style="width:100; height:500;" /></td>
							</tr>

							<tr>
								<td><input type="submit" value="submit" /></td>
								<td></td>
							</tr>
						</table>
					</div>
				</form:form></td>
		</tr>
	</table>


</body>
</html>