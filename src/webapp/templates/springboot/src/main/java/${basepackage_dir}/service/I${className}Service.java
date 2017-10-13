<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import java.util.List;

import ${basepackage}.entity.*;

public interface I${className}Service extends IBaseService<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> {


}
