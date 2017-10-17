<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.repository.custom.impl;

import ${basepackage}.domain.${className};
import ${basepackage}.repository.custom.${className}RepositoryCustom;

public class ${className}RepositoryImpl extends BaseRepositoryCustomImpl<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>>
		implements ${className}RepositoryCustom{

}
