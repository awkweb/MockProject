<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<section class="row login">
	<div class="col-sm-5 col-sm-offset-3">
		<h3>Reset password</h3>
		
		<c:if test="${error}">
			<c:set value="has-error" var="cssClass"></c:set>
		</c:if>

		<form:form action="reset" method="post" modelAttribute="user">
			<div class="form-group ${cssClass}">
				<form:label path="username" cssClass="control-label">Username: </form:label>
				<form:input path="username" name="username" cssClass="form-control"
					type="text" />
			</div>
			
			<div class="form-group ${cssClass}">
				<form:label path="email" cssClass="control-label">Email: </form:label>
				<form:input path="email" name="email" cssClass="form-control"
					type="text" />
			</div>
			
			<div class="form-group ${cssClass}">
				<form:label path="password" cssClass="control-label">New Password: </form:label>
				<form:input path="password" name="newPassword" cssClass="form-control"
					type="password" />
			</div>
			
			<button type="submit" class="btn btn-default">Reset</button>
			<a class="btn btn-link" href="${pageContext.request.contextPath}" role="button">Return to login</a>
		</form:form>

		<c:if test="${error}">
			<div class="alert alert-danger" role="alert">
				Unable to reset password. Try again.
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		
	</div>
</section>
