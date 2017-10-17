<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service.interfac;

import ${basepackage}.domain.*;

public interface I${className}Service extends IBaseService<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>>{
	
}
