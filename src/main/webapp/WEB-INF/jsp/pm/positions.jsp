<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="nav nav-tabs" role="tablist">
	<div>


		<form:form modelAttribute="myform" action="result" method="get">
			<form:select path="nameOfInstitution">
				<form:option value="NONE"> --SELECT--</form:option>
				<form:options items="${pnames}"></form:options>
			</form:select>

		</form:form>


	</div>

</ul>
<table class="table table-bordered table-hover table-responsive">
	<tr>

		<th>Portfolio Name</th>
		<th>Security</th>
		<th>Total Quantity</th>
		<th>Average Price</th>



	</tr>
	<c:forEach items="${portfoliolist}" var="pmpos">
		<tr>

			<td><c:out value="${pmpos.getPortfolioname()}" /></td>
			<td><c:out value="${pmpos.getSecurity()}" /></td>
			<td><c:out value="${pmpos.getQty()}" /></td>
			<td><c:out value="${pmpos.getAvgprice()}" /></td>

		</tr>
	</c:forEach>
</table>