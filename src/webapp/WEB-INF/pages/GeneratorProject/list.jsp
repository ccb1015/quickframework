<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html>
<head>
<title>工程管理</title>
<jsp:include page="/base.jsp"></jsp:include>

<style type="text/css">.td-btns a{word-break: keep-all;  padding: 5px 0;display: inline-block;}</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>工程</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="generatorProject!add.action">新增</a></li>
							</ul>
							<a class="close-link" ><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dataTables_wrapper form-inline">
							<div class="row">
								
								<div class="col-sm-12">
									<form role="form" class="form-inline" method="post" action="generatorProject!list.action">
										<a class="btn btn-white" href="generatorProject!add.action" style="float: right;">新增</a>
											<!-- search -->
										<%-- <div class="form-group">
											<label>
												<label class="">昵称：</label>
												<input type="text" placeholder="请输入昵称" class="form-control" name="bean.user.nickName" value="${bean.user.nickName}">
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
										<th>path</th>
										<th>工程名称</th>
										<th>工程描述</th>
										<th>包名前缀</th>
										<th>模版</th>
										<th>数据库服务器</th>
										<th>web服务器</th>
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
						<td><s:property value="#item.path"/></td>
						<td><s:property value="#item.code"/></td>
						<td>
							<a target="_blank" href="http://${generatorWebserver.ip}:${generatorWebserver.port}/${code}">${name }</a>
						</td>
						<td><s:property value="#item.basepackage"/></td>
						<td><s:property value="#item.temaplateName"/></td>
						<td><%-- <s:property value="#item.generatorDbserver.code"/> -  --%>${generatorDbserver.name}</td>
						<td><%-- <s:property value="#item.generatorWebserver.code"/> -  --%>${generatorWebserver.name}</td>
					<td class="td-btns">
						<a href="generatorProject!edit.action?bean.generatorProject.id=<s:property value="#item.id"/>">修改</a>&nbsp;
						<a href="generatorProject!delete.action?bean.generatorProject.id=<s:property value="#item.id"/>">删除</a>&nbsp;
						
						<a class="btn_generate" href="javascript:;" url="generatorProject!generate.action?bean.generatorProject.id=<s:property value="#item.id"/>"> 生成代码</a>&nbsp;
						<a class="btn_clean" href="javascript:;" url="generatorProject!clean.action?codePath=${item.path }"> 清理</a>&nbsp;
						<a class="btn_publis" href="javascript:;" url="generatorProject!publish.action?codePath=${item.path }"> 发布</a>&nbsp;
						<a href="generatorEntity!list.action?bean.generatorEntity.projectId=${item.id }"> 查看实体</a>&nbsp;
						<a href="generatorService!list.action?bean.generatorEntity.projectId=${item.id }"> 查看服务</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</s:else>
								</tbody>
							</table>
<u:pageLink  linkId="123" align="right" action="generatorProject!list.action" 
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
	
	
<script>
		jQuery(function(){
			jQuery(document).on("click",".btn_publis,.btn_clean,.btn_confirm_generate",function(){
				ShowMsg("正在"+jQuery(this).text());
				var param={"ajax":true,"delete":jQuery(this).attr("delete")};
				jQuery.post(jQuery(this).attr("url"),param,function(data){
					jQuery.unblockUI();
					if(data.success==true){
						ShowMsgAutoClose(data.message||'成功');
					}else{
						alert(data.message);
					}
				});
			}).css("cursor","pointer");
			jQuery(".btn_generate").click(function(){
				ShowPoplayer(jQuery("#pop_generate"));
				jQuery(".btn_confirm_generate").attr("url",jQuery(this).attr("url"));
			});
		})
	</script>
<div class="win win-warp2" style="display:none;" id="pop_generate">
	<div class="win-title">
		<span class="title-text">生成方式</span><a title="关闭" class="closeBtn"></a>
	</div>
	<div class="content2">
		<div class="msg buttons">
			<input type="button" value="覆盖同名文件" class="btn-ok closePop btn_confirm_generate" delete="0"/>&nbsp;
			<input type="button" value="重新生成(先删除)" class="btn-ok closePop btn_confirm_generate" delete="1"/>
		</div>
		<!-- <div class="buttons">
			<input type="button" value="取消" class="btn-cancel closePop"/>
		</div> -->
	</div>
</div>	
</body>


</html>
