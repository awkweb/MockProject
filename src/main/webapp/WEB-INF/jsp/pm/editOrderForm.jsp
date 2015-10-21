<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#ordertype').change(function() {
			if (this.value == "Market") {
				$('#stopPrice').prop('readonly', true);
				$('#stopPrice').css({"background-color" : "#EBEBE4"});

				$('#stopPrice').val("0");
				$('#limitPrice').prop('readonly', true);
				$('#limitPrice').css({"background-color" : "#EBEBE4"});
				$('#limitPrice').val("0");
			} else if (this.value == "Limit") {
				$('#stopPrice').prop('readonly', true);
				$('#stopPrice').css({"background-color" : "#EBEBE4"});
				$('#stopPrice').val("0");
				
				$('#limitPrice').prop('readonly', false);
				$('#limitPrice').css({"background-color" : "white"});
			} else if (this.value == "Stop") {
				$('#stopPrice').prop('readonly', false);
				$('#stopPrice').css({"background-color" : "white"});
				
				$('#limitPrice').prop('readonly', true);
				$('#limitPrice').css({"background-color" : "#EBEBE4"});
				$('#limitPrice').val("0");
			} else {
				$('#stopPrice').prop('readonly', false);
				$('#stopPrice').css({"background-color" : "white"});
				$('#limitPrice').prop('readonly', false);
				$('#limitPrice').css({"background-color" : "white"});
			}
		}).change();
	});
</script>
<script type="text/javascript">
function loadFunc(){
	var x = document.getElementById("orderTypeHidden").value;
	document.getElementById("ordertype").value = x;
	var y = document.getElementById("qualifierTypeHidden").value;
	document.getElementById("qualifiers").value = y;
	var z = document.getElementById("traderTypeHidden").value;
	document.getElementById("trader").value = z;
	var a = document.getElementById("accountTypeHidden").value;
	document.getElementById("account").value = a;
	var b = document.getElementById("portfolioTypeHidden").value;
	document.getElementById("portfolio").value = b;
	document.getElementById("quantity").value = Math.abs(document.getElementById("quantity").value);
}
</script>


	<table cellspacing="10" frame="box" style="width: 100%">

		<tr>
			<td style="padding: 3%;"><form:form action="editdetails"
					method="post" modelAttribute="order" id="userform">
					<div style="float: left; width: 50%">
						<table cellspacing="10" style="width: 100%" style="height: 100%">

							<tr>
								<td style="width: 30%"><form:label path="symbol2">Equity Symbol: </form:label></td>
								<td><form:input path="symbol2"
										style="background-color: #EBEBE4; border: 1px solid #ABADB3; padding: 2px 1px;"
										id="symbolDrop" name="symbol2" value="${passedOrder.getSecurity().getSymbol()}"
										readonly="true" /></td>
								<td align="left" style="width: 39%"></td>
							</tr>




							<tr>
								<td><label>Security Name: </label></td>
								<td><input name="securityName" id="namefill"
									value="${passedOrder.getSecurity().getName()}" readonly
									style="background-color: #EBEBE4; border: 1px solid #ABADB3; padding: 2px 1px;" /></td>
							</tr>

							<tr>
								<td><form:label path="side">Side: </form:label></td>
								<td><form:input path="side" name="side" readonly="true"
										value="${passedOrder.getSide()}"
										style="background-color: #EBEBE4; border: 1px solid #ABADB3; padding: 2px 1px;" />
								</td>
							</tr>

							<tr>
								<td><form:label path="ordertype">Order Type: </form:label></td>
								<td><form:select style="width: 95%" id="ordertype" path="ordertype"
										name="ordertype">
										<form:options items="${orderTypeList}" value=""/>
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="qualifiers">Qualifier: </form:label></td>
								<td><form:select style="width: 95%" id="qualifiers" path="qualifiers" name="qualifier">
										<form:options items="${qualifierTypeList}" />
									</form:select></td>
							</tr>

							<tr>
								<td><label>Trader: </label></td>
								<td><form:select style="width: 95%" id="trader" path="traderId2" name="traderId2">
										<form:options items="${traderId}"></form:options>
									</form:select></td>
							</tr>

							<tr>
								<td><form:label path="accType">Account Type: </form:label></td>
								<td><form:select style="width: 95%" id="account" path="accType" name="accountType">
										<form:options items="${accountTypeList}" />
									</form:select></td>
							</tr>

							<%-- <tr>
								<td><form:label path="portId2">Portfolio: </form:label></td>
								<td><form:select style="width: 95%" id="portfolio" path="portId2" name="portfolio">
										<form:options items="${portfolioList}"></form:options>
									</form:select></td>
							</tr> --%>
						</table>
					</div>

					<div style="float: right; width: 50%">
						<table cellspacing="20" style="width: 80%" style="height:100%">
							<tr>
								<td style="width: 50%"><form:label path="totalQty">Quantity: </form:label></td>
								<td><form:input type="number" min="1" step="1"
										value="${passedOrder.getTotalQty()}" path="totalQty" name="totalQty" /></td>
							</tr>

							<tr>
								<td><form:label path="stopPrice">Stop Price: </form:label></td>
								<td><form:input id="stopPrice" type="number" min="0"
										step="0.01" path="stopPrice" name="stopPrice"
										value="${passedOrder.getStopPrice()}" /></td>
							</tr>

							<tr>
								<td><form:label path="limitPrice">Limit Price: </form:label></td>
								<td><form:input id="limitPrice" type="number" min="0"
										step="0.01" path="limitPrice" name="limitPrice"
										value="${passedOrder.getLimitPrice()}" /></td>
							</tr>

							<tr>
								<td><form:label path="notes">Notes: </form:label></td>
							</tr>
							<tr>
								<td></td>
								<td><form:input path="notes" name="notes"
										style="height:250%;" value="${passedOrder.getNotes()}" wrap="true"/></td>
							</tr>
						
							<tr>
								<td><form:input id="orderTypeHidden" path="path" type="hidden" value="${passedOrder.getOrdertype()}"/>
								<form:input id="qualifierTypeHidden" path="path" type="hidden" value="${passedOrder.getQualifiers()}"/>
								<form:input id="traderTypeHidden" path="path" type="hidden" value="${passedOrder.getUser2().getUserId()}"/>
								<form:input id="accountTypeHidden" path="path" type="hidden" value="${passedOrder.getAccType()}"/>
								<form:input id="portfolioTypeHidden" path="path" type="hidden" value="${passedOrder.getPortfolio().getPortId()}"/>
								<form:input id="orderIdHidden" path="orderId" type="hidden" value="${passedOrder.getOrderId()}"/>
								<form:input id="statusHidden" path="status" type="hidden" value="${passedOrder.getStatus()}"/>
								<form:input id="timeStamp" path="timestamp" type="hidden" value="${passedOrder.getTimestamp()}"/></td>
							</tr>
						</table>
						<br><br>
						<p style="text-align: center;"><input type="submit" value="Save"/></p>
					</div>
				</form:form></td>
		</tr>
	</table>

<script type="text/javascript">
window.onload=loadFunc(); 
</script>
