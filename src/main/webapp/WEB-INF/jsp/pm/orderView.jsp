<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div>

	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation active" class="active"><form:form
				action="PmNewOrderview" method="post">
				<input type="submit"
					style="background-color: transparent; width: 15em; height: 2em;"
					value="New" />

			</form:form></li>
		<li role="presentation"><form:form action="PmOpenOrderview"
				method="post">
				<input type="submit"
					style="background-color: transparent; width: 15em; height: 2em;"
					value="Open" />

			</form:form></li>
		<li role="presentation"><form:form action="PmCancelledOrderview"
				method="post">
				<input type="submit"
					style="background-color: transparent; width: 15em; height: 2em;"
					value="Cancelled" />

			</form:form></li>




		<div class="col-sm-3 col-sm-offset-8">
			<div class="btn-group btn-group-justified" role="group"
				aria-label="...">
				<div class="btn-group" role="group">
					<button id="cancelorderbutton" type="button"
						class="btn btn-default">Cancel</button>
				</div>
			</div>
		</div>


	</ul>

	<div class="tab-content">
		<br>
		<div role="tabpanel" class="tab-pane fade in active" id="New">
			<table class="table table-bordered table-hover table-responsive">
				<tr>
					<th>Select</th>
					<th>Order Id</th>
					<th>Symbol</th>

					<th>Average Price</th>

					<th>Total Quantity</th>
					<th>Status</th>
					<th></th>

				</tr>


				<c:forEach items="${pmorderlist}" var="order">
					<tr>
						<td><input type="checkbox" class="ordercheckbox"
							name="checkboxOptions" id="${order.getOrderId()}" value="row1"></td>
						<td><c:out value="${order.getOrderId()}" /></td>
						<td><c:out value="${order.getSecurity().getSymbol()}" /></td>
						<td><c:out value="${order.getSecurity().getMarketPrice()}" /></td>
						<td><c:out value="${order.getTotalQty()}" /></td>
						<td><c:out value="${order.getStatus()}" /></td>
						<td class="text-center"><a id="editOrder"
							data-id="${order.getOrderId()}" role="button">Edit</a></td>

					</tr>
				</c:forEach>


			</table>
			<div class="col-sm-3 col-sm-offset-8">
				<div class="btn-group btn-group-justified" role="group"
					aria-label="...">
					<div class="btn-group" role="group">
						<button id="sendorderbutton" type="button" class="btn btn-default">Send
							For Trade</button>
					</div>

				</div>
			</div>




		</div>
	</div>
</div>
