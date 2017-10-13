<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>WEB服务</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>WEB服务</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
							<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;"><i class="fa fa-wrench"></i></a>
							<ul class="dropdown-menu dropdown-user">
								<!-- <li><a href="javascript:;">选项1</a></li> -->
							</ul>
							<a class="close-link"><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<form role="form" class="form-horizontal" method="post" action="generatorService!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorService.id" value='<s:property value="bean.generatorService.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">服务名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorService.code" value='<s:property value="bean.generatorService.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorService.name" value='<s:property value="bean.generatorService.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<%-- <!-- ImplType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">实现类型:</label>
								<div class="col-sm-5">
									<input name="bean.generatorService.implType" value='<s:property value="bean.generatorService.implType"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Uri -->
							<div class="form-group">
								<label class="col-sm-2 control-label">访问路径:</label>
								<div class="col-sm-5">
									<input name="bean.generatorService.uri" value='<s:property value="bean.generatorService.uri"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div> --%>
							<!-- ServiceType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">服务类型:</label>
								<div class="col-sm-5">
								<select name="bean.generatorService.serviceType" class="form-control m-b">
									<option value="1" ${bean.generatorService.serviceType=='1'?'selected="selected"':'' }>SOAP</option>
									<option value="2" ${bean.generatorService.serviceType=='2'?'selected="selected"':'' }>REST</option>
								</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Path -->
							<div class="form-group">
								<label class="col-sm-2 control-label">访问路径:</label>
								<div class="col-sm-5">
									<input name="bean.generatorService.path" value='<s:property value="bean.generatorService.path"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									如：/queryTest
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ProjectId -->
							<div class="form-group">
								<label class="col-sm-2 control-label">所属项目:</label>
								<div class="col-sm-5">
								<select name="bean.generatorService.projectId" class="form-control m-b">
									<s:if test="generatorProjects==null || generatorProjects.size()==0">
									<s:iterator value="generatorProjects" var="item" status="s">
										<option value="${item.id }" ${bean.generatorService.projectId==item.id?'selected="selected"':'' }>${item.name }</option>
									</s:iterator>
									</s:if>
								</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- button -->
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">提交</button>&nbsp;
									<button class="btn btn-white" type="button" onclick="window.history.back()">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
