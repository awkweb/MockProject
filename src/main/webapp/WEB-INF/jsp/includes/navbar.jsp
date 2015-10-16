<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/block-blotter">Acme
				Inc.</a>
		</div>
		
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${authenticatedUser.getRole() == 'et'}">
						<li><a
							href="${pageContext.request.contextPath}/block-blotter">Block
								Blotter
						</a></li>
						<li><a href="${pageContext.request.contextPath}/execution-blotter">Execution
								Blotter</a></li>
						<li><a href="${pageContext.request.contextPath}/open-orders">Open
								Orders</a></li>
					</c:when>
					<c:otherwise>
						<li class="active"><a
							href="${pageContext.request.contextPath}/#">PM Link 1<span
								class="sr-only">(current)</span></a></li>
						<li><a href="${pageContext.request.contextPath}/#">PM
								Link 2</a></li>
						<li><a href="${pageContext.request.contextPath}/#">PM
								Link 3</a></li>
					</c:otherwise>
				</c:choose>
				<li><p class="navbar-text">User:
						${authenticatedUser.getFName()} ${authenticatedUser.getLName()}</p></li>
				<li><a href="${pageContext.request.contextPath}/logout">Log
						Out</a></li>
			</ul>
		</div>
	</div>
</nav>
