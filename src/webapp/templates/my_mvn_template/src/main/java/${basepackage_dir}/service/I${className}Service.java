<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

import ${basepackage}.entity.${className};

<#include "/java_imports.include">

public interface I${className}Service extends IBaseService<${className},<#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> {
}
