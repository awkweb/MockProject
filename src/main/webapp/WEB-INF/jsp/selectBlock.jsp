<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">

	<c:forEach items="" var="block" varStatus="loop">
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title">
					<input type="checkbox" value="${loop.index}"
						data-type="block"> <a class="collapsed" role="button"
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
								test="${block.getOrders().get(0).getOrdertype().equals('Stop Loss')}">
									Stop Price: $${block.getStopPrice()}, 
								</c:when>
							<c:otherwise />
						</c:choose> Total Quantity: ${block.getTotalQty()}
					</small>
				</h4>
			</div>


		</div>
	</c:forEach>

</div>

<script src="${pageContext.request.contextPath}/static/js/traderOpenOrders.js"></script>
