<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>WEB服务器</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>WEB服务器</h5>
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
						<form role="form" class="form-horizontal" method="post" action="SRWebConfig!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorWebserver.id" value='<s:property value="bean.generatorWebserver.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorWebserver.code" value='<s:property value="bean.generatorWebserver.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorWebserver.name" value='<s:property value="bean.generatorWebserver.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="hr-line-dashed"></div>
							<!-- Port -->
							<div class="form-group">
								<label class="col-sm-2 control-label">端口:</label>
								<div class="col-sm-5">
									<input name="bean.generatorWebserver.port" value='<s:property value="bean.generatorWebserver.port"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<!-- Url -->
							<div class="form-group">
								<label class="col-sm-2 control-label">发布路径:</label>
								<div class="col-sm-5">
									<input name="bean.generatorWebserver.url" value='<s:property value="bean.generatorWebserver.url"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									如：/security
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
