<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/static/js/pmview.js"></script> --%>
<script type="text/javascript">
	 function nameAutoFill(x) {
		var symbol = document.getElementById("symbolDrop").value;
		x.form.securityName.value = symbol;
	} 

	$(document).ready(function() {
		$('#ordertype').change(function() {
			if (this.value == "Market") {
				$('#stopPrice').prop('readonly', true);
				$('#stopPrice').css({"background-color": "#EBEBE4"});
				$('#stopPrice').val("0");
				$('#limitPrice').prop('readonly', true);
				$('#limitPrice').css({"background-color": "#EBEBE4"});
				$('#limitPrice').val("0");
			} else if (this.value == "Limit") {
				$('#stopPrice').prop('readonly', true);
				$('#stopPrice').css({"background-color": "#EBEBE4"});
				$('#stopPrice').val("0");
				$('#limitPrice').prop('readonly', false);
				$('#limitPrice').css({"background-color": "white"});
				$('#limitPrice').val("0");
			} else if (this.value == "Stop") {
				$('#stopPrice').prop('readonly', false);
				$('#stopPrice').css({"background-color": "white"});
				$('#stopPrice').val("0");
				$('#limitPrice').prop('readonly', true);
				$('#limitPrice').css({"background-color": "#EBEBE4"});
				$('#limitPrice').val("0");
			} else {
				$('#stopPrice').prop('readonly', false);
				$('#stopPrice').css({"background-color": "white"});
				$('#limitPrice').prop('readonly', false);
				$('#limitPrice').css({"background-color": "white"});
			}
		}).change();
	});
</script>


<script type="text/javascript">
function searchSel() 
{
  var input = document.getElementById('searchtxt').value.toLowerCase(),
      len = input.length,
      output = document.getElementById('symbolDrop').options;
  for(var i=0; i<output.length; i++)
    if (output[i].text.toLowerCase().slice(0, len) == input)
      output[i].selected = true;
  if (input == '')
    output[0].selected = true;
}
</script>


<table cellspacing="10" frame="box" style="width: 100%">

	<tr>
		<td style="padding: 3%;"><form:form action="orderdetails"
				method="post" modelAttribute="order" id="userform">
				<div style="float: left; width: 50%">
					<table cellspacing="10" style="width: 100%" style="height: 100%">

						<tr>
							<td style="width: 30%"><form:label path="symbol2">Equity Symbol: </form:label></td>
							<td><form:select style="width: 95%" path="symbol2"
									id="symbolDrop" OnChange="nameAutoFill(this)" name="symbol2"
									required="true">
									<form:option value="" selected="selected" disabled="disabled">Select Equity</form:option>

									<form:options items="${symbolList}" />
								</form:select></td>
							<td align="left" style="width: 39%">Search:<input
								style="width: 40%" onkeyup="searchSel()" id="searchtxt" /></td>
						</tr>


						<tr>
							<td><label>Security Name: </label></td>
							<td><input name="securityName" id="namefill" value=""
								readonly
								style="background-color: #EBEBE4; border: 1px solid #ABADB3; padding: 2px 1px;" /></td>
						</tr>

						<tr>
							<td><form:label path="side">Side: </form:label></td>
							<td><form:select style="width: 95%" path="side" name="side">
									<form:options items="${sideList}" />
								</form:select></td>
						</tr>

						<tr>
							<td><form:label path="ordertype">Order Type: </form:label></td>
							<td><form:select style="width: 95%" id="ordertype"
									path="ordertype" name="ordertype">
									<form:options items="${orderTypeList}" />
								</form:select></td>
						</tr>

						<tr>
							<td><form:label path="qualifiers">Qualifier: </form:label></td>
							<td><form:select style="width: 95%" path="qualifiers"
									name="qualifier">
									<form:options items="${qualifierTypeList}" />
								</form:select></td>
						</tr>

						<tr>
							<td><label>Trader: </label></td>
							<td><form:select style="width: 95%" path="traderId2"
									name="traderId2">
									<form:options items="${traderId}"></form:options>
								</form:select></td>
						</tr>

						<tr>
							<td><form:label path="accType">Account Type: </form:label></td>
							<td><form:select style="width: 95%" path="accType"
									name="accountType">
									<form:options items="${accountTypeList}" />
								</form:select></td>
						</tr>

						<tr>
							<td><form:label path="portId2">Portfolio: </form:label></td>
							<td><form:select style="width: 95%" path="portId2"
									name="portfolio">
									<form:options items="${portfolioList}"></form:options>
								</form:select></td>
						</tr>
					</table>
				</div>

				<div style="float: right; width: 50%">
					<table cellspacing="20" style="width: 80%" style="height:100%">
						<tr>
							<td style="width: 50%"><form:label path="totalQty">Quantity: </form:label></td>
							<td><form:input type="number" min="1" step="1"
									path="totalQty" name="totalQty" /></td>
						</tr>

						<tr>
							<td><form:label path="stopPrice">Stop Price: </form:label></td>
							<td><form:input id="stopPrice" type="number" min="0"
									step="0.01" path="stopPrice" name="stopPrice" value="0"
									readonly="true" /></td>
						</tr>

						<tr>
							<td><form:label path="limitPrice">Limit Price: </form:label></td>
							<td><form:input id="limitPrice" type="number" min="0"
									step="0.01" path="limitPrice" name="limitPrice" value="0"
									readonly="true" /></td>
						</tr>

						<tr>
							<td><form:label path="notes">Notes: </form:label></td>
						</tr>
						<tr>
							<td></td>
							<td><form:input type="textarea" path="notes" name="notes"
									style="height:250%;" /></td>
						</tr>
					</table>
					<br>
					<br>
					<p style="text-align: center;">
						<input type="submit" value="Save" />
					</p>
				</div>
			</form:form></td>
	</tr>
</table>
