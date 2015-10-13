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
	
	<form:form action = "orderdetails" method = "post" modelAttribute="order">
		<form:label path="symbol">Equity Symbol: </form:label>
  		<form:input  path="symbol"  name="symbol" />
  		
  		<form:label path="side">Side: </form:label>
  		<form:select path="side"  name="side" >
  			<form:options items = "${sideList}"/>
  		</form:select>
  		
  		<form:label path="ordertype">Order Type: </form:label>
  		<form:select path="ordertype"  name="ordertype" >
  			<form:options items = "${orderTypeList}"/>
  			<form:option value="Market" selected="selected">Market</form:option>
  			
  			<form:option value="Limit">Limit</form:option>
  			
  			<form:option value="StopLimit">Stop Limit</form:option>
  			
  			<form:option value="Stop">Stop</form:option>
  		</form:select>
  		
  		<form:label path="qualifier">Qualifier: </form:label>	
		<form:select path="qualifier"  name="qualifier" >
  			<form:option value="DayOrder" selected="selected">Day Order</form:option>
  			<form:option value="GTC">GTC</form:option>
  		</form:select>
  		
  		Traders will need to be autofilled from DB
  		<form:label path="traderId">Trader: </form:label>
  		<form:select path="traderId"  name="traderId" >
  			<form:option value="traderId">Trader 1</form:option>
  		</form:select>
  		
  		<form:label path="accountType">Account Type: </form:label>	
		<form:select path="accountType"  name="accountTyp" >
  			<form:option value="Cash" selected="selected">Cash</form:option>
  			<form:option value="Margin">Margin</form:option>
  		</form:select>
  		
  		Portolios will need to be autofilled from DB
  		<form:label path="portId">Portfolio: </form:label>
  		<form:select path="portId"  name="portfolio" >
  			<form:option value="portId" selected="selected">Port1</form:option>
  		</form:select>
  		
  		<form:label path="totalQty">Quantity: </form:label>
  		<form:input  path="totalQty"  name="totalQty" />
  		
  		<form:label path="stopPrice">Stop Price: </form:label>
  		<form:input  path="stopPrice"  name="stopPrice" />
  		
  		<form:label path="limitPrice">Limit Price: </form:label>
  		<form:input  path="limitPrice"  name="limitPrice" />
  		
  		<form:label path="notes">Notes: </form:label>
  		<form:input type="text" path="notes"  name="notes" style="width:160px; height:90;" />
  		
  		<input type="submit" value="submit"/>
	</form:form>
</body>
</html>