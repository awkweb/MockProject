<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${pnames}" var="pname">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse${pname}"
						aria-expanded=false aria-controls="collapseOne">${pname} </a> <small>
					</small>
				</h4>
			</div>


			<div id="collapse${pname}" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingOne">

				<table class="table table-bordered table-hover table-responsive">
					<tr>

						<th>Portfolio Name</th>
						<th>Security</th>
						<th>Total Quantity</th>
						<th>Average Price</th>


					</tr>
					<c:forEach items="${portfoliolist}" var="pmpos">

						<c:if test="${pname == pmpos.getPortfolioname()}">

							<tr>
								<td><c:out value="${pmpos.getPortfolioname()}" /></td>
								<td><c:out value="${pmpos.getSecurity()}" /></td>
								<c:choose>
									<c:when test="${pmpos.getQty() lt 0}">
										<td><c:out value="${-pmpos.getQty()}" /></td>
									</c:when>
									<c:otherwise>
										<td><c:out value="${pmpos.getQty()}" /></td>
									</c:otherwise>
								</c:choose>
								<td><c:out value="${pmpos.getAvgprice()}" /></td>


							</tr>
						</c:if>


					</c:forEach>
				</table>

			</div>
		</div>
	</c:forEach>
</div>
