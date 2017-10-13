<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>服务方法</title>
<jsp:include page="/base.jsp"></jsp:include>
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
								<!-- <li><a href="javascript:;">éé¡¹1</a></li> -->
							</ul>
							<a class="close-link"><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<form role="form" class="form-horizontal" method="post" action="generatorServiceMethod!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorServiceMethod.id" value='<s:property value="bean.generatorServiceMethod.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">方法名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorServiceMethod.code" value='<s:property value="bean.generatorServiceMethod.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									如： add
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorServiceMethod.name" value='<s:property value="bean.generatorServiceMethod.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Consumes -->
							<div class="form-group">
								<label class="col-sm-2 control-label">consumes:</label>
								<div class="col-sm-5">
			<select name="bean.generatorServiceMethod.consumes" class="form-control m-b">
				<option ${bean.generatorServiceMethod.consumes=='application/json'?'selected="selected"':'' }>application/json</option>
				<option ${bean.generatorServiceMethod.consumes=='application/xml'?'selected="selected"':'' }>application/xml</option>
			</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- HttpMethod -->
							<div class="form-group">
								<label class="col-sm-2 control-label">访问方式:</label>
								<div class="col-sm-5">
			<select name="bean.generatorServiceMethod.httpMethod" class="form-control m-b">
				<option value="GET" ${bean.generatorServiceMethod.httpMethod=='GET'?'selected="selected"':'' }>GET</option>
				<option value="POST" ${bean.generatorServiceMethod.httpMethod=='POST'?'selected="selected"':'' }>POST</option>
			</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Producer -->
							<div class="form-group">
								<label class="col-sm-2 control-label">producer:</label>
								<div class="col-sm-5">
			<select name="bean.generatorServiceMethod.producer" class="form-control m-b">
				<option ${bean.generatorServiceMethod.producer=='application/json'?'selected="selected"':'' }>application/json</option>
				<option ${bean.generatorServiceMethod.producer=='application/xml'?'selected="selected"':'' }>application/xml</option>
			</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- SubPath -->
							<div class="form-group">
								<label class="col-sm-2 control-label">访问子路径:</label>
								<div class="col-sm-5">
									<input name="bean.generatorServiceMethod.subPath" value='<s:property value="bean.generatorServiceMethod.subPath"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									如：/add
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ReturnType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">返回类型:</label>
								<div class="col-sm-5">
									<input name="bean.generatorServiceMethod.returnType" value='<s:property value="bean.generatorServiceMethod.returnType"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									如：String
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ServiceId -->
							<div class="form-group">
								<label class="col-sm-2 control-label">所属服务:</label>
								<div class="col-sm-5">
			<select name="bean.generatorServiceMethod.serviceId" class="form-control m-b">
				<s:if test="generatorServices==null || generatorServices.size()==0">
				<s:iterator value="generatorServices" var="item" status="s">
					<option value="${item.id }" ${bean.generatorServiceMethod.serviceId==item.id?'selected="selected"':'' }>${item.name }</option>
				</s:iterator>
				</s:if>
			</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- BusinessLogic -->
							<div class="form-group">
								<label class="col-sm-2 control-label">业务逻辑:</label>
								<div class="col-sm-5">
									<textarea rows="30" cols="100" name="bean.generatorServiceMethod.businessLogic">${bean.generatorServiceMethod.businessLogic }</textarea>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- button -->
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">提交</button>&nbsp;
									<button class="btn btn-white" type="button" onclick="window.history.back()">取消</button>
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
