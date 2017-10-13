<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title>数据库服务器</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>数据库服务器</h5>
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
						<form role="form" class="form-horizontal" method="post" action="generatorDbserver!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorDbserver.id" value='<s:property value="bean.generatorDbserver.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorDbserver.code" value='<s:property value="bean.generatorDbserver.code"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Name -->
							<div class="form-group">
								<label class="col-sm-2 control-label">描述:</label>
								<div class="col-sm-5">
									<input name="bean.generatorDbserver.name" value='<s:property value="bean.generatorDbserver.name"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- DbType -->
							<div class="form-group">
								<label class="col-sm-2 control-label">数据库类型:</label>
								<div class="col-sm-5">
									<select name="bean.generatorDbserver.dbType" class="select_db_type form-control m-b">
										<option ${bean.generatorDbserver.dbType == 'mysql'?'selected="selected"':'' }>mysql</option>
										<option ${bean.generatorDbserver.dbType == 'oracle'?'selected="selected"':'' }>oracle</option>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- JdbcUrl -->
							<div class="form-group">
								<label class="col-sm-2 control-label">jdbcUrl:</label>
								<div class="col-sm-5">
									<input name="bean.generatorDbserver.jdbcUrl" value='<s:property value="bean.generatorDbserver.jdbcUrl"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Username -->
							<div class="form-group">
								<label class="col-sm-2 control-label">用户名称:</label>
								<div class="col-sm-5">
									<input name="bean.generatorDbserver.username" value='<s:property value="bean.generatorDbserver.username"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Password -->
							<div class="form-group">
								<label class="col-sm-2 control-label">密码:</label>
								<div class="col-sm-5">
									<input type="password" name="bean.generatorDbserver.password" value='<s:property value="bean.generatorDbserver.password"/>' class="form-control"/>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Dialect -->
							<div class="form-group">
								<label class="col-sm-2 control-label">方言:</label>
								<div class="col-sm-5">
									<select name="bean.generatorDbserver.dialect" class="select_dialect  form-control m-b">
										<s:if test="bean.generatorDbserver.dbType=='mysql'">
											<option ${bean.generatorDbserver.dialect == 'org.hibernate.dialect.MySQL5Dialect'?'selected="selected"':'' }>org.hibernate.dialect.MySQL5Dialect</option>
											<option ${bean.generatorDbserver.dialect == 'org.hibernate.dialect.MySQLInnoDBDialect'?'selected="selected"':'' }>org.hibernate.dialect.MySQLInnoDBDialect</option>
										</s:if>
										<s:if test="bean.generatorDbserver.dbType=='oracle'">
											<option>org.hibernate.dialect.Oracle10gDialect</option>
										</s:if>
									</select>
								</div>
								<div class="col-sm-5">
									
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- DriverClass -->
							<div class="form-group">
								<label class="col-sm-2 control-label">驱动类:</label>
								<div class="col-sm-5">
									<select name="bean.generatorDbserver.driverClass" class="select_driver_class form-control m-b">
										<s:if test="bean.generatorDbserver.dbType=='mysql'">
											<option>com.mysql.jdbc.Driver</option>
										</s:if>
										<s:if test="bean.generatorDbserver.dbType=='oracle'">
											<option>oracle.jdbc.driver.OracleDriver</option>
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
<script>
	jQuery(function(){
		jQuery(".select_db_type").change(function(){
			var type=jQuery(this).val();
			if(type=='mysql'){
				jQuery(".select_driver_class").html("<option>com.mysql.jdbc.Driver</option>");
				jQuery(".select_dialect").html("<option>org.hibernate.dialect.MySQL5Dialect</option><option>org.hibernate.dialect.MySQLInnoDBDialect</option>");
				
			}else if(type=='oracle'){
				jQuery(".select_driver_class").html("<option>oracle.jdbc.driver.OracleDriver</option>");
				jQuery(".select_dialect").html("<option>org.hibernate.dialect.Oracle10gDialect</option>");
			}
		}).change();
	});
</script>
</body>

</html>
