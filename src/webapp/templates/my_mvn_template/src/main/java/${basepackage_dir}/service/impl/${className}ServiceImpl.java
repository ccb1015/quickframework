<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${basepackage}.dao.IBaseDao;
import ${basepackage}.dao.I${className}Dao;
import ${basepackage}.entity.${className};
import ${basepackage}.service.I${className}Service;

<#include "/java_imports.include">

@Service(value = "${classNameLower}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className},<#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> implements I${className}Service {
	@Resource(name = "${classNameLower}Dao")
	private I${className}Dao ${classNameLower}Dao;

	@Override
	protected IBaseDao<${className},<#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> getCurrentDao() {
		return ${classNameLower}Dao;
	}
}
