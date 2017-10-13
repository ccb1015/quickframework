<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>服务方法</title>
<jsp:include page="/base.jsp"></jsp:include>

<style type="text/css">.td-btns a{word-break: keep-all;  padding: 5px 0;display: inline-block;}</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>服务方法</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="generatorServiceMethod!add.action?bean.generatorServiceMethod.serviceId=${bean.generatorServiceMethod.serviceId }">新增</a></li>
							</ul>
							<a class="close-link" ><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline" method="post" action="generatorServiceMethod!list.action">
										<a class="btn btn-white" href="generatorServiceMethod!add.action?bean.generatorServiceMethod.serviceId=${bean.generatorServiceMethod.serviceId }" style="float: right;">新增</a>
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
										<th>ID</th>
										<th>业务逻辑</th>
										<th>方法名称</th>
										<th>描述</th>
										<th>consumes</th>
										<th>访问方式</th>
										<th>producer</th>
										<th>访问子路径</th>
										<th>返回值类型</th>
										<th>所属服务</th>
									<th>操作</th>
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
						<td width="20%"><s:property value="#item.businessLogic"/></td>
						<td><s:property value="#item.code"/></td>
						<td><s:property value="#item.name"/></td>
						<td><s:property value="#item.consumes"/></td>
						<td><s:property value="#item.httpMethod"/></td>
						<td><s:property value="#item.producer"/></td>
						<td><s:property value="#item.subPath"/></td>
						<td><s:property value="#item.returnType"/></td>
						<td>
							 ${generatorService.name }(${generatorService.code })
						</td>
					<td>
						<a href="generatorServiceMethod!edit.action?bean.generatorServiceMethod.id=<s:property value="#item.id"/>&bean.generatorServiceMethod.serviceId=${item.serviceId}">修改</a>&nbsp;
						<a href="generatorServiceMethod!delete.action?bean.generatorServiceMethod.id=<s:property value="#item.id"/>">删除</a>&nbsp;
						<a href="generatorMethodParam!list.action?bean.generatorMethodParam.methodId=${item.id }"> 查看参数</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="generatorServiceMethod!list.action" 
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
