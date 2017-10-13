<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>quickframework</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							GENERATOR TOOL
						</div>
						<div class="logo-element">
							TOOL
						</div>
					</li>
					<li>
						<a href="javascript:;"><i class="fa fa-home"></i> <span
							class="nav-label">主页</span> <span class="fa arrow"></span>
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-desktop"></i>
							<span class="nav-label">服务器</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="generatorDbserver!list.action">数据库服务器管理</a></li>
							<li><a class="J_menuItem" href="generatorWebserver!list.action">WEB服务器管理</a></li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-table"></i>
							<span class="nav-label">基础信息</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="generatorDataType!list.action">数据类型管理</a></li>
							<li><a class="J_menuItem" href="generatorUi!list.action">UI组件</a></li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-edit"></i>
							<span class="nav-label">工程信息 </span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="generatorProject!list.action">工程管理</a></li>
							<li><a class="J_menuItem" href="generatorEntity!list.action">实体管理</a></li>
							<li><a class="J_menuItem" href="generatorField!list.action">字段管理</a></li>
							<li><a class="J_menuItem" href="generatorService!list.action">服务管理</a></li>
							<li><a class="J_menuItem" href="generatorServiceMethod!list.action">服务方法管理</a></li>
							<li><a class="J_menuItem" href="generatorMethodParam!list.action">方法参数管理</a></li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="fa fa-edit"></i>
							<span class="nav-label">界面设计</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="genTemplate!list.action">模版管理</a></li>
							<li><a class="J_menuItem" href="genUi!list.action">组件管理</a></li>
							<li><a class="J_menuItem" href="genUiDetail!list.action">组件详细</a></li>
							<li><a class="J_menuItem" href="genUiPropertity!list.action">组件属性</a></li>
							<li><a class="J_menuItem" href="genPropType!list.action">属性类型</a></li>
							<li><a class="J_menuItem" href="genModel!list.action">Model</a></li>
							<li><a class="J_menuItem" href="genModelProperty!list.action">Model属性</a></li>
							<li><a class="J_menuItem" href="genController!list.action">Controller</a></li>
							<li><a class="J_menuItem" href="genControlerMethod!list.action">Controller方法</a></li>
							<li><a class="J_menuItem" href="genView!list.action">View</a></li>
						</ul>
					</li>

				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
						<!-- <form role="search" class="navbar-form-custom" method="post"
							action="search_results.html">
							<div class="form-group">
								<input type="text" placeholder="请输入您需要查找的内容 …"
									class="form-control" name="top-search" id="top-search">
							</div>
						</form> -->
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i>
								<span class="label label-warning">0</span>
						</a>
							<ul class="dropdown-menu dropdown-messages">
								<li class="m-t-xs">
									<div class="dropdown-messages-box">
										<a href="profile.html" class="pull-left"> <img alt="image"
											class="img-circle" src="img/a7.jpg">
										</a>
										<div class="media-body">
											<small class="pull-right">46小时前</small> <strong>小四</strong>
											这个在日本投降书上签字的军官，建国后一定是个不小的干部吧？ <br> <small
												class="text-muted">3天前 2014.11.8</small>
										</div>
									</div>
								</li>

								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										<a class="J_menuItem" href="mailbox.html"> <i
											class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
										</a>
									</div>
								</li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
								class="label label-primary">8</span>
						</a>
							<ul class="dropdown-menu dropdown-alerts">
								<li><a href="mailbox.html">
										<div>
											<i class="fa fa-envelope fa-fw"></i> 您有16条未读消息 <span
												class="pull-right text-muted small">4分钟前</span>
										</div>
								</a></li>
								<li class="divider"></li>
								<li><a href="profile.html">
										<div>
											<i class="fa fa-qq fa-fw"></i> 3条新回复 <span
												class="pull-right text-muted small">12分钟前</span>
										</div>
								</a></li>
								<li class="divider"></li>
								<li>
									<div class="text-center link-block">
										<a class="J_menuItem" href="notifications.html"> <strong>查看所有
										</strong> <i class="fa fa-angle-right"></i>
										</a>
									</div>
								</li>
							</ul></li>
						<li class="dropdown hidden-xs"><a
							class="right-sidebar-toggle" aria-expanded="false"> <i
								class="fa fa-tasks"></i> 设置
						</a></li>
					</ul>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab"
							data-id="index_v1.html">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>
					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a data-toggle="dropdown" class="roll-nav roll-right J_tabExit"
					href="#"> <span class="clear"> <span
						class="text-muted text-xs block">管理员<b class="caret"></b></span>
				</span>
				</a>
				<ul role="menu" class="dropdown-menu dropdown-menu-right">
					<li><a class="J_menuItem" href="form_avatar.html">修改头像</a></li>
					<li><a class="J_menuItem" href="profile.html">个人资料</a></li>
					<li><a class="J_menuItem" href="contacts.html">联系人</a></li>
					<li><a class="J_menuItem" href="mailbox.html">信箱</a></li>
					<li class="divider"></li>
					<li><a href="login.html">安全退出</a></li>
				</ul>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="javascript:;" frameborder="0" data-id="javascript:;"
					seamless></iframe>
			</div>
			<div class="footer">
				<div class="pull-right">
					&copy; 2014-2015 <a href="http://www.bonc.com.cn/" target="_blank">BONC</a>
				</div>
			</div>
		</div>
	</div>
	<script src="resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="resources/js/plugins/layer/layer.min.js"></script>
	<script src="resources/js/boncfront.min.js?v=4.0.0"></script>
	<script src="resources/js/contabs.min.js"></script>
	<script src="resources/js/plugins/pace/pace.min.js"></script>
</body>
</html>
