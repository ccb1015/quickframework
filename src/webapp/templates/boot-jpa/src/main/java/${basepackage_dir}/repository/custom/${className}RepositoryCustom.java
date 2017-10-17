<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.repository.custom;

import ${basepackage}.domain.${className};

public interface ${className}RepositoryCustom extends BaseRepositoryCustom<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>>{
	
}
