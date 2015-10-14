<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${authenticatedUser.getBlocks()}" var="block">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<a role="button" data-toggle="collapse" data-parent="#accordion"
						href="#collapse${block.getBlockId()}" aria-expanded="true"
						aria-controls="collapseOne">Block ${block.getBlockId()} SYMBOL
						SIDE</a>  <small>Total Quantity. ${block.getTotalQty()}, PRICE</small>
				</h4>
			</div>
			<div id="collapse${block.getBlockId()}"
				class="panel-collapse collapse in" role="tabpanel"
				aria-labelledby="headingOne">

				<table class="table table-bordered table-hover ">
					<tr>
						<th></th>
						<th>Order Id</th>
						<th>Portfolio Manager</th>
						<th>Portfolio</th>
						<th>Quantity</th>
						<th>Price</th>
						<th></th>
					</tr>
					<c:forEach items="${block.getOrders()}" var="order">
						<tr>
							<td><input type="checkbox"></td>
							<td>${order.getOrderId()}</td>
							<td>${order.getUser2().getFName()}
								${order.getUser2().getLName()}</td>
							<td>${order.getPortfolio().getName()}</td>
							<td>${order.getTotalQty()}</td>
							<td>$200</td>
							<td>Edit</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</c:forEach>

</div>
