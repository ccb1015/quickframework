<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>generatorEntity - list</title>
<jsp:include page="/base.jsp"></jsp:include>

<style type="text/css">.td-btns a{word-break: keep-all;  padding: 5px 0;display: inline-block;}</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>实体</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="generatorEntity!add.action?bean.generatorEntity.projectId=${bean.generatorEntity.projectId }">新增</a></li>
							</ul>
							<a class="close-link" ><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline" method="post" action="generatorEntity!list.action">
										<a class="btn btn-white" href="generatorEntity!add.action?bean.generatorEntity.projectId=${bean.generatorEntity.projectId }" style="float: right;">新增</a>
											<!-- search -->
										<div class="form-group">
											<label>
												<label>名称：</label>
												<input type="text" placeholder="请输入名称" class="form-control" name="bean.generatorEntity.code" value="${bean.generatorEntity.code }" />
											</label>
											<label>
												<label>描述：</label>
												<input type="text" placeholder="请输入描述" class="form-control" name="bean.generatorEntity.name" value="${bean.generatorEntity.name }" />
											</label>
											<label>
												<label>所属项目：</label>
			<select name="bean.generatorEntity.projectId" class="form-control">
				<option value="">----</option>
				<s:if test="generatorProjects==null || generatorProjects.size()==0">
				<s:iterator value="generatorProjects" var="item" status="s">
					<option value="${item.id }" ${bean.generatorEntity.projectId==item.id?'selected="selected"':'' }>${item.name }</option>
				</s:iterator>
				</s:if>
			</select>
											</label>
										</div>
										<button class="btn btn-white" type="submit" style="float: right;margin-right: 10px;">查询</button>
									</form>
								</div>
							</div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>实体名称</th>
										<th>描述</th>
										<th>所属项目</th>
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
						<td><s:property value="#item.code"/></td>
						<td><s:property value="#item.name"/></td>
						<td>${generatorProject.name}(${item.generatorProject.code })</td>
					<td>
						<a href="generatorEntity!edit.action?bean.generatorEntity.id=<s:property value="#item.id"/>&bean.generatorEntity.projectId=${item.projectId }">修改</a>&nbsp;
						<a href="generatorEntity!delete.action?bean.generatorEntity.id=<s:property value="#item.id"/>">删除</a>&nbsp;
						
						<a href="generatorField!list.action?bean.generatorField.entityId=${item.id }"> 查看字段</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="generatorEntity!list.action" 
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
