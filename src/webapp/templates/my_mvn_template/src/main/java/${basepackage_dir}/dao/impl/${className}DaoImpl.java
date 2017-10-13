<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.dao.impl;

import org.springframework.stereotype.Repository;
import ${basepackage}.dao.I${className}Dao;
import ${basepackage}.entity.${className};

<#include "/java_imports.include">

@Repository(value="${classNameLower}Dao")
public class ${className}DaoImpl extends BaseDaoImpl<${className},<#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> implements I${className}Dao {

}
