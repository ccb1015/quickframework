<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>genController - list</title>
<jsp:include page="/base.jsp"></jsp:include>

<style type="text/css">.td-btns a{word-break: keep-all;  padding: 5px 0;display: inline-block;}</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>GenController</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="genController!add.action">新增</a></li>
							</ul>
							<a class="close-link" ><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline form_search" method="post" action="genController!list.action">
											<!-- search -->
										<%-- <div class="form-group">
											<label>
												<label class="">昵称：</label>
												<input type="text" placeholder="请输入昵称" class="form-control" name="bean.user.nickName" value="">
											</label>
										</div>
										<button class="btn btn-white" type="submit" style="float: right;margin-right: 10px;">查询</button> --%>
										<a class="btn btn-white btn_delete_selected" href="javascript:;" url="genController!asyncDeleteAll.action" style="float: right;">删除</a>
										<a class="btn btn-white" href="genController!add.action" style="float: right;margin-right:10px;">新增</a>
									</form>
								</div>
							</div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th style="text-align: center;"><input type="checkbox" class="ck_all"/></th>
										<th>ID</th>
										<th>code</th>
										<th>name</th>
										<th>modelId</th>
										<th>projectId</th>
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
					<td style="text-align: center;">
						<input type="checkbox" class="ck_item" value="<s:property value="#item.id"/>"/>
					</td>
					<td>
						<s:property value="#item.id"/>
					</td>
						<td><s:property value="#item.code"/></td>
						<td><s:property value="#item.name"/></td>
						<td><s:property value="#item.modelId"/></td>
						<td><s:property value="#item.projectId"/></td>
					<td>
						<a href="genController!edit.action?bean.genController.id=<s:property value="#item.id"/>">修改</a>&nbsp;
						<a url="genController!asyncDeleteAll.action?bean.idList=<s:property value="#item.id"/>" class="btn_delete_item">删除</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="genController!list.action" 
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
