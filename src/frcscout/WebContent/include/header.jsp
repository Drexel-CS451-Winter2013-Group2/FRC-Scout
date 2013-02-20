<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/frcscout/css/lib/ext-all.css" rel="stylesheet" type="text/css">
<link href="/frcscout/css/standard.css" rel="stylesheet" type="text/css">
<link href="/frcscout/css/layout/layout.css" rel="stylesheet" type="text/css">
<script src="/frcscout/js/lib/extjs/ext-all-debug-w-comments.js"></script>
<script src="/frcscout/js/layout/layout.js"></script> 
<script src="/frcscout/js/util/util.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRC Scout</title>
</head>
<body>
<div id="links">
<a href="/frcscout/index.jsp">View Event Data</a>
<c:if test="${pageContext.request.isUserInRole('scout')}">
     | <a href="/frcscout/scout/scout.jsp">Manage My Scout Data</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('administrator')}">
     | <a href="/frcscout/admin/admin.jsp">System Administrator</a>
</c:if>
</div>
<div id="logout"><%=request.getRemoteUser()%> <a href="/frcscout/logout.jsp">Log Out</a></div>
<div id="content">