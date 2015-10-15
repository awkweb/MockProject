<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<section class="row login">
	<div class="col-sm-5 col-sm-offset-3">
		<h3>Login to Acme</h3>

		<c:if test="${error}">
			<c:set value="has-error" var="cssClass"></c:set>
		</c:if>

		<form:form action="authenticate" method="post" modelAttribute="user">
			<div class="form-group ${cssClass}">
				<form:label path="username" cssClass="control-label">Username: </form:label>
				<form:input path="username" name="username" cssClass="form-control"
					type="text" />
			</div>

			<div class="form-group ${cssClass}">
				<form:label path="password" cssClass="control-label">Password: </form:label>
				<form:input path="password" name="password" cssClass="form-control"
					type="password" />
			</div>

			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>

		<c:if test="${error}">
			<div class="alert alert-danger" role="alert">
				Invalid credentials. Try submitting again.
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		
		<c:if test="${resetSuccess}">
			<div class="alert alert-success" role="alert">
				Reset password successful!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>

		<p class="text-center">
			<a href="${pageContext.request.contextPath}/forgot">Forgot
				password?</a>
		</p>
	</div>
</section>
