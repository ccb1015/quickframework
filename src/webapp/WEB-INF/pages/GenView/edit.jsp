<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>GenView</title>
<jsp:include page="/base.jsp"></jsp:include>
<style>
.droppable-active {	background-color: #ffe !important}
.tools a {cursor: pointer;font-size: 80%}
.form-body .col-md-6, .form-body .col-md-12 {min-height: 400px}
.draggable {cursor: move}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-5">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>元素</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="form_editors.html#"> <i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="form_editors.html#">选项1</a></li>
								<li><a href="form_editors.html#">选项2</a></li>
							</ul>
							<a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="alert alert-info">
							拖拽左侧的表单元素到右侧区域，即可生成相应的HTML代码，表单代码，轻松搞定！</div>
						<form role="form" class="form-horizontal m-t">
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">文本框：</label>
								<div class="col-sm-9">
									<input type="text" name="" class="form-control"
										placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span>
								</div>
							</div>
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">密码框：</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" name="password"
										placeholder="请输入密码">
								</div>
							</div>
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">下拉列表：</label>
								<div class="col-sm-9">
									<select class="form-control" name="">
										<option>选项 1</option>
										<option>选项 2</option>
										<option>选项 3</option>
										<option>选项 4</option>
									</select>
								</div>
							</div>
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">文件域：</label>
								<div class="col-sm-9">
									<input type="file" name="" class="form-control">
								</div>
							</div>
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">纯文本：</label>

								<div class="col-sm-9">
									<p class="form-control-static">这里是纯文字信息</p>
								</div>
							</div>
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">单选框： </label>

								<div class="col-sm-9">
									<label class="radio-inline"> <input type="radio"
										checked="" value="option1" id="optionsRadios1"
										name="optionsRadios">选项1
									</label> <label class="radio-inline"> <input type="radio"
										value="option2" id="optionsRadios2" name="optionsRadios">选项2
									</label>

								</div>
							</div>
							<div class="form-group draggable">
								<label class="col-sm-3 control-label">复选框：</label>

								<div class="col-sm-9">
									<label class="checkbox-inline"> <input type="checkbox"
										value="option1" id="inlineCheckbox1">选项1
									</label> <label class="checkbox-inline"> <input type="checkbox"
										value="option2" id="inlineCheckbox2">选项2
									</label> <label class="checkbox-inline"> <input type="checkbox"
										value="option3" id="inlineCheckbox3">选项3
									</label>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group draggable">
								<div class="col-sm-12 col-sm-offset-3">
									<button class="btn btn-primary" type="submit">保存内容</button>
									<button class="btn btn-white" type="submit">取消</button>
								</div>
							</div>
						</form>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-7">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>拖拽左侧表单元素到此区域</h5>
						<div class="ibox-tools">
							请选择显示的列数： <select id="n-columns">
								<option value="1">显示1列</option>
								<option value="2">显示2列</option>
							</select>
						</div>
					</div>

					<div class="ibox-content">
						<div class="row form-body form-horizontal m-t">
							<div class="col-md-12 droppable sortable"></div>
							<div class="col-md-6 droppable sortable" style="display: none;">
							</div>
							<div class="col-md-6 droppable sortable" style="display: none;">
							</div>
						</div>
						<button type="submit" class="btn btn-warning"
							data-clipboard-text="testing" id="copy-to-clipboard">复制代码</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="<%=request.getContextPath()%>/resources/js/view_builder/view_builder.js?v=1.1.0"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.10.4.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/plugins/beautifyhtml/beautifyhtml.js"></script>
<script type="text/javascript">
	$(function(){
		initUIs();
	})
	function initUIs(){
		jQuery.post("genView!loadUis.action",{"uiTypeId":1},function(result){
			if(result.success==true){
				var data = result.params.data;
				if(data instanceof Array){
					jQuery.each(data,function(i,item){
						debugger
					});
					//jQuery(target).html(options);
				}
			}else{
				alert(result.message);
			}
		});
	}


</script>	
	
	
</body>
</html>
