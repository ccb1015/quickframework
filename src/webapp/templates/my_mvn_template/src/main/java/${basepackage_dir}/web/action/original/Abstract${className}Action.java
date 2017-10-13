<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.web.action.original;

import java.util.*;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import ${basepackage}.factory.serviceContext;
import ${basepackage}.bean.*;
import ${basepackage}.entity.*;
import ${basepackage}.service.*;
import ${basepackage}.bean.ResultInfo;
import ${basepackage}.util.ServletUtil;

<#include "/java_imports.include">

public class Abstract${className}Action extends BaseAction {
	
	${className}Bean bean=new ${className}Bean();
	
	@Resource(name = "${classNameLower}Service")
	protected I${className}Service ${classNameLower}Service;


	public ${className}Bean getBean() {
		return bean;
	}

	public void setBean(${className}Bean bean) {
		this.bean = bean;
	}
	
	public String list() {
		//简单搜索
		<#list table.columns as column>
			<#if column.search>
        ${column.javaType} ${column.columnNameLower} = this.getBean().get${table.className}().get${column.columnName}();
		if(${column.columnNameLower} != null && !"".equals(${column.columnNameLower}.toString())){
			this.getBean().getPageInfo().getMatchMaps().put("${column.columnNameLower}", ${column.columnNameLower});
		}
			</#if>
		</#list>
		//下拉搜索
		<#list table.associatedTables?values as foreignKey>
			<#if foreignKey.field?? && foreignKey.field.isSearch?? && foreignKey.field.isSearch==1>
				<#if foreignKey.field.entityTypeId??>
					<#assign fkPojoClass = foreignKey.classTableName>
					<#assign fkPojoClassVar = fkPojoClass?uncap_first>
					<#assign valueColumn = foreignKey.columnName?uncap_first>
					<#assign fkFieldName = foreignKey.parentName>
		if(this.getBean().get${foreignKey.table.className}().get${fkFieldName}()!=null){
			Long ${fkFieldName?uncap_first}_id = this.getBean().get${foreignKey.table.className}().get${fkFieldName}().getId();
			if(${fkFieldName?uncap_first}_id != null && !"".equals(${fkFieldName?uncap_first}_id.toString())){
				this.getBean().getPageInfo().getQuerys().put("${fkFieldName?uncap_first}.id", ${fkFieldName?uncap_first}_id);
			}
		}
		//--
		ActionContext.getContext().put("${fkPojoClassVar}s", new serviceContext().get${fkPojoClass}Service().loadAll());
				</#if>
			</#if>
		</#list>
		
		List dataList = this.${classNameLower}Service.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		<#list table.associatedTables?values as foreignKey>
			<#assign fkPojoClass = foreignKey.classTableName>
			<#assign fkPojoClassVar = fkPojoClass?uncap_first>
			<#if foreignKey.relation == "ManyToOne">
		ActionContext.getContext().put("${fkPojoClassVar}s", new serviceContext().get${fkPojoClass}Service().loadAll());
			</#if>
			<#if foreignKey.relation == "ManyToMany" && (foreignKey.mainFk?? && foreignKey.mainFk=="1")>
		ActionContext.getContext().put("${fkPojoClassVar}s", new serviceContext().get${fkPojoClass}Service().loadAll());		
			</#if>
		</#list>
		return "add";
	}
	public String addDo() {
		processSetParams();
		this.${classNameLower}Service.saveOrUpdate(this.getBean().get${className}());
		return SUCCESS;
	}
	public String edit() {
		<#list table.associatedTables?values as foreignKey>
			<#assign fkPojoClass = foreignKey.classTableName>
			<#assign fkPojoClassVar = fkPojoClass?uncap_first>
			<#if foreignKey.relation == "ManyToOne">
		ActionContext.getContext().put("${fkPojoClassVar}s", new serviceContext().get${fkPojoClass}Service().loadAll());
			</#if>
			<#if foreignKey.relation == "ManyToMany" && (foreignKey.mainFk?? && foreignKey.mainFk=="1")>
		ActionContext.getContext().put("${fkPojoClassVar}s", new serviceContext().get${fkPojoClass}Service().loadAll());		
			</#if>
		</#list>
		
		${className} ${classNameLower}=this.${classNameLower}Service.findById(this.getBean().get${className}().getId());
		this.getBean().set${className}(${classNameLower});
		return "edit";
	}
	public String editDo() {
		processSetParams();
		this.${classNameLower}Service.saveOrUpdate(this.getBean().get${className}());
		return SUCCESS;
	}
	public String delete() {
		this.${classNameLower}Service.delete(this.getBean().get${className}().getId());
		return SUCCESS;
	}
	
	/**
	 * 异步删除
	 */
	public void asyncDeleteAll() {
		try {
			String idString = this.getBean().getIdList();
			if(idString == null || "".equals(idString.trim())){
				ServletUtil.sendAsJson(response,new ResultInfo(false,"id is null").toJSONString() );
			}else{
				List<Long> ids = new ArrayList<Long>();
				String[] tmps = idString.split(",");
				for (String tmp : tmps) {
					if(tmp!=null && !"".equals(tmp.trim())){
						ids.add(Long.parseLong(tmp));
					}
				}
				this.${classNameLower}Service.deleteByCollection(this.${classNameLower}Service.findList(ids));
			}
			ServletUtil.sendAsJson(response,new ResultInfo(true,"删除成功").toJSONString() );
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtil.sendAsJson(response,new ResultInfo(false,e.getMessage()).toJSONString() );
		}
	}
	/**
	 * 字段关联
	 */	
	<#list table.associatedTables?values as foreignKey>
		<#assign fkPojoClass = foreignKey.classTableName>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#assign valueColumn = foreignKey.columnName?uncap_first>
		<#if foreignKey.relation == "ManyToOne" && foreignKey.filterColumn??>
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass>
			</#if>
			<#assign f = foreignKey.filterColumn>
	public void find${f.parentTypeName}Json(){
		try {
			String ${f.typeName?uncap_first}Id = request.getParameter("${f.typeName?uncap_first}Id");
			if(${f.typeName?uncap_first}Id != null && !"".equals(${f.typeName?uncap_first}Id.trim()) ){
				this.getBean().getPageInfo().getQuerys().put("${f.parentTypeColumnName?uncap_first}.id", Long.parseLong(${f.typeName?uncap_first}Id));
			}
			this.getBean().getPageInfo().setPageSize(Integer.MAX_VALUE);
			List list = new serviceContext().get${f.parentTypeName}Service().findList(this.getBean());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("data", list);
			ServletUtil.sendAsJson(response,new ResultInfo(true, "", params).toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtil.sendAsJson(response,new ResultInfo(false,e.getMessage()).toJSONString() );
		}
	}
						
		</#if>
	</#list>

	/**
	 * 处理ManytoMany关系（维护关联关系端）
	 */
	private void processSetParams(){
		<#list table.associatedTables?values as foreignKey>
			<#assign fkTable    = foreignKey.sqlTableName>
			<#assign fkPojoClass = foreignKey.classTableName>
			<#assign fkPojoClassVar = fkPojoClass?uncap_first>
			<#if foreignKey.relation == "ManyToMany" && (foreignKey.mainFk?? && foreignKey.mainFk=="1")>
				<#if foreignKey.parentName??>
					<#assign fkFieldName = foreignKey.parentName>
				<#else>
					<#assign fkFieldName = fkPojoClass +"s">
				</#if>
		//
		String[] ${fkPojoClassVar}Ids = request.getParameterValues("bean.${classNameLower}.${fkFieldName?uncap_first}.id");
		if(${fkPojoClassVar}Ids!=null && ${fkPojoClassVar}Ids.length>0){
			HashSet<${fkPojoClass}> set=new HashSet<${fkPojoClass}>();
			for (String idStr : ${fkPojoClassVar}Ids) {
				if(idStr==null || "".equals(idStr.trim())){
					continue;
				}
				${fkPojoClass} ${fkPojoClassVar}=new ${fkPojoClass}();
				${fkPojoClassVar}.setId(Long.parseLong(idStr));
				set.add(${fkPojoClassVar});
			}
			this.getBean().get${className}().set${fkFieldName}(set);
		}
			</#if>
		</#list>
	}
	
}
