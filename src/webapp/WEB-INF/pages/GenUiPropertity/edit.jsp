<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>GenUiPropertity</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>GenUiPropertity</h5>
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
						<form role="form" class="form-horizontal" method="post" action="genUiPropertity!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.genUiPropertity.id" value='<s:property value="bean.genUiPropertity.id"/>'/>
							<!-- property -->
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">name:</label>
								<div class="col-sm-5">
									<input name="bean.genUiPropertity.name" value='<s:property value="bean.genUiPropertity.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- TypeId -->
							<div class="form-group">
								<label class="col-sm-2 control-label">typeId:</label>
								<div class="col-sm-5">
									<input name="bean.genUiPropertity.typeId" value='<s:property value="bean.genUiPropertity.typeId"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- PropKey -->
							<div class="form-group">
								<label class="col-sm-2 control-label">propKey:</label>
								<div class="col-sm-5">
									<input name="bean.genUiPropertity.propKey" value='<s:property value="bean.genUiPropertity.propKey"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- PropValue -->
							<div class="form-group">
								<label class="col-sm-2 control-label">propValue:</label>
								<div class="col-sm-5">
									<input name="bean.genUiPropertity.propValue" value='<s:property value="bean.genUiPropertity.propValue"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Script -->
							<div class="form-group">
								<label class="col-sm-2 control-label">script:</label>
								<div class="col-sm-5">
									<input name="bean.genUiPropertity.script" value='<s:property value="bean.genUiPropertity.script"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- UiId -->
							<div class="form-group">
								<label class="col-sm-2 control-label">uiId:</label>
								<div class="col-sm-5">
									<input name="bean.genUiPropertity.uiId" value='<s:property value="bean.genUiPropertity.uiId"/>' class="form-control"/>
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
