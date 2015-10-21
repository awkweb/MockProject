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

<form:form action="editdetails" method="post"
	modelAttribute="order" id="userform" cssClass="form-horizontal">
	<div class="form-group">
		<form:label path="symbol2" cssClass="col-sm-2 control-label">Equity Symbol: </form:label>
		<div class="col-sm-4 ">
			<form:input path="symbol2" cssClass="form-control"
				id="symbolDrop" name="symbol2"
				value="${passedOrder.getSecurity().getSymbol()}" readonly="true" />
		</div>

		<label class="col-sm-2 control-label">Security Name: </label>
		<div class="col-sm-4">
			<input class="form-control disabled" name="securityName" id="namefill"
									value="${passedOrder.getSecurity().getName()}" readonly/>
		</div>
	</div>
	
	<div class="form-group">
		<form:label path="portId2" cssClass="col-sm-2 control-label">Portfolio: </form:label>
		<div class="col-sm-4">
			<form:select path="portId2" name="portfolio" cssClass="form-control">
				<form:options items="${portfolioList}"></form:options>
			</form:select>
		</div>
		
		<form:label path="accType" cssClass="col-sm-2 control-label">Account Type: </form:label>
		<div class="col-sm-4">
			<form:select cssClass="form-control" path="accType" name="accountType">
				<form:options items="${accountTypeList}" />
			</form:select>
		</div>
	</div>

	<div class="form-group">
		<form:label path="side" cssClass="col-sm-2 control-label">Side: </form:label>
		<div class="col-sm-4">
			<form:input path="side" cssClass="form-control" name="side"
				value="${passedOrder.getSide()}" readonly="true" />
		</div>
	</div>

	<div class="form-group">
		<form:label path="totalQty" cssClass="col-sm-2 control-label">Quantity: </form:label>
		<div class="col-sm-4">
			<form:input type="number" min="1" step="1" path="totalQty"
				name="totalQty" cssClass="form-control" value="${passedOrder.getTotalQty()}"/>
		</div>
	</div>

	<div class="form-group">
		<form:label path="ordertype" cssClass="col-sm-2 control-label">Order Type: </form:label>
		<div class="col-sm-4">
			<form:select id="ordertype" cssClass="form-control" path="ordertype"
				name="ordertype">
				<form:options items="${orderTypeList}" />
			</form:select>
		</div>
	</div>
	
	<div class="form-group">
		<form:label path="stopPrice" cssClass="col-sm-2 control-label">Stop Price: </form:label>
		<div class="col-sm-4">
			<form:input id="stopPrice" type="number" min="0" step="0.01"
				path="stopPrice" name="stopPrice" value="${passedOrder.getStopPrice()}" readonly="true" cssClass="form-control" />
		</div>
		
		<form:label path="limitPrice" cssClass="col-sm-2 control-label">Limit Price: </form:label>
		<div class="col-sm-4">
			<form:input id="limitPrice" type="number" min="0" step="0.01"
				path="limitPrice" name="limitPrice" value="${passedOrder.getLimitPrice()}" readonly="true" cssClass="form-control" />
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label">Trader: </label>
		<div class="col-sm-4">
			<form:select path="traderId2" name="traderId2"
				cssClass="form-control">
				<form:options items="${traderId}"></form:options>
			</form:select>
		</div>
	</div>

	<div class="form-group">
		<form:label path="qualifiers" cssClass="col-sm-2 control-label">Qualifier: </form:label>
		<div class="col-sm-4">
			<form:select path="qualifiers" cssClass="form-control" name="qualifier">
				<form:options items="${qualifierTypeList}" />
			</form:select>
		</div>
	</div>

	<div class="form-group">
		<form:label path="notes" cssClass="col-sm-2 control-label">Notes: </form:label>
		<div class="col-sm-4">
			<form:textarea rows="4" path="notes" name="notes" cssClass="form-control" value="${passedOrder.getNotes()}"/>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-2">
			<button type="submit" class="btn btn-primary btn-block">Submit</button>
		</div>
		<div class="col-sm-2">
			<a class="btn btn-default btn-block" href="${pageContext.request.contextPath}/order-view" role="button">Cancel</a>
		</div>
	</div>
	
	<form:input id="orderTypeHidden" path="path" type="hidden"
	value="${passedOrder.getOrdertype()}" />
	<form:input id="qualifierTypeHidden" path="path" type="hidden"
		value="${passedOrder.getQualifiers()}" />
	<form:input id="traderTypeHidden" path="path" type="hidden"
		value="${passedOrder.getUser2().getUserId()}" />
	<form:input id="accountTypeHidden" path="path" type="hidden"
		value="${passedOrder.getAccType()}" />
	<form:input id="portfolioTypeHidden" path="path" type="hidden"
		value="${passedOrder.getPortfolio().getPortId()}" />
	<form:input id="orderIdHidden" path="orderId" type="hidden"
		value="${passedOrder.getOrderId()}" />
	<form:input id="statusHidden" path="status" type="hidden"
		value="${passedOrder.getStatus()}" />
	<form:input id="timeStamp" path="timestamp" type="hidden"
		value="${passedOrder.getTimestamp()}" />
</form:form>

<script type="text/javascript">
window.onload=loadFunc(); 
</script>
