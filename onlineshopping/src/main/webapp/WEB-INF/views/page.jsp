<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>online shopping-${title}</title>
<script>
	window.menu = '${title}';
	
	window.contextRoot='${contextRoot}'
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap Data TAble  -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">
			<!-- loding home page contained -->
			<c:if test="${userClickHome==true}">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- load only when user clicked about -->
			<c:if test="${userClickAbout==true}">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- load only when user clicked contact -->
			<c:if test="${userClickContact==true}">
				<%@include file="contact.jsp"%>
			</c:if>
			<!-- load only when user clicked contact -->
			<c:if test="${userClickAllProducts==true or userClickCategoryProducts==true}">
				<%@include file="listProduct.jsp"%>
			</c:if>
			<!-- load only when user clicked show product -->
			<c:if test="${userClickShowProduct==true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			<!-- load only when user clicked manage product -->
			<c:if test="${userClickedMangedProduct==true}">
				<%@include file="mangeProducts.jsp"%>
			</c:if>
			
			<!-- load only when user clicked show  cart -->
			<c:if test="${userClickShowCart==true}">
				<%@include file="cart.jsp"%>
			</c:if>
			
		</div>
		<!-- /footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- /.container -->

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>
		
		<!-- jQuery Validatar -->
		<script src="${js}/jquery.validate.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		
		<!-- data table plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- data Bootstrp script -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!--  Bootbox  -->
		<script src="${js}/bootbox.min.js"></script>
		
		<!-- Self Coded JavaScript -->
		<script src="${js}/myapp.js"></script>
		
	</div>
</body>

</html>


