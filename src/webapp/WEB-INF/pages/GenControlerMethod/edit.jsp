<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>GenControlerMethod</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>GenControlerMethod</h5>
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
						<form role="form" class="form-horizontal" method="post" action="genControlerMethod!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.genControlerMethod.id" value='<s:property value="bean.genControlerMethod.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">code:</label>
								<div class="col-sm-5">
									<input name="bean.genControlerMethod.code" value='<s:property value="bean.genControlerMethod.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">name:</label>
								<div class="col-sm-5">
									<input name="bean.genControlerMethod.name" value='<s:property value="bean.genControlerMethod.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ControllerId -->
							<div class="form-group">
								<label class="col-sm-2 control-label">controllerId:</label>
								<div class="col-sm-5">
									<input name="bean.genControlerMethod.controllerId" value='<s:property value="bean.genControlerMethod.controllerId"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- BusinessScript -->
							<div class="form-group">
								<label class="col-sm-2 control-label">businessScript:</label>
								<div class="col-sm-5">
									<input name="bean.genControlerMethod.businessScript" value='<s:property value="bean.genControlerMethod.businessScript"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ScriptType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">scriptType:</label>
								<div class="col-sm-5">
									<input name="bean.genControlerMethod.scriptType" value='<s:property value="bean.genControlerMethod.scriptType"/>' class="form-control"/>
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
