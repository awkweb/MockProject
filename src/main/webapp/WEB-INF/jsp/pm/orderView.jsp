<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<c:if test="${orderViewError}">
	<div class="alert alert-danger" role="alert">
		${orderViewMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<c:if test="${orderViewSuccess}">
	<div class="alert alert-success" role="alert">
		${orderViewMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<div>

	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a id="newTab" href="#new"
			aria-controls="new" role="tab" data-toggle="tab">New</a></li>
		<li role="presentation"><a id="openTab" href="#open" aria-controls="open"
			role="tab" data-toggle="tab">Open</a></li>
		<li role="presentation"><a id="cancelledTab" href="#cancelled"
			aria-controls="cancelled" role="tab" data-toggle="tab">Cancelled</a></li>
	</ul>

	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="new">
			<br>
			<div class="panel panel-default">
				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th class="text-center"><input type="checkbox"
							name="checkboxNew" id="checkboxNew"
							value="row1"></th>
						<th>Order Id</th>
						<th>Symbol</th>
						<th>Average Price</th>
						<th>Total Quantity</th>
						<th></th>
					</tr>

					<c:forEach items="${newOrders}" var="order">
						<tr>
							<td class="text-center"><input type="checkbox" class="ordercheckbox"
								id="${order.getOrderId()}" value="row1" data-checkid="subNewOrders"></td>
							<td>${order.getOrderId()}</td>
							<td>${order.getSecurity().getSymbol()}</td>
							<td>${order.getSecurity().getMarketPrice()}</td>
							<td>${order.getTotalQty()<0? -order.getTotalQty():order.getTotalQty()}</td>
							<td class="text-center"><a id="editOrder"
								data-id="${order.getOrderId()}" role="button">Edit</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="open">
			<br>
			<div class="panel panel-default">
				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th class="text-center"><input type="checkbox"
							name="checkboxOpen" id="checkboxOpen"
							value="row1"></th>
						<th>Order Id</th>
						<th>Symbol</th>
						<th>Average Price</th>
						<th>Total Quantity</th>
						<th></th>
					</tr>

					<c:forEach items="${openOrders}" var="order">
						<tr>
							<td class="text-center"><input type="checkbox" class="ordercheckbox"
								id="${order.getOrderId()}" value="row1" data-checkid="subOpenOrders"></td>
							<td>${order.getOrderId()}</td>
							<td>${order.getSecurity().getSymbol()}</td>
							<td>${order.getSecurity().getMarketPrice()}</td>
							<td>${order.getTotalQty()<0? -order.getTotalQty():order.getTotalQty()}</td>
							<td class="text-center"><a id="amendOrder"
								data-id="${order.getOrderId()}" role="button">Amend</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="cancelled">
			<br>
			<div class="panel panel-default">
				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th>Order Id</th>
						<th>Symbol</th>
						<th>Average Price</th>
						<th>Total Quantity</th>
					</tr>

					<c:forEach items="${cancelledOrders}" var="order">
						<tr>
							<td>${order.getOrderId()}</td>
							<td>${order.getSecurity().getSymbol()}</td>
							<td>${order.getSecurity().getMarketPrice()}</td>
							<td>${order.getTotalQty()<0? -order.getTotalQty():order.getTotalQty()}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>
