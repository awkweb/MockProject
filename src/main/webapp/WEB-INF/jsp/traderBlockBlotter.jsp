<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${authenticatedUser.getBlocks()}" var="block">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<input type="checkbox" data-id="${block.getBlockId()}"> <a
						class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse${block.getBlockId()}"
						aria-expanded=false aria-controls="collapseOne">Block
						#${block.getBlockId()} ${block.getOrders().get(0).getSymbol()}
						${block.getOrders().get(0).getSide()}</a> <small>Total
						Quantity: ${block.getTotalQty()}</small>
				</h4>
			</div>
			<div id="collapse${block.getBlockId()}"
				class="panel-collapse collapse" role="tabpanel"
				aria-labelledby="headingOne">

				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th class="text-center"><a href="#">Select All</a></th>
						<th>Order Id</th>
						<th>Portfolio Manager</th>
						<th>Portfolio</th>
						<th>Total Quantity</th>
						<th>Open Quantity</th>
						<th>Allocated Quantity</th>

						<c:choose>
							<c:when test="${order.getLimitPrice() > 0.00}">
								<th>Limit Price</th>
							</c:when>
							<c:when test="${order.getStopPrice() > 0.00}">
								<th>Stop Price</th>
							</c:when>
							<c:otherwise/>
						</c:choose>
						
						<th>Account Type</th>
						<th>Status</th>
						<th></th>
					</tr>
					<c:forEach items="${block.getOrders()}" var="order">
						<tr>
							<td class="text-center"><input type="checkbox"
								data-id="${order.getOrderId()}"></td>
							<td>${order.getOrderId()}</td>
							<td>${order.getUser2().getFName()}
								${order.getUser2().getLName()}</td>
							<td>${order.getPortfolio().getName()}</td>
							<td>${order.getTotalQty()}</td>
							<td>${order.getOpenQty()}</td>
							<td>${order.getAllocQty()}</td>

							<c:choose>
								<c:when test="${order.getLimitPrice() > 0.00}">
									<td>${order.getLimitPrice()}</td>
								</c:when>
								<c:when test="${order.getStopPrice() > 0.00}">
									<td>${order.getStopPrice()}</td>
								</c:when>
								<c:otherwise />
							</c:choose>
							
							<td>${order.getAccType()}</td>
							<td>${order.getStatus()}</td>
							<td class="text-center"><a href="#">Edit</a></td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</c:forEach>

</div>
