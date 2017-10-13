<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<title>GeneratorField</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<!-- <h5>字段</h5> -->
						<h5 style="font-size: 10px;color: grey;">约定：1、主键名称都为“Id”，类型为Long类型 &nbsp;&nbsp;2、关联关系字段都在维护关联关系（ManyToOne的many端）端添加，需要选择显示属性</h5>
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
						<form role="form" class="form-horizontal" method="post" action="SRFieldConfig!editDo.action">
							<!-- id -->		
       						<input type="hidden" name="bean.generatorField.id" value='<s:property value="bean.generatorField.id"/>'/>
							<!-- property -->
							<!-- Code -->
							<div class="form-group">
								<label class="col-sm-2 control-label">字段名称:</label>
								<div class="col-sm-4">
									<input name="bean.generatorField.code" value='<s:property value="bean.generatorField.code"/>' class="field_name form-control"/>
									<span class="help-block m-b-none">
										<!-- PK,isIncrement，notNull，isUnique -->
										<label >
											<input class="ck_pk" type="checkbox" value="1" name="bean.generatorField.pk" ${bean.generatorField.pk=='1'?'checked="checked"':'' } >
											主键
										</label>&nbsp;&nbsp;
										<label>
											<input class="ck_increment" type="checkbox" value="1" name="bean.generatorField.isIncrement" ${bean.generatorField.isIncrement=='1'?'checked="checked"':'' }/>
											自增
										</label>&nbsp;&nbsp;
										<label>
											<input class="ck_not_null" type="checkbox" value="1" name="bean.generatorField.notNull" ${bean.generatorField.notNull=='1'?'checked="checked"':'' }/>
											非空
										</label>&nbsp;&nbsp;
										<label>
											<input class="ck_unique" type="checkbox" value="1" name="bean.generatorField.isUnique" ${bean.generatorField.isUnique=='1'?'checked="checked"':'' }/>
											唯一
										</label>&nbsp;&nbsp;
										<label>
											<input type="checkbox" value="1" name="bean.generatorField.isSearch" ${bean.generatorField.isSearch=='1'?'checked="checked"':'' } >
											检索
										</label>
									</span>
								</div>
								<!-- Type -->
								<label class="col-sm-1 control-label">字段类型:</label>
								<div class="col-sm-4">
									<!-- 简单类型 --> 
									<select name="bean.generatorField.dataTypeId" class="form-control m-b">
										<option value="">----</option>
										<s:if test="generatorEntitys==null || generatorEntitys.size()==0">
										<s:iterator value="dataTypes" var="item" status="s">
											<option value="${item.id }" 
											${bean.generatorField.dataTypeId==item.id?'selected="selected"':'' }>${item.code }</option>
										</s:iterator>
										</s:if>
									</select>
									<span class="help-block m-b-none">
											ManyToOne、ManyToMany（默认Set类型）可以不设置
									</span>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<!-- Name -->
								<label class="col-sm-2 control-label">字段描述:</label>
								<div class="col-sm-4">
									<input name="bean.generatorField.name" value='<s:property value="bean.generatorField.name"/>' class="form-control"/>
								</div>
							<!-- EntityId -->
								<label class="col-sm-1 control-label">所属实体:</label>
								<div class="col-sm-4">
									<select name="bean.generatorField.entityId" class="form-control m-b">
										<s:if test="generatorEntitys==null || generatorEntitys.size()==0">
										<s:iterator value="generatorEntitys" var="item" status="s">
											<option value="${item.id }" ${bean.generatorField.entityId==item.id?'selected="selected"':'' }>${item.name==null?item.code:item.name }</option>
										</s:iterator>
										</s:if>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- Length -->
							<div class="form-group">
								<label class="col-sm-2 control-label">长度:</label>
								<div class="col-sm-4">
									<input name="bean.generatorField.length" value='<s:property value="bean.generatorField.length"/>' class="form-control"/>
								</div>
							<!-- DefaultValue -->
								<label class="col-sm-1 control-label">默认值:</label>
								<div class="col-sm-4">
									<input name="bean.generatorField.defaultValue" value='<s:property value="bean.generatorField.defaultValue"/>' class="form-control"/>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- FkTypeColumn -->
							<div class="form-group">
								<label class="col-sm-2 control-label">关联关系:</label>
								<div class="col-sm-4">
									<select name="bean.generatorField.fkTypeColumn" class="select_fk_type_column form-control m-b">
								 		<option value="">---</option>
								 		<%-- <option ${fn:toUpperCase(bean.generatorField.fkTypeColumn) eq 'ONETOMANY'?'selected="selected"':'' }>OneToMany</option> --%>
								 		<option ${fn:toUpperCase(bean.generatorField.fkTypeColumn) eq 'MANYTOONE'?'selected="selected"':'' }>ManyToOne</option>
								 		<option ${fn:toUpperCase(bean.generatorField.fkTypeColumn) eq 'MANYTOMANY'?'selected="selected"':'' }>ManyToMany</option>
								 	</select>
								</div>
								<!-- many2many -->
								<label class="label_mainFk" style="display: none;">
									<input type="checkbox" value="1" name="bean.generatorField.mainFk" ${bean.generatorField.mainFk=='1'?'checked="checked"':'' }/>
									维护关联表
								</label>

							</div>
							<div class="hr-line-dashed"></div>
							<div class="div_relation" style="display: none;">
								<div class="form-group ">
									<label class="col-sm-2 control-label">关联实体:</label>
									<div class="col-sm-4">
										<select name="bean.generatorField.entityTypeId" class="select_type form-control m-b">
											<option value="">----</option>
											<s:if test="generatorEntitys==null || generatorEntitys.size()==0">
											<s:iterator value="generatorEntitys" var="item" status="s">
												<option type="diy" oid="${item.id }" value="${item.id }" ${bean.generatorField.entityTypeId==item.id?'selected="selected"':'' }>${item.code }(${item.name==null?item.code:item.name })</option>
											</s:iterator>
											</s:if>
										 </select>
									</div>
									<!-- ShowColumn -->
									<div style="display: none;">
										<label class="col-sm-1 control-label">显示属性:</label>
										<div class="col-sm-4">
											<select name="bean.generatorField.showColumnId" class="select1_show_column form-control m-b" cur="${bean.generatorField.showColumnId}">
												<!-- 选择多对一的显示列 -->
												<option>${bean.generatorField.showColumn}</option>
											</select>
											<span class="help-block m-b-none">
												选择关联实体显示的属性，如：User实体的Org字段，可以选择Org实体的name
											</span>
										</div>
									</div>
								</div>
								<div class="hr-line-dashed"></div>
							</div>

							<div class="form-group">
									<label class="col-sm-2 control-label">是否在list展示:</label>
									<div class="col-sm-4">
										<select name="bean.generatorField.isShow" class="form-control m-b">
											<option value='1' ${bean.generatorField.isShow ==1?'selected="selected"':'' }>是</option>
								 			<option value='0' ${bean.generatorField.isShow !=1?'selected="selected"':'' }>否</option>
										</select>
									</div>
									<label class="col-sm-1 control-label">是否排序:</label>
									<div class="col-sm-4">
										<select name="bean.generatorField.isSort" class="form-control m-b">
											<option value='1' ${bean.generatorField.isSort ==1?'selected="selected"':'' }>是</option>
								 			<option value='0' ${bean.generatorField.isSort !=1?'selected="selected"':'' }>否</option>
										</select>
									</div>
							</div>
							<div class="form-group">
									<div class="list-field">
										<label class="col-sm-2 control-label">显示属性:</label>
										<div class="col-sm-4">
											<select name="bean.generatorField.showField" class="select_show_column form-control m-b" cur="${bean.generatorField.showField}">
												<!-- 选择多对一的显示列 -->
												<option>${bean.generatorField.showColumn}</option>
											</select>
										</div>
									</div>
							</div>
							
							<div class="hr-line-dashed"></div>
							<div class="form-group">
									<label class="col-sm-2 control-label">是否作为搜索条件:</label>
									<div class="col-sm-4">
										<select name="bean.generatorField.isSearchCondition" class="form-control m-b">
											<option value='1' ${bean.generatorField.isSearchCondition ==1 ?'selected="selected"':'' }>是</option>
								 			<option value='0' ${bean.generatorField.isSearchCondition !=1?'selected="selected"':'' }>否</option>
										</select>
									</div>
									<div class="list-field">
										<label class="col-sm-1 control-label">条件属性:</label>
										<div class="col-sm-4">
											<select name="bean.generatorField.searchField" class="select_search_column form-control m-b" cur="${bean.generatorField.searchField}">
												<!-- 选择多对一的显示列 -->
												<option>${bean.generatorField.showColumn}</option>
											</select>
										</div>
									</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">UI组件:</label>
								<div class="col-sm-4">
									<select name="bean.generatorField.uiId" class="form-control m-b">
								 		<option value="">---</option>
								 		<s:if test="uis==null || uis.size()==0">
											<s:iterator value="uis" var="item" status="s">
												<option value="${item.id }" ${bean.generatorField.uiId==item.id?'selected="selected"':'' }>${item.code }(${item.name==null?item.code:item.name })</option>
											</s:iterator>
											</s:if>
								 	</select>
								 	<span class="help-block m-b-none">
								 	</span>
								</div>
									
								<label class="col-sm-1 control-label">顺序:</label>
								<div class="col-sm-4">
									<input name="bean.generatorField.sequence" value='<s:property value="bean.generatorField.sequence"/>' class="form-control"/>
									<span class="help-block m-b-none">
									
									</span>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<!-- button -->
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="button" onclick="onSave(this);">提交</button>&nbsp;
									<button class="btn btn-white" type="button" onclick="window.history.back()">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	jQuery(function(){
		bindChangeEvent();
	});
	function onSave(ele){
		//验证主键
		if(jQuery(".ck_pk").is(':checked')){
			if(jQuery("input[name='bean.generatorField.code']").val().toLowerCase()!="id"){
				alert("主键名称只能为Id");
				return false;
			}
			if(jQuery("select[name='bean.generatorField.dataTypeId'] option:selected").text().toLowerCase()!="long"){
				alert("主键只能选Long");
				return false;
			}
		}
		//验证关联关系
		var relation=jQuery(".select_fk_type_column").val();
		if(relation){
			if(!jQuery(".select_type").val()){
				alert("未选择关联实体");
			}
			/* if(relation=="ManyToMany"){
				var simpleType=jQuery("select[name='bean.generatorField.dataTypeId'] option:selected").text().toLowerCase();
				if(simpleType!="set"){
					alert("ManyToMany只能选Set类型");
					return;
				}
			} */
			/* if(!jQuery(".select_show_column").val()){
				alert("未选择显示属性");
				return false;
			} */
		}else{
			var simpleType=jQuery("select[name='bean.generatorField.dataTypeId']").val();
			if(!simpleType){
				alert("未选择字段类型");
				return false;
			}
		}
		//
		if(!jQuery("input[name='bean.generatorField.name']").val()){
				alert("未填写字段描述");
				return false;
		}
		if(relation != 'ManyToOne' && relation != 'ManyToMany'){
			jQuery("select[name='bean.generatorField.showField']").append("<option selected='selected'>"+jQuery(".field_name").val()+"</option>");
			jQuery("select[name='bean.generatorField.searchField']").append("<option selected='selected'>"+jQuery(".field_name").val()+"</option>");
		}
		jQuery("select[name='bean.generatorField.showColumnId']").append("<option selected='selected'>1</option>");
		
		
		jQuery(ele).parents("form").submit();
	}
	function bindChangeEvent(){
		//关联类型
		jQuery(".select_type").change(function(){
			var type=jQuery(".select_type option:selected").attr("type");
			if(type=='diy'){
				//var cur=jQuery(".select_show_column").attr("cur");
				var entityId=jQuery(".select_type option:selected").attr("oid");
				loadFields(entityId,".select_show_column");
				loadFields(entityId,".select_search_column");
			}
		}).change();
		//过滤列
		jQuery("select[name='bean.generatorField.filterFieldId']").change(function(){
			var entityId = "";
			var filterFieldId=jQuery("select[name='bean.generatorField.filterFieldId']").val();
			if(filterFieldId){
				entityId=jQuery("select[name='bean.generatorField.entityTypeId']").val();
			}
			loadFields(entityId,jQuery("select[name='bean.generatorField.filterTypeId']"));
		}).change();
		
		function loadFields(entityId,target){
			if(!entityId){
				jQuery(target).html('<option value="">--</option>');
				return;
			}
			var cur=jQuery(target).attr("cur");
			jQuery.post("generatorField!findFieldsJson.action",{"bean.generatorField.entityId":entityId},function(data){
				if(data.success==true){
					var entitys=data.params.data;
					if(entitys instanceof Array){
						var options='<option value="">--</option>';
						jQuery.each(entitys,function(i,item){
							options += '<option value="'+item.code+'" '+(cur==item.code?'selected="selected""':'')+'>'+(item.code)+'</option>'
						});
						jQuery(target).html(options);
					}
				}else{
					alert(data.message);
				}
			});
		}
		
		//主键选择
		jQuery(".ck_pk").change(function(){
			if(jQuery(this).is(':checked')){
				jQuery(".ck_increment,.ck_not_null,.ck_unique").attr("checked",true);
			}
		});
		//关联关系
		jQuery(".select_fk_type_column").change(function(){
			if(jQuery(this).val()=="ManyToOne"){
				jQuery(".label_mainFk").hide();
				jQuery(".div_filter_list").show();
				jQuery(".list-field").show();
			}else if(jQuery(this).val()=="ManyToMany"){
				jQuery(".div_filter_list").hide();
				jQuery(".label_mainFk").show();
				jQuery(".list-field").show();
			}else{
				jQuery(".label_mainFk,.div_filter_list").hide();
				jQuery(".list-field").hide();
			}
			if(jQuery(this).val()){
				jQuery(".div_relation").show();
				jQuery(".div_simple_type").show();
			}else{
				jQuery(".div_relation").hide();
				jQuery(".div_simple_type").show();
			}
		}).change();
	}
	
</script>
</body>

</html>
