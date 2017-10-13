<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import ${basepackage}.dao.IBaseDao;
import ${basepackage}.entity.${className};

<#include "/java_imports.include">
public interface I${className}Dao extends IBaseDao<${className},<#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> {

}