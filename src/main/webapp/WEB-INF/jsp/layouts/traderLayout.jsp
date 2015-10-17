<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<tiles:insertAttribute name="head" />
	<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<tiles:insertAttribute name="navbar" />
	<div class="container">

		<div class="row">
			<div class="col-sm-12">
				<div class="page-header">
					<h2><tiles:insertAttribute name="title" /></h2>
				</div>
			</div>
		</div>
		
		<tiles:insertAttribute name="toolbar" />
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html>
