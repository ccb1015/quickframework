<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/base.jsp"></jsp:include>
<title>${table.description} - 列表</title>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>${table.description}</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="${classNameLower}!add.action">新增</a></li>
							</ul>
							<a class="close-link"><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline form_search" method="post" action="${classNameLower}!list.action">
										<#if (table.searchFieldCount!=0) >
											<!-- search -->
											<#list table.columns as column>
												<#if column.search>
										<div class="form-group">
											<label>
												<label class="">${column.description}：</label>
												<input type="text" placeholder="请输入${column.description}" class="form-control" name="bean.${classNameLower}.${column.columnNameLower}" value="<@jspEl 'bean.${classNameLower}.${column.columnNameLower}' />">
											</label>
										</div>
												</#if>
											</#list>
											<!-- 下拉选择 -->
											<#list table.associatedTables?values as foreignKey>
												<#if foreignKey.field?? && foreignKey.field.isSearch?? && foreignKey.field.isSearch==1>
													<#if foreignKey.field.entityTypeId??>
														<#assign fkPojoClass = foreignKey.classTableName>
														<#assign fkPojoClassVar = fkPojoClass?uncap_first>
														<#assign valueColumn = foreignKey.columnName?uncap_first>
														<#assign fkFieldName = foreignKey.parentName>
										<label>
												<label>${foreignKey.description }：</label>
			<select name="bean.${foreignKey.table.className?uncap_first}.${fkFieldName?uncap_first}.id" class="form-control">
				<option value="">----</option>
				<s:if test="#${fkPojoClassVar}s !=null && #${fkPojoClassVar}s.size()>0">
				<s:iterator value="${fkPojoClassVar}s" var="item" status="s">
					<option value="<@jspEl 'item.${valueColumn}'/>" <@jspEl '(bean.${classNameLower}.${fkFieldName?uncap_first}!=null && bean.${classNameLower}.${fkFieldName?uncap_first}.${valueColumn}==item.${valueColumn})?"selected=\'selected\'":""'/> ><@jspEl 'item.${foreignKey.showColumnName }' /></option>
				</s:iterator>
				</s:if>
			</select>
											</label>				
													</#if>
												</#if>
											</#list>
										</#if>
										<a class="btn btn-white btn_delete_selected" href="javascript:;" url="${classNameLower}!asyncDeleteAll.action" style="float: right;">删除</a>
										<a class="btn btn-white" href="${classNameLower}!add.action" style="float: right;margin-right:10px;">新增</a>
										<#if (table.searchFieldCount!=0) >
										<button class="btn btn-white" type="submit" style="float: right;margin-right: 10px;">查询</button>
										</#if>
									</form>
								</div>
							</div>
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th style="text-align: center;"><input type="checkbox" class="ck_all"/></th>
										<th>ID</th>
										<!-- property -->
										<#list table.columns as column>
											<#if !column.pk>
										<th>${column.description }</th>
											</#if>
										</#list>
										<#list table.associatedTables?values as foreignKey>
											<#if foreignKey.relation == "ManyToOne">
										<!-- many2one -->
										<th><#if foreignKey.description??>${foreignKey.description}<#else>${foreignKey.classTableName}</#if></th>
											</#if>
											<#if foreignKey.relation == "ManyToMany" && (foreignKey.mainFk?? && foreignKey.mainFk=="1")>
										<!-- many2many -->
										<!-- 
										<th><#if foreignKey.description??>${foreignKey.description}<#else>${foreignKey.classTableName}</#if></th>	
										 -->
											</#if>
										</#list>
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
										<!-- property -->
										<#list table.columns as column>
											<#if !column.pk>
												<#if column.isDateTimeColumn>
										<td><s:date name="#item.${column.columnNameLower}" format="yyyy-MM-dd HH:mm:ss"/></td>
												<#else>
										<td><s:property value="#item.${column.columnNameLower}"/></td>
												</#if>
											</#if>
										</#list>
										<#list table.associatedTables?values as foreignKey>
											<#if foreignKey.relation == "ManyToOne">
												<#assign fkPojoClass = foreignKey.classTableName>
												<#assign fkPojoClassVar = fkPojoClass?uncap_first>
										<!-- many2one -->
												<#if foreignKey.parentName??>
													<#assign fkFieldName = foreignKey.parentName>
												<#else>
													<#assign fkFieldName = fkPojoClass>
												</#if>
										<td><s:property value="#item.${fkFieldName?uncap_first}.${foreignKey.showColumnName }"/></td>
											</#if>
											<#if foreignKey.relation == "ManyToMany" && (foreignKey.mainFk?? && foreignKey.mainFk=="1")>
										<!-- many2many -->
											<#if foreignKey.parentName??>
												<#assign fkFieldName = foreignKey.parentName>
											<#else>
												<#assign fkFieldName = fkPojoClass +"s">
											</#if>
										<!-- <td>
											<s:if test="bean.${classNameLower}.${fkFieldName?uncap_first}!=null">
												<s:iterator value="bean.${classNameLower}.${fkFieldName?uncap_first}" var="o">
										<@jspEl 'o.index==0?"":"; "' />
										<@jspEl 'o.${foreignKey.showColumnName }' />
												</s:iterator>
											 </s:if>
										</td> -->
											</#if>
										</#list>
										<td>
											<a href="${classNameLower}!edit.action?bean.${classNameLower}.id=<s:property value="#item.id"/>">修改</a>&nbsp;
											<a href="javascript:;" url="${classNameLower}!asyncDeleteAll.action?bean.idList=<s:property value="#item.id"/>" class="btn_delete_item">删除</a>&nbsp;
										</td>
									</tr>
									</s:iterator>
								</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="${classNameLower}!list.action" 
totalCount="<@jspEl 'bean.pageInfo.totalCounts'/>" 
pageNo="<@jspEl 'bean.pageInfo.currentPage '/>" 
pageSize="<@jspEl 'bean.pageInfo.pageSize'/>" 
ajax="false" ajaxRequestFun="doSearch"/>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>
