<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<!-- Data Tables -->
<link href="<%=request.getContextPath()%>/resources/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/animate.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/style.min.css?v=4.0.0" rel="stylesheet">

<!-- js -->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js?v=3.3.5"></script>
<!-- <script src="/resources/js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="/resources/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="/resources/js/plugins/dataTables/dataTables.bootstrap.js"></script> -->
<script src="<%=request.getContextPath()%>/resources/js/content.min.js?v=1.0.0"></script>
<script src="<%=request.getContextPath()%>/resources/js/plugins/layer/laydate/laydate.js?v=1.1.0"></script>

<!-- 提示 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/common.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/myblockUI.js"></script>
 
<script type="text/javascript">
	jQuery(function(){
		bindDataListEvent();//列表操作事件
		bindValidateEvent();//绑定验证事件
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
	//
	function bindValidateEvent(){
		// Chrome
		var checkvalue = function(e){
			 e = e ? e : window.event
             var el = e.target || e.srcElement;;
             var isvalid = el.checkValidity();
             if(isvalid){
                 jQuery(el).removeClass("error");
             }else{
            	 jQuery(el).addClass("error");
             }
             e.stopPropagation();
             e.preventDefault();
         }
         function invalidHandler() {
            jQuery("form").each(function(){
            	var myform=jQuery(this)[0];
	           	addHandler(myform,"invalid", checkvalue, true);
	             for(var i=0;i< myform.elements.length-1;i++){
	                 addHandler(myform.elements[i],"change",checkvalue,false);
	                 
	             }
            });
          }
         addHandler(window,"load",invalidHandler,false);
         
         function addHandler(element, type, handler,bubble) {
             if (element.addEventListener) {
                 element.addEventListener(type, handler, bubble);
             } else if (element.attachEvent) {
                 element.attachEvent("on" + type, handler);
             } else {
                 element["on" + type] = handler;
             }
         } 
         //IE
        jQuery("form").each(function(){
         	var myform=jQuery(this)[0];
            for(var i=0;i< myform.elements.length-1;i++){
            	jQuery(myform.elements[i]).change(function(){
	            	var ele=jQuery(this);
	            	ele.removeClass("error");
	            	if(ele.attr("required")=="required" && !ele.val()){
	            		ele.addClass("error");
	            	}
	            	var regStr=ele.attr("pattern");
	            	if(regStr){
	            		var value=ele.val();
	            		var reg = eval("/^"+regStr+"$/");
	            		if(!reg.test(value)){
	            			ele.addClass("error");
	            		}
	            	}
            	})
            }
        });
		jQuery("form").on("submit",function(){
			var myform=jQuery(this)[0];
            for(var i=0;i< myform.elements.length-1;i++){
            	jQuery(myform.elements[i]).change();
            	if(jQuery(myform.elements[i]).hasClass("error")){
            		return false;
            	}
            }
			return true;
		});
	}
</script>