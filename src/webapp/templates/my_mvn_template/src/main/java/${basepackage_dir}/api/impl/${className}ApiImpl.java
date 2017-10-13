<#include "/java_copyright.include">

package ${basepackage}.api.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import ${basepackage}.factory.serviceContext;
import ${basepackage}.api.*;
import ${basepackage}.entity.*;
import ${basepackage}.dao.*;
import ${basepackage}.service.*;
import ${basepackage}.util.*;

<#include "/java_imports.include">
<#assign classNameLower = className?uncap_first>  

<#if table??>
/**
 * {className}自动生成接口
 */
@Component("${classNameLower}Api")
public class ${className}ApiImpl extends BaseApiImpl<${className}, Long>
		implements I${className}Api {

	@Resource(name = "${classNameLower}Service")
	private I${className}Service ${classNameLower}Service;

	@Override
	protected IBaseService<${className}, Long> getCurrentService() {
		return ${classNameLower}Service;
	}

	<#list table.associatedTables?values as foreignKey>
		<#assign fkTable    = foreignKey.sqlTableName>
		<#assign fkPojoClass = foreignKey.classTableName>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
		
		<#if (foreignKey.relation == "ManyToMany"|| foreignKey.relation == "OneToMany")>
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass +"s">
			</#if>
	public Collection<${fkPojoClass}> find${fkFieldName}(Long id) {
		return this.getCurrentService().findById(id).get${fkFieldName}();
	}
		</#if>
		<#if foreignKey.relation == "ManyToOne">
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass>
			</#if>
	public ${fkPojoClass} find${fkFieldName}(Long id){
		${className} ${classNameLower}=this.getCurrentService().findById(id);
		if(${classNameLower}!=null){
			${fkPojoClass} o=${classNameLower}.get${fkFieldName}();
			if(o!=null && o.toString()!=null){
				return (${fkPojoClass}) ClassUtil.DeepClone(o);
			}
		}
		return null;
	}
		</#if>
	</#list>
}
</#if>

<#if service??>
/**
 * {className}自定义接口
 */
@Component("${classNameLower}Api")
public class ${className}ApiImpl implements I${className}Api{
	
	<#if service.generatorServiceMethods??>
	<#list service.generatorServiceMethods as method>
	
	/**
	 * ${method.name}
	 */
	public <#if method.returnType?? && method.returnType!="">${method.returnType}<#else>void</#if> ${method.code}(<#if method.generatorMethodParams??>
		  	<#list method.generatorMethodParams as inparam>
		  	${inparam.dataType} ${inparam.code}<#if inparam_has_next>,</#if>	  	
		  	</#list>
		  	</#if>) throws Exception{
		ScriptEngineManager sem = new ScriptEngineManager(); 
		ScriptEngine engine = sem.getEngineByName("javascript"); 
		
		engine.eval("importPackage(java.util)");
		engine.eval("importPackage(java.sql)");
		engine.eval("importPackage(javax.sql)");
		engine.eval("importPackage(${basepackage}.api)");
		engine.eval("importPackage(${basepackage}.entity)");
		engine.eval("importPackage(${basepackage}.dao)");
		engine.eval("importPackage(${basepackage}.service)");
		engine.eval("importPackage(${basepackage}.factory)");
		
		engine.put("serviceContext",new serviceContext());
		engine.put("daoSupportUtil",new DaoSupportUtil());
		
		<#if method.generatorMethodParams??>
	  		<#list method.generatorMethodParams as inparam>
		engine.put("${inparam.code}", ${inparam.code});	  	
	  		</#list>
	  	</#if>
		
		<#if method.businessLogic??>	
		engine.eval("${method.businessLogic?replace("\"","\\\"")}");	
		</#if>
		
		<#if method.returnType?? && method.returnType!="" && method.returnType!="void">
		return (${method.returnType} )engine.get("result");		
		<#else> 
		return;	
		</#if>
	}
	</#list>
	</#if>
}

</#if>