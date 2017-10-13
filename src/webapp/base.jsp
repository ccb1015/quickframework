<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!--------------boncfront------start----------- -->
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<!-- Data Tables -->
<link href="<%=request.getContextPath()%>/resources/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/style.min.css?v=4.0.0" rel="stylesheet">

<!-- <style>.gray-bg{background-color:#fff}</style> -->
<!-- js -->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js?v=3.3.5"></script>
<!-- <script src="/resources/js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="/resources/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="/resources/js/plugins/dataTables/dataTables.bootstrap.js"></script> -->
<script src="<%=request.getContextPath()%>/resources/js/content.min.js?v=1.0.0"></script>
<script src="<%=request.getContextPath()%>/resources/js/plugins/layer/laydate/laydate.js?v=1.1.0"></script>

<!--------------boncfront------end----------- -->

<!-- 提示 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/common.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/myblockUI.js"></script>
 
 <script type="text/javascript">
	jQuery(function(){
		bindDataListEvent();//列表操作事件
	});
	function bindDataListEvent(){
		jQuery(".ck_all").on("click",function(){
			jQuery(this).parents("table").first().find(".ck_item").prop("checked",jQuery(this).is(":checked"));
		});
		jQuery(".btn_delete_selected").on("click",function(){
			var url=jQuery(this).attr("url");
			var cks = jQuery(".ck_item:checked");
			if(cks.length==0){
				alert("没有选择任何数据");
				return false;
			}
			DelConfirm("确定删除？",function(){
				var ids=[];
				cks.each(function(){
					var id = jQuery(this).val();
					if(id)ids.push(id);
				});
				deleteDo(url+"?bean.idList="+ids.join(","));
			});
		});
		jQuery(".btn_delete_item").on("click",function(){
			var url=jQuery(this).attr("url");
			DelConfirm("确定删除？",function(){
				deleteDo(url);
			});
		});
		function deleteDo(url){
			jQuery.post(url,{},function(data){
				if(data.success==true){
					jQuery(".form_search").submit();
				}else{
					alert(data.message);
				}
			});
		}
	}
</script>