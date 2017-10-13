<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<title>方法参数</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>方法参数</h5>
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
						<form role="form" class="form-horizontal" method="post" action="generatorMethodParam!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorMethodParam.id" value='<s:property value="bean.generatorMethodParam.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">参数名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorMethodParam.code" value='<s:property value="bean.generatorMethodParam.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									如：userId
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorMethodParam.name" value='<s:property value="bean.generatorMethodParam.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Description -->
							<div class="form-group">
								<label class="col-sm-2 control-label">备注:</label>
								<div class="col-sm-5">
									<input name="bean.generatorMethodParam.description" value='<s:property value="bean.generatorMethodParam.description"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ParamMapType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">映射类型:</label>
								<div class="col-sm-5">
			<select name="bean.generatorMethodParam.paramMapType" class="form-control m-b">
				<option value="@QueryParam" ${generatorMethodParam.paramMapType=='@QueryParam'?'selected="selected"':'' }>@QueryParam</option>
				<option value="@PathParam" ${generatorMethodParam.paramMapType=='@PathParam'?'selected="selected"':'' }>@PathParam</option>
			</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- ParamMapValue -->
							<div class="form-group">
								<label class="col-sm-2 control-label">映射名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorMethodParam.paramMapValue" value='<s:property value="bean.generatorMethodParam.paramMapValue"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- DataType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">数据类型:</label>
								<div class="col-sm-5">
			<select name="bean.generatorMethodParam.dataType" class="select_type form-control m-b">
				<s:if test="generatorEntitys==null || generatorEntitys.size()==0">
				<s:iterator value="dataTypes" var="item" status="s">
					<option value="${item.code }" 
					${bean.generatorMethodParam.dataType==item.code?'selected="selected"':'' }>${item.code }</option>
				</s:iterator>
				</s:if>
				<!-- 实体类型 -->
				<s:if test="generatorEntitys==null || generatorEntitys.size()==0">
					<s:iterator value="generatorEntitys" var="item" status="s">
				<option type="diy" oid="${item.id }" value="${item.code }" ${bean.generatorMethodParam.dataType==item.code?'selected="selected"':'' }>${item.code }(${item.name==null?item.code:item.name })</option>
					</s:iterator>
				</s:if>
			</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- MethodId -->
							<div class="form-group">
								<label class="col-sm-2 control-label">所属方法:</label>
								<div class="col-sm-5">
			<select name="bean.generatorMethodParam.methodId" class="form-control m-b">
				<s:if test="generatorServiceMethods==null || generatorServiceMethods.size()==0">
				<s:iterator value="generatorServiceMethods" var="item" status="s">
					<option value="${item.id }" ${bean.generatorMethodParam.methodId==item.id?'selected="selected"':'' }>${item.name }</option>
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
