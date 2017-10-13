<#include "/java_copyright.include">
package ${basepackage}.api;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ${basepackage}.bean.*;
import ${basepackage}.entity.*;
<#include "/java_imports.include">
<#if table??>
/**
 * ${className}自动生成接口
 */
<#assign classNameLower = className?uncap_first>  

@Path("/${classNameLower}")
@Produces({ MediaType.APPLICATION_JSON })
@WebService
public interface I${className}Api {

	@GET
	@POST
	@Path("/queryById")
	@WebMethod
	public ${className} queryById(
			@WebParam(name = "id") @QueryParam("id") Long id);

	@GET
	@POST
	@Path("/queryAll")
	@WebMethod
	public List<${className}> queryAll();

	@GET
	@POST
	@Path("/queryList")
	@WebMethod
	public List<${className}> queryList(
			@WebParam(name = "bean") @QueryParam("bean") BaseBean bean);
	
	<#list table.associatedTables?values as foreignKey>
		<#assign fkTable    = foreignKey.sqlTableName>
		<#assign fkPojoClass = foreignKey.classTableName>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
		
		<#if (foreignKey.relation == "ManyToMany" || foreignKey.relation == "OneToMany")>
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass +"s">
			</#if>
	@GET
	@POST
	@Path("/find${fkFieldName}")
	@WebMethod
	public Collection<${fkPojoClass}> find${fkFieldName}(@WebParam(name = "id") @QueryParam("id") Long id);
		</#if>
		
		<#if foreignKey.relation == "ManyToOne">
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass>
			</#if>
	@GET
	@POST
	@Path("/find${fkFieldName}")
	@WebMethod
	public ${fkPojoClass} find${fkFieldName}(@WebParam(name = "id") @QueryParam("id") Long id);
		</#if>
	</#list>
	
}
</#if>


<#if service??>
/**
 * {className}自定义接口
 */
<#assign className = service.code>  
<#assign classNameLower = className?uncap_first>  

<#if service.serviceType == 1>
@WebService
<#else>
	<#if service.path?? && service.path != "" >
@Path("${service.path}")
	<#else>
@Path("/${classNameLower}")
	</#if>
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
</#if>
public interface I${className}Api {

<#if service.generatorServiceMethods??>
<#list service.generatorServiceMethods as method>
	
	<#if service.serviceType == 1>
	/**
	 * ${method.name} -- soap
	 */
	@WebMethod
	public <#if method.returnType?? && method.returnType!="">${method.returnType}<#else>void</#if> ${method.code}(<#if method.generatorMethodParams??>
	<#list method.generatorMethodParams as inparam>
	@WebParam(name="${inparam.code}")${inparam.dataType} ${inparam.code}<#if inparam_has_next>,</#if>
  	</#list>
	</#if>) throws Exception;
	
	<#else>
		 <#assign pathp="">			 
		 <#if method.generatorMethodParams??>			 
		 <#list method.generatorMethodParams as inparam>
		 	<#if inparam.paramMapType??&&inparam.paramMapType=="@PathParam">
		 		<#assign pathp=pathp+"{${inparam.paramMapValue}}">
		 		<#if inparam_has_next><#assign pathp=pathp+"/"></#if>
		 	</#if>
		 </#list>
		 </#if>
	/**
	 * ${method.name}  -- restful
	 */
		<#if method.producer??  && method.producer !="">
	@Produces("${method.producer}")
		</#if>
		<#if method.cusomer?? && method.cusomer !="">
	@Consumes("${method.cusomer}")
		</#if>
		<#if method.httpMethod??>
	@${method.httpMethod?upper_case}
		<#else>
	@GET
		</#if>
		<#if method.subPath?? && method.subPath !="">
	@Path("${method.subPath}<#if pathp??>/${pathp}</#if>")
		<#else>
	@Path("/${method.code?uncap_first}<#if pathp !="">/${pathp}</#if>")
		</#if>
	public <#if method.returnType?? && method.returnType!="">${method.returnType}<#else>void</#if> ${method.code}(<#if method.generatorMethodParams??>
  	<#list method.generatorMethodParams as inparam>
  		<#if inparam.paramMapType??>
  	${inparam.paramMapType}("${inparam.paramMapValue}")
  		</#if>
  	${inparam.dataType} ${inparam.code}<#if inparam_has_next>,</#if>	  	
  	</#list>
  	</#if>) throws Exception;
	</#if>
	
	
</#list>
</#if>
}

</#if>