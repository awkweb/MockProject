<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="${executeBlocks}" var="row">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse${row.block.blockId}"
						aria-expanded=false aria-controls="collapseOne">Block
						#${row.block.blockId} ${row.side} ${row.security.symbol}</a> <small>Executed
						Quantity: ${row.executedQty}, Transaction Price:
						$${row.transactFee}, Status: ${row.status}</small>
				</h4>
			</div>
			<div id="collapse${row.block.blockId}"
				class="panel-collapse collapse" role="tabpanel"
				aria-labelledby="headingOne">

				<table class="table table-bordered table-hover table-responsive">
					<tr>
						<th>Order Id</th>
						<th>Manager</th>
						<th>Portfolio</th>
						<th>Trade Price</th>
						<th>Open Quantity</th>
						<th>Allocated Quantity</th>
						<th>Status</th>
						<th>Portfolio</th>
						<th>Account Type</th>
					</tr>
					<c:forEach items="${ordersMap}" var="orders">

						<c:if test="${orders.key.blockId==row.block.blockId}">

							<c:forEach items="${orders.value}" var="orderDetails">
								<tr>
									<td>${orderDetails.orderId}</td>
									<td>${orderDetails.user1.FName}
										${orderDetails.user1.LName}</td>
									<td>${orderDetails.portfolio.name}</td>
									<td>${row.tradePrice}</td>

									<td>${orderDetails.openQty}</td>
									<td>${orderDetails.allocQty}</td>

									<c:choose>
										<c:when test="${orderDetails.status == 'Partially Executed'}">
											<td class="warning">${orderDetails.status}</td>
										</c:when>
										<c:when test="${orderDetails.status == 'Completed'}">
											<td class="success">${orderDetails.status}</td>
										</c:when>
										<c:otherwise>
											<td class="info">${orderDetails.status}</td>
										</c:otherwise>
									</c:choose>

									<td>${orderDetails.portfolio.name}</td>
									<td>${orderDetails.accType}</td>
								</tr>

							</c:forEach>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:forEach>
</div>