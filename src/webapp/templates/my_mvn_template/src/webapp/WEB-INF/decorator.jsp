<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh:write property='title' /></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/common.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/myblockUI.js"></script>
<sitemesh:write property='head' />
</head>
<body>
<h1><sitemesh:write property='title' /></h1>
<hr/>
 <sitemesh:write property='body' />
<br>
<div align="center" style="width:100%;color:grey;border-top:1px solid; position:fixed;bottom: 0;margin-top:10px;">
	CopyRight@2015-2016<br/>
	${projectCode }
</div>
</body>
</html>