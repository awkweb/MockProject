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

<form:form action="create-order-submit" method="post"
	modelAttribute="order" id="userform" cssClass="form-horizontal">
	<div class="form-group">
		<form:label path="symbol2" cssClass="col-sm-2 control-label">Equity Symbol: </form:label>
		<div class="col-sm-4 ">
			<form:select path="symbol2" id="symbolDrop" cssClass="form-control"
			OnChange="nameAutoFill(this)" name="symbol2" required="true">
			<form:option value="" selected="selected" disabled="disabled">Select Equity</form:option>
			<form:options items="${symbolList}" />
			</form:select>
		</div>
		
		<label class="col-sm-2 control-label">Security Name: </label>
		<div class="col-sm-4">
			<input class="form-control disabled" name="securityName" id="namefill" value="" disabled/>
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
			<form:select path="side" name="side" cssClass="form-control">
			<form:options items="${sideList}" />
			</form:select>
		</div>
	</div>

	<div class="form-group">
		<form:label path="totalQty" cssClass="col-sm-2 control-label">Quantity: </form:label>
		<div class="col-sm-4">
			<form:input type="number" min="1" step="1" path="totalQty"
				name="totalQty" cssClass="form-control" />
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
				path="stopPrice" name="stopPrice" value="0" readonly="true" cssClass="form-control" />
		</div>
		
		<form:label path="limitPrice" cssClass="col-sm-2 control-label">Limit Price: </form:label>
		<div class="col-sm-4">
			<form:input id="limitPrice" type="number" min="0" step="0.01"
				path="limitPrice" name="limitPrice" value="0" readonly="true" cssClass="form-control" />
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
			<form:textarea rows="4" path="notes" name="notes" cssClass="form-control"/>
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
</form:form>
