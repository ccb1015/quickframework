<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>GeneratorUi</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>UI组件</h5>
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
						<form role="form" class="form-horizontal" method="post" action="generatorUi!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorUi.id" value='<s:property value="bean.generatorUi.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">编码:</label>
								<div class="col-sm-5">
									<input name="bean.generatorUi.code" value='<s:property value="bean.generatorUi.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorUi.name" value='<s:property value="bean.generatorUi.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Type -->
							<div class="form-group">
								<label class="col-sm-2 control-label">类型:</label>
								<div class="col-sm-5">
									<input name="bean.generatorUi.type" value='<s:property value="bean.generatorUi.type"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Sequence -->
							<div class="form-group">
								<label class="col-sm-2 control-label">排序:</label>
								<div class="col-sm-5">
									<input name="bean.generatorUi.sequence" value='<s:property value="bean.generatorUi.sequence"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Type -->
							<div class="form-group">
								<label class="col-sm-2 control-label">实现类型:</label>
								<div class="col-sm-5">
									<select name="bean.generatorUi.implementType" class="form-control m-b" cu1r="${bean.generatorUi.implementType }">
										<option value="1" ${bean.generatorUi.implementType=='1'?'selected="selected"':'' }>自动生成</option>
										<option value="2" ${bean.generatorUi.implementType=='2'?'selected="selected"':'' }>手动开发</option>
									</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Script -->
							<div class="div_script">
								<div class="form-group">
									<label class="col-sm-2 control-label">模版名称:</label>
									<div class="col-sm-5">
										<input name="bean.generatorUi.script" value='<s:property value="bean.generatorUi.script"/>' class="form-control"/>
										<%-- <textarea class="form-control" style="height: 150px;" name="bean.generatorUi.script"><s:property value="bean.generatorUi.script"/></textarea> --%>
									</div>
									<div class="col-sm-5">
										
									</div>
								</div>
								<div class="hr-line-dashed"></div>
							</div>
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
	<script>
	jQuery(function(){
		jQuery("select[name='bean.generatorUi.implementType']").change(function(){
			if(jQuery(this).val()=="1"){
				jQuery(".div_script").hide();
			}else{
				jQuery(".div_script").show();
			}
		}).change();
	})
	
	</script>
</body>

</html>
