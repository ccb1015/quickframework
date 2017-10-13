<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<body>

<table class='grid'>
<#list tables as table>
	<tr>
		<td>${table.description }列表</td>
		<td><a href="${table.className?uncap_first}!list.action">${table.className?uncap_first}!list.action</a></td>
	</tr>
</#list>
</table>

</body>
</html>
