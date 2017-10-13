<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>工程编辑</title>
<jsp:include page="/base.jsp"></jsp:include>
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
								<!-- <li><a href="javascript:;">选项1</a></li> -->
							</ul>
							<a class="close-link"><i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<form role="form" class="form-horizontal" method="post" action="SRProjectConfig!editDo.action">
							<!-- id -->		
					        <input type="hidden" name="bean.generatorProject.id" value='<s:property value="bean.generatorProject.id"/>'/>
							<!-- property -->
							<div class="form-group">
								<label class="col-sm-2 control-label">Path:</label>
								<div class="col-sm-5">
									<input name="bean.generatorProject.path" value='<s:property value="bean.generatorProject.path"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									输出代码存放路径（磁盘绝对路径，如：d:/demo）
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">工程名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorProject.code" value='<s:property value="bean.generatorProject.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									生成项目的名称(如：demo)
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">工程描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorProject.name" value='<s:property value="bean.generatorProject.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">basepackage:</label>
								<div class="col-sm-5">
									<input name="bean.generatorProject.basepackage" value='<s:property value="bean.generatorProject.basepackage"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									包名前缀（如：com.bonc.demo）
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">模版名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorProject.temaplateName" value='<s:property value="bean.generatorProject.temaplateName"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									模版文件的路径（webapp根目录的相对路径，如：templates/my_mvn_template）
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">数据库服务器:</label>
								<div class="col-sm-5">
									<select name="bean.generatorProject.dbserverId" class="form-control m-b">
										<s:if test="dbServers==null || dbServers.size()==0">
										<s:iterator value="dbServers" var="item" status="s">
											<option value="${item.id }" ${bean.generatorProject.dbserverId==item.id?'selected="selected"':'' }>${item.name }</option>
										</s:iterator>
										</s:if>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">WEB服务器:</label>
								<div class="col-sm-5">
									<select name="bean.generatorProject.webserverId" class="form-control m-b">
										<s:if test="webServers==null || webServers.size()==0">
										<s:iterator value="webServers" var="item" status="s">
											<option value="${item.id }" ${bean.generatorProject.dbserverId==item.id?'selected="selected"':'' }>${item.name }</option>
										</s:iterator>
										</s:if>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
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
