<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>generatorDbserver - list</title>
<jsp:include page="/base.jsp"></jsp:include>

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>数据库服务器</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="SRDataBaseConfig!add.action">新增</a></li>
							</ul>
							<a class="close-link" ><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline" method="post" action="generatorDbserver!list.action">
										<a class="btn btn-white" href="SRDataBaseConfig!add.action" style="float: right;">新增</a>
											<!-- search -->
										<%-- <div class="form-group">
											<label>
												<label class="">昵称：</label>
												<input type="text" placeholder="请输入昵称" class="form-control" name="bean.user.nickName" value="">
											</label>
										</div>
										<button class="btn btn-white" type="submit" style="float: right;margin-right: 10px;">查询</button> --%>
									</form>
								</div>
							</div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>主键</th>
										<th>名称</th>
										<th>描述</th>
										<th>数据库类型</th>
										<th width="30%">jdbcUrl</th>
										<th>用户名称</th>
										<th>密码</th>
										<th>方言</th>
										<th>驱动类</th>
										<th style="min-width: 110px;">操作</th>
									</tr>
								</thead>
								<tbody>
		<s:if test="#bean.objectList==null || #bean.objectList.size()==0">
			<tr >
				<td colspan="10" align="center">暂无数据</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator value="bean.objectList" var="item" status="s">
				<tr>
					<td>
						<s:property value="#item.id"/>
					</td>
					<td><s:property value="#item.code"/></td>
					<td><s:property value="#item.name"/></td>
					<td><s:property value="#item.dbType"/></td>
					<td><s:property value="#item.jdbcUrl"/></td>
					<td><s:property value="#item.username"/></td>
					<td><s:property value="#item.password"/></td>
					<td><s:property value="#item.dialect"/></td>
					<td><s:property value="#item.driverClass"/></td>
					<td>
						<a href="SRDataBaseConfig!edit.action?bean.generatorDbserver.id=<s:property value="#item.id"/>">修改</a>&nbsp;
						<a href="SRDataBaseConfig!delete.action?bean.generatorDbserver.id=<s:property value="#item.id"/>">删除</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="SRDataBaseConfig!list.action" 
totalCount="${bean.pageInfo.totalCounts}" 
pageNo="${bean.pageInfo.currentPage }" 
pageSize="${bean.pageInfo.pageSize}" 
ajax="false" ajaxRequestFun="doSearch"/>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>

	
</body>
</html>
