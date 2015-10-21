<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${blockBlotterError}">
	<div class="alert alert-danger" role="alert">
		${blockBlotterMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<c:if test="${blockBlotterSuccess}">
	<div class="alert alert-success" role="alert">
		${blockBlotterMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${authenticatedUser.getBlocks()}" var="block">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<input type="radio" name="block" value="${block.getBlockId()}"
						data-type="block"> <a class="collapsed" role="button"
						data-toggle="collapse" data-parent="#accordion"
						href="#collapse${block.getBlockId()}" aria-expanded=false
						aria-controls="collapseOne">Block #${block.getBlockId()}
						${block.getSymbol()} ${block.getSide()}</a> <small>Type:
						${block.getOrders().get(0).getOrdertype()}, <c:choose>
							<c:when
								test="${block.getOrders().get(0).getOrdertype().equals('Limit')}">
									Limit Price: $${block.getLimitPrice()}, 
								</c:when>
							<c:when
								test="${block.getOrders().get(0).getOrdertype().equals('Stop Loss')}">
									Stop Price: $${block.getStopPrice()}, 
								</c:when>
							<c:otherwise />
						</c:choose> Total Quantity: ${block.getTotalQty()<0? -block.getTotalQty():block.getTotalQty()}
					</small>
				</h4>
			</div>
			<div id="collapse${block.getBlockId()}"
				class="panel-collapse collapse" role="tabpanel"
				aria-labelledby="headingOne">

				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th class="text-center"><input type="checkbox" name="block"
							data-type="block" id="${block.getSide()}_${block.getSymbol()}"
							class="blockcheckbox"></th>
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
						
						<c:if test="${block.getOrders().get(0).getOrdertype() != 'Market'}">
							<th></th>
						</c:if>
					</tr>
					<c:forEach items="${block.getOrders()}" var="order">
						<tr>
							<td class="text-center"><input type="checkbox"
								class="ordercheckbox" value="${order.getOrderId()}"
								data-type="order"
								data-checkid="${order.getSide()}_${order.getSymbol()}"></td>
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
							
							<c:if test="${order.getOrdertype() != 'Market'}">
								<td class="text-center">
								<a id="editOrder" data-toggle="modal"
									data-target="#myModal1" data-id="${order.getOrderId()}"
									data-options="${order.getOrdertype()}" href="#" role="button">Edit</a>
									</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</c:forEach>

</div>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 id="modalTitle" class="modal-title">Edit Order #</h4>
			</div>

			<form action="edit-block" method="post">
				  
				<div class="modal-body">
					<input class="modalid" type="text" name="id" style="display: none;"
						value="">
					<input class="modaltype" type="text" name="type" style="display: none;"
						value="">

					<div class="form-group">
						<label id="inputLabel" for="inputPrice" class="col-sm-12 control-label">Limit
							Price</label>
						<div class="input-group">
					      	<div class="input-group-addon">$</div>
					      	<input type="text" class="form-control"
					      	id="inputPrice" placeholder="00.00" name="price" pattern="^\d+(\.|\,)\d{2}$">
					    </div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>

		</div>
	</div>
</div>

<script
	src="${pageContext.request.contextPath}/static/js/traderBlockBlotter.js"></script>
