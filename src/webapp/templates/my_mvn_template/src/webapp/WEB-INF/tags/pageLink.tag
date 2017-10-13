<#include "/macro.include"/> 
<#include "/custom.include"/> 
<%-- 
    用途   : 页面分页标签
    创建于 : 2015-4-29
    作者     : 王邦东 
--%>
<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib prefix="s" uri="/struts-tags"%>

<%@ attribute name="totalCount" required="true" rtexprvalue="true" type="java.lang.Long" %> 
<%@ attribute name="pageNo" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="pageSize" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="type" required="false" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="ajax" required="false" rtexprvalue="true" type="java.lang.Boolean" %>
<%@ attribute name="ajaxRequestFun" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>
 <%@ attribute name="align" required="false" rtexprvalue="true" type="java.lang.String" %>
 <%@ attribute name="linkId" required="false" rtexprvalue="true" type="java.lang.String" %> 
  <%@ attribute name="getParamFun" required="false" rtexprvalue="true" type="java.lang.String" %> 
 
 <%@ tag  
	import="
		javax.servlet.jsp.tagext.JspTag, 
		javax.servlet.jsp.tagext.SimpleTagSupport,
		java.util.*,  
		net.sf.json.JSONObject	 
		"
		%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/css/common.css" />
 --%>
<%
	if(align==null)align="right"; 
	if(ajax==null)ajax=false;
	if(type==null)type=1;

 long randId=System.currentTimeMillis()+ java.lang.Math.round(java.lang.Math.random()*10000L);
 StringBuffer sb=new StringBuffer();
 StringBuffer title=new StringBuffer();
 Map _param=new HashMap();
 int pageCount =(int) (totalCount + pageSize - 1) /pageSize;
 Enumeration<String> enumeration = request.getParameterNames();  
 String name = null;  //参数名  
 String value = null; //参数值  
 //把请求中的所有参数当作隐藏表单域  
 
 while (enumeration.hasMoreElements()) {  
     name =  enumeration.nextElement();  
     value = request.getParameter(name);  
     _param.put(name, value);        
 }  
%>
<style>
	#pagediv<%=randId%>{
		font-familay:宋体;
		font-size:12px;
	}
	#pagediv<%=randId%> button{
		font-color:#666666;		 
		height:26px;
		/* border: #bcbcbc  1px solid;*/
		border: #C9C9C9  1px solid;
		background-color: #fff;
		margin-left:2px;
		margin-right:2px;
		padding:2px;
		width:48px;
		 border-radius:3px;
		 font-size:11px;
		 cursor:pointer;
		 
	}
	#pagediv<%=randId%> button[disabled]{
		color: inherit;
  		border: #fff  1px solid;
		background-color: #fff;
		cursor:default;
	}
	#pagediv<%=randId%> input[type="text"]{
	    height: 26px;
		width: 26px;
		padding:0 2px;
	    margin: 0 2px;
        border: #D3D3D3 thin solid;
        border-radius: 3px;
        vertical-align: middle;
	}
</style>
 <div id='pagediv<%=randId %>' class= "pagelink"  style="float:<%=align%>"> 
 
 </div> 
 <div style="clear:both;"></div>
 <script type="text/javascript">
 
	<%out.print("if(!window._pageInfo_"+randId+"){window._pageInfo_"+randId+"={};};");
	int i=0;
	for(Object n:_param.keySet()){
		i++;
		Map m=new HashMap();
		m.put("value",_param.get(n));
		 
		out.print("window._pageInfo_"+randId+"['"+n+"']="+ JSONObject.fromObject(m).toString() +".value;");
	}
	%>
	window._pageInfo_<%=randId%>.setTotalCount=function(c){
		window._pageInfo_<%=randId%>.totalCount=parseInt(c,10);
		
		window._pageInfo_<%=randId%>.pageCount=parseInt(c,10)%parseInt(window._pageInfo_<%=randId%>.pageSize,10)==0?parseInt(c,10)/parseInt(window._pageInfo_<%=randId%>.pageSize,10):parseInt(parseInt(c,10)/parseInt(window._pageInfo_<%=randId%>.pageSize,10))+1;
		buildPage<%=randId%>();
	}
	
	window._pageInfo_<%=randId%>.pageCount=parseInt(<%=pageCount%>,10);
	window._pageInfo_<%=randId%>.pageNo=parseInt(<%=pageNo%>,10);
	window._pageInfo_<%=randId%>.totalCount=parseInt(<%=(totalCount<0?0:totalCount)%>,10);
	window._pageInfo_<%=randId%>.pageSize=parseInt(<%=pageSize%>,10);
	
	function turnToPage<%=randId%>(no,pageSize){ 
		 no=parseInt(no,10);
		 if(!(pageSize&&parseInt(pageSize,10)!=parseInt(window._pageInfo_<%=randId%>.pageSize,10))){ 
			 if(no>window._pageInfo_<%=randId%>.pageCount){ 
			  	no=window._pageInfo_<%=randId%>.pageCount;
			 } 
		 }
		if(no<1){no=1;} 
		 window._pageInfo_<%=randId%>['bean.pageInfo.currentPage']= no; 
		 window._pageInfo_<%=randId%>['bean.pageInfo.pageSize']=pageSize?parseInt(pageSize,10):parseInt(window._pageInfo_<%=randId%>.pageSize,10);
		 if(pageSize){
			 window._pageInfo_<%=randId%>['pageSize']=parseInt(pageSize,10);
		 } 
		 window._pageInfo_<%=randId%>['bean.pageInfo.totalCount']=parseInt( window._pageInfo_<%=randId%>.totalCount,10);
		 window._pageInfo_<%=randId%>['pageNo']= no; 
		 
		 var url='<%=action%>';
		 url+=url.indexOf("?")!=-1?"":"?_random="+new Date().getTime();
		 var getParams={};
		 <%if(getParamFun!=null){%>
			try{ 
				  getParams=eval("<%=getParamFun%>()")
				for(var p1 in getParams){				
						url+='&'+p1+'='+getParams[p1]; 
				}
			}catch(e){
				alert(e.message)
			}
		<%}%>
		 
		  for(var p in window._pageInfo_<%=randId%>){ 
			  if(getParams[p]===undefined)
			   if(p!="setTotalCount")
		       		url+='&'+p+'='+((window._pageInfo_<%=randId%>[p])); 
		    } 
		
		if(!<%=ajax%>){   
		       window.location.href=(url);  
		}else{
			if('<%=ajaxRequestFun%>'!='null'){
				eval('ajaxRequest<%=randId%>(url)');
			}			
		}	
	 } 
	String.prototype.trim=function() {  
	    return this.replace(/(^\s*)|(\s*$)/g,'');  
	};  
	function jumpToPage<%=randId%>(pageno,pageSize){
			 var re = /^[0-9]+.?[0-9]*$/;
			 
		 	if(!re.test(pageno)){
		 		pageno=window._pageInfo_<%=randId%>.pageNo;
		 		/* alert("请输入要跳转的页号")
		 		return false; */
		 	}
		 	if(pageSize){ 
			 	if(!re.test(pageSize)){
			 		alert("请输入每页记录数")
			 		return false;
			 	}
		 	}
		 	if(parseInt(pageSize,10)>100||parseInt(pageSize,10)<=0){
		 		alert("每页记录数值范围：1~100");
		 		return false;
		 	}
			var totalPage= (parseInt(window._pageInfo_<%=randId%>.totalCount ,10)+ parseInt(pageSize,10) - 1) /parseInt(pageSize,10);
		 	pageno=pageno>=totalPage?totalPage:pageno;
	 
			turnToPage<%=randId%>(pageno,pageSize);  
		 
	}
	function ajaxRequest<%=randId%>(url){
	  
		 <%out.write( ajaxRequestFun==null?"": ajaxRequestFun+"(url, window._pageInfo_"+randId+");");%>
		 
	 	
	}
	function buildPage<%=randId%>(){
		if(<%=type%>==1){


			if(window._pageInfo_<%=randId%>.totalCount<=0){
				jQuery("#pagediv<%=randId%>").html("<button disabled  class=\"disabled\">首页</button><button  disabled class=\"disabled\">上一页</button>"+
						"<button  disabled class=\"disabled\">下一页</button><button disabled  class=\"disabled\">尾页</button>"+
						"<span>每页<input type='text' id='xxx_page_size<%=randId%>' onkeydown='inputKeyDown<%=randId%>(event)'>条 "+
						"&nbsp;&nbsp;跳转到<input type='text' id='xxx_to_page<%=randId%>' onkeydown='inputKeyDown<%=randId%>(event)'>页&nbsp;&nbsp;"+
						"<button style=\"background-color:#F3791E;color:#fff;width:30px;border: 0;\"  disabled class=\"disabled\">go</button>&nbsp;&nbsp;【 第 0/0页 , 共 0条记录 】</span>")
				 
			}else{  
				if(window._pageInfo_<%=randId%>.pageNo>window._pageInfo_<%=randId%>.pageCount){
					window._pageInfo_<%=randId%>.pageNo=window._pageInfo_<%=randId%>.pageCount
				}
				if(window._pageInfo_<%=randId%>.pageNo<1){
					window._pageInfo_<%=randId%>.pageNo=1
				}			    
				var htmls=[];
				    
			     
				
				 
				if(window._pageInfo_<%=randId%>.pageNo==1){
					htmls.push("<button disabled  class=\"disabled\">首页</button><button disabled='true' class=\"disabled\">上一页</button>");
				}else{
					htmls.push("<button onclick=\"javascript:turnToPage<%=randId%>(");
					htmls.push(1)
					htmls.push(")\">首页</button>");
					htmls.push("<button onclick=\"javascript:turnToPage<%=randId%>(");
					htmls.push((window._pageInfo_<%=randId%>.pageNo - 1))
					htmls.push(")\">上一页</button>");
				}
			      
				if (window._pageInfo_<%=randId%>.pageNo == window._pageInfo_<%=randId%>.pageCount) {  
					htmls.push("<button  disabled='true' class=\"disabled\">下一页")  
			        htmls.push("</button>");  
					htmls.push("<button   disabled='true' class=\"disabled\">尾页")  
			        htmls.push("</button>");  
				} else {  
					htmls.push("<button   onclick=\"javascript:turnToPage<%=randId%>(")  
					htmls.push((window._pageInfo_<%=randId%>.pageNo + 1))  
			        htmls.push(")\">下一页</button>");  
					htmls.push("<button   onclick=\"javascript:turnToPage<%=randId%>(")  
					htmls.push(window._pageInfo_<%=randId%>.pageCount )  
			        htmls.push(")\">尾页</button>");  
				}  
				htmls.push("<span>每页<input type='text' id='xxx_page_size<%=randId%>' value='"+window._pageInfo_<%=randId%>.pageSize+"' onkeydown='inputKeyDown<%=randId%>(event)'>条 "+
						"&nbsp;&nbsp;跳转到<input type='text' id='xxx_to_page<%=randId%>' onkeydown='inputKeyDown<%=randId%>(event)'>页&nbsp;&nbsp;"+
						"<button style=\"background-color:#F3791E;color:#fff;width:30px;border: 0;\"  onclick='jumpToPage<%=randId%>(jQuery(\"#xxx_to_page<%=randId%>\").val(),jQuery(\"#xxx_page_size<%=randId%>\").val())'   >go</button>&nbsp;&nbsp;");
				htmls.push("<span>【 第 "+window._pageInfo_<%=randId%>.pageNo+"/"+window._pageInfo_<%=randId%>.pageCount+" 页 , 共 "+window._pageInfo_<%=randId%>.totalCount+" 条记录】</span>&nbsp;&nbsp;");
			      
				jQuery("#pagediv<%=randId%>").html(htmls.join(''));
			 } 
		}
		else if(<%=type%>==2){
			if(window._pageInfo_<%=randId%>.totalCount<=0){
				jQuery("#pagediv<%=randId%>").html("&laquo;&nbsp;<span class=\"disabled\">上一页</span><span class=\"disabled\">下一页</span>&nbsp;&raquo;")
				jQuery("#pagediv<%=randId%>").attr("title","共0条记录  0/0页面");
			}else{
				 
				if(window._pageInfo_<%=randId%>.pageNo>window._pageInfo_<%=randId%>.pageCount){
					window._pageInfo_<%=randId%>.pageNo=window._pageInfo_<%=randId%>.pageCount
				}
				if(window._pageInfo_<%=randId%>.pageNo<1){
					window._pageInfo_<%=randId%>.pageNo=1
				}
				jQuery("#pagediv<%=randId%>").attr("title","共"+window._pageInfo_<%=randId%>.totalCount+"条记录  "+window._pageInfo_<%=randId%>.pageNo+"/"+window._pageInfo_<%=randId%>.pageCount+"页面");
				var htmls=[];
				 
				if(window._pageInfo_<%=randId%>.pageNo==1){
					htmls.push("<span class=\"disabled\">&laquo;&nbsp;上一页</span>");
				}else{
					htmls.push("<a href=\"javascript:turnToPage<%=randId%>(");
					htmls.push((window._pageInfo_<%=randId%>.pageNo - 1))
					htmls.push(")\">&laquo;&nbsp;上一页</a>");
				}
				var start=1;
				 
				if(window._pageInfo_<%=randId%>.pageNo>4){
					 start = window._pageInfo_<%=randId%>.pageNo - 1;
					 htmls.push("<a href=\"javascript:turnToPage<%=randId%>(1)\">1</a>");  
					 htmls.push("<a href=\"javascript:turnToPage<%=randId%>(2)\">2</a>");  
					 htmls.push("&hellip;"); 
				}
				var end= window._pageInfo_<%=randId%>.pageNo+1;
				 
				if(end > window._pageInfo_<%=randId%>.pageCount){  
				    end = window._pageInfo_<%=randId%>.pageCount;  
				}  
				for(var i=start;i<=end;i++){
					if(window._pageInfo_<%=randId%>.pageNo == i){   //当前页号不需要超链接  
						htmls.push("<span class=\"current\">")  
				            htmls.push(i)  
				            htmls.push("</span>");  
				    }else{  
				    	htmls.push("<a href=\"javascript:turnToPage<%=randId%>(")  
				    	htmls.push(i)  
				        htmls.push(")\">")  
			        htmls.push(i)  
				        htmls.push("</a>");  
				    } 
				}
				 
				if(end < window._pageInfo_<%=randId%>.pageCount - 2){  
					 htmls.push("&hellip;");  
				}  
				if(end < window._pageInfo_<%=randId%>.pageCount - 1){  
					 htmls.push("<a href=\"javascript:turnToPage<%=randId%>(")  
					 htmls.push(window._pageInfo_<%=randId%>.pageCount - 1)  
				     htmls.push(")\">")  
				     htmls.push(window._pageInfo_<%=randId%>.pageCount - 1)  
				     htmls.push("</a>");  
				}  
				if(end < window._pageInfo_<%=randId%>.pageCount){  
					 htmls.push("<a href=\"javascript:turnToPage<%=randId%>(")  
					 htmls.push(window._pageInfo_<%=randId%>.pageCount)  
				     htmls.push(")\">")  
				     htmls.push(window._pageInfo_<%=randId%>.pageCount)  
				     htmls.push("</a>");   
				}  
				//下一页处理  
				if (window._pageInfo_<%=randId%>.pageNo == window._pageInfo_<%=randId%>.pageCount) {  
					htmls.push("<span class=\"disabled\">下一页&nbsp;&raquo;")  
			        htmls.push("</span>\r\n");  
				} else {  
					htmls.push("<a href=\"javascript:turnToPage<%=randId%>(")  
					htmls.push((window._pageInfo_<%=randId%>.pageNo + 1))  
			        htmls.push(")\">下一页&nbsp;&raquo;</a>\r\n");  
				} 
				htmls.push("到第&nbsp;<input type='text' id='xxx_to_page<%=randId%>' style='width:20px;height:14px;'  onkeydown='inputKeyDown<%=randId%>(event)'>&nbsp;页&nbsp;");
				htmls.push("<input type='button' value='确定' onclick='jumpToPage<%=randId%>(jQuery(\"#xxx_to_page<%=randId%>\").val())'>");
				jQuery("#pagediv<%=randId%>").html(htmls.join(''));
			}
		}else if(<%=type%>==3){

			if(window._pageInfo_<%=randId%>.totalCount<=0){
				jQuery("#pagediv<%=randId%>").html("<span>共0条记录  0/0页</span>&nbsp;&nbsp;<button disabled class=\"disabled\">上一页</button><button style=\"margin-left:3px;\" disabled class=\"disabled\">下一页</button>")
				 
			}else{  
				if(window._pageInfo_<%=randId%>.pageNo>window._pageInfo_<%=randId%>.pageCount){
					window._pageInfo_<%=randId%>.pageNo=window._pageInfo_<%=randId%>.pageCount
				}
				if(window._pageInfo_<%=randId%>.pageNo<1){
					window._pageInfo_<%=randId%>.pageNo=1
				}			    
				var htmls=[];
				htmls.push("<span>共"+window._pageInfo_<%=randId%>.totalCount+"条记录  "+window._pageInfo_<%=randId%>.pageNo+"/"+window._pageInfo_<%=randId%>.pageCount+"页</span>&nbsp;&nbsp;");
			      
			     
				
				 
				if(window._pageInfo_<%=randId%>.pageNo==1){
					htmls.push("<button disabled='true' class=\"disabled\">上一页</button>");
				}else{
					htmls.push("<button onclick=\"javascript:turnToPage<%=randId%>(");
					htmls.push((window._pageInfo_<%=randId%>.pageNo - 1))
					htmls.push(")\">上一页</button>");
				}
			      
				if (window._pageInfo_<%=randId%>.pageNo == window._pageInfo_<%=randId%>.pageCount) {  
					htmls.push("<button style=\"margin-left:3px;\" disabled='true' class=\"disabled\">下一页")  
			        htmls.push("</button>");  
				} else {  
					htmls.push("<button style=\"margin-left:3px;\" onclick=\"javascript:turnToPage<%=randId%>(")  
					htmls.push((window._pageInfo_<%=randId%>.pageNo + 1))  
			        htmls.push(")\">下一页</button>\r\n");  
				}  
			   
				jQuery("#pagediv<%=randId%>").html(htmls.join(''));
			 } 
		}
	}
	 
	if(!window.__pageLinks){
		window.__pageLinks={};
	}
	<%if(linkId!=null){%>
		window.__pageLinks['<%=linkId%>']=window._pageInfo_<%=randId%>;
	<%}%>
	
	if(!window.getPageLinkObject){
		window.getPageLinkObject=function(linkId){
			return window.__pageLinks[linkId];
		}
	}
	jQuery(document).ready(function(){
		buildPage<%=randId%>();
	})
	function inputKeyDown<%=randId%>(e){
		var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
		  if(key == 13) {
		   if(e && e.preventDefault){
			   e.preventDefault();
		   }
		   jumpToPage<%=randId%>(jQuery("#xxx_to_page<%=randId%>").val(),jQuery("#xxx_page_size<%=randId%>").val());
		 }
	}
</script> 
 