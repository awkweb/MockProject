<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<tiles:insertAttribute name="head" />
	<title><tiles:insertAttribute name="title" ignore="true" /></title> 
</head>
<body>
	<tiles:insertAttribute name="navbar" />

	<div class="container">
		<tiles:insertAttribute name="pageHeader" />
		<tiles:insertAttribute name="content" /> 
	</div>
</body>
</html>
