<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${openOrdersError}">
	<div class="alert alert-danger" role="alert">
		${openOrdersMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<c:if test="${openOrdersSuccess}">
	<div class="alert alert-success" role="alert">
		${openOrdersMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${proposedBlocks}" var="block" varStatus="loop">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					 <a class="collapsed" role="button"
						data-toggle="collapse" data-parent="#accordion"
						href="#collapse${loop.index}" aria-expanded=false
						aria-controls="collapseOne">Proposed ${block.getSymbol()}
						${block.getSide()}</a> <small>Type:
						${block.getOrders().get(0).getOrdertype()}, <c:choose>
							<c:when
								test="${block.getOrders().get(0).getOrdertype().equals('Limit')}">
									Limit Price: $${block.getLimitPrice()}, 
								</c:when>
							<c:when
								test="${block.getOrders().get(0).getOrdertype().equals('Stop')}">
									Stop Price: $${block.getStopPrice()}, 
								</c:when>
							<c:otherwise />
						</c:choose> Total Quantity: ${block.getTotalQty()<0? -block.getTotalQty():block.getTotalQty()}
					</small>
				</h4>
			</div>
			<div id="collapse${loop.index}" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingOne">

				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th class="text-center">
						<input type="checkbox" name="block" data-type="block" id="${block.getSide()}_${block.getSymbol()}" 
						class="blockcheckbox">
						</th>
						<th>Order Id</th>
						<th>Portfolio Manager</th>
						<th>Portfolio</th>
						<th>Total Quantity</th>
						<th>Open Quantity</th>
						<th>Allocated Quantity</th>

						<c:choose>
							<c:when
								test="${block.getOrders().get(0).getOrdertype().equals('Limit')}">
								<th>Limit Price</th>
							</c:when>
							<c:when
								test="${block.getOrders().get(0).getOrdertype().equals('Stop')}">
								<th>Stop Price</th>
							</c:when>
							<c:otherwise />
						</c:choose>

						<th>Account Type</th>
						<th>Status</th>
						<th>Notes</th>
						<th></th>
					</tr>
					<c:forEach items="${block.getOrders()}" var="order">
						<tr>
							<td class="text-center"><input type="checkbox" class="ordercheckbox"
								value="${order.getOrderId()}" data-type="order" data-checkid="${order.getSide()}_${order.getSecurity().getSymbol()}"></td>
							<td>${order.getOrderId()}</td>
							<td>${order.getUser2().getFName()}
								${order.getUser2().getLName()}</td>
							<td>${order.getPortfolio().getName()}</td>
							<td>${order.getTotalQty()<0? -order.getTotalQty():order.getTotalQty()}</td>
							<td>${order.getOpenQty()}</td>
							<td>${order.getAllocQty()}</td>

							<c:choose>
								<c:when test="${order.getOrdertype().equals('Limit')}">
									<td>$${order.getLimitPrice()}</td>
								</c:when>
								<c:when test="${order.getOrdertype().equals('Stop')}">
									<td>$${order.getStopPrice()}</td>
								</c:when>
								<c:otherwise />
							</c:choose>

							<td>${order.getAccType()}</td>
							<td>${order.getStatus()}</td>

							<c:choose>
								<c:when test="${order.getNotes().length() > 0}">
									<td><a tabindex="0" role="button" data-toggle="popover"
										data-trigger="focus" title="Notes"
										data-content="${order.getNotes()}">Display</a></td>
								</c:when>
								<c:otherwise>
									<td>None</td>
								</c:otherwise>
							</c:choose>

							<td class="text-center"><a href="#">Edit</a></td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</c:forEach>

</div>

<script src="${pageContext.request.contextPath}/static/js/traderOpenOrders.js"></script>
