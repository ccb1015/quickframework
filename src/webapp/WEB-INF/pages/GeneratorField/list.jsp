<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>generatorField - list</title>
<jsp:include page="/base.jsp"></jsp:include>

<style type="text/css">.td-btns a{word-break: keep-all;  padding: 5px 0;display: inline-block;}</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>字段</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="generatorField!add.action?bean.generatorField.entityId=${bean.generatorField.entityId }">新增</a></li>
							</ul>
							<a class="close-link" ><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline form_search" method="post" action="generatorField!list.action">
										<a class="btn btn-white btn_delete_selected" href="javascript:;" url="generatorField!asyncDeleteAll.action" style="float: right;">删除</a>
										<a class="btn btn-white" href="generatorField!add.action?bean.generatorField.entityId=${bean.generatorField.entityId }" style="float: right;margin-right:10px;">新增</a>
											<!-- search -->
										<div class="form-group">
											<label>
												<label>名称：</label>
												<input type="text" placeholder="请输入名称" class="form-control" name="bean.generatorField.code" value="${bean.generatorField.code }" />
											</label>
											<label>
												<label>描述：</label>
												<input type="text" placeholder="请输入描述" class="form-control" name="bean.generatorField.name" value="${bean.generatorField.name }" />
											</label>
											<label>
												<label>所属实体：</label>
						<select name="bean.generatorField.entityId" class="form-control">
							<option value="">----</option>
							<s:if test="generatorEntitys==null || generatorEntitys.size()==0">
							<s:iterator value="generatorEntitys" var="item" status="s">
								<option value="${item.id }" ${bean.generatorField.entityId==item.id?'selected="selected"':'' }>${item.name==null?item.code:item.name }</option>
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
			<th><input type="checkbox" class="ck_all"/></th>
			<th>ID</th>
			<th>字段名称</th>
			<th>描述</th>
			<th>类型</th>
			<th>长度</th>
			<th>主键</th>
			<th>自增</th>
			<th>非空</th>
			<th>默认值</th>
			<th>唯一</th>
			<th>所属实体</th>
			<th>关联关系</th>
			<th>维护关联表</th>
			<th>显示属性(关联对象)</th>
			<th style="min-width: 90px;">操作</th>
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
						<input type="checkbox" class="ck_item" value="<s:property value="#item.id"/>"/>
					</td>
					<td>
						<s:property value="#item.id"/>
					</td>
					<td><s:property value="#item.code"/></td>
					<td><s:property value="#item.name"/></td>
					<td>
						<s:if test="#item.entityType != null && #item.entityType.code != null">
							<s:if test="#item.fkTypeColumn=='ManyToMany'">
								${item.dataType.code==null||item.dataType.code==''?'Set': item.dataType.code}
								&lt;${item.entityType.code }&gt;
							</s:if>
							<s:else>
								${item.entityType.code }
							</s:else>
						</s:if>
						<s:else>
							${item.dataType.code }
						</s:else>
					</td>
					<td><s:property value="#item.length"/></td>
					<td><s:property value="#item.pk"/></td>
					<td><s:property value="#item.isIncrement"/></td>
					<td><s:property value="#item.notNull"/></td>
					<td><s:property value="#item.defaultValue"/></td>
					<td><s:property value="#item.isUnique"/></td>
					<td>${generatorEntity.name }(${item.generatorEntity.code })</td>
					<td><s:property value="#item.fkTypeColumn"/></td>
					<td><s:property value="#item.mainFk"/></td>
					<td><s:property value="#item.showColumn.code"/></td>
					<td>
						<a href="generatorField!edit.action?bean.generatorField.id=${item.id}&bean.generatorField.entityId=${item.entityId }">修改</a>&nbsp;
						<a href="javascript:;" url="generatorField!asyncDeleteAll.action?bean.idList=<s:property value="#item.id"/>" class="btn_delete_item">删除</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="generatorField!list.action" 
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
