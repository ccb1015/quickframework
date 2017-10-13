<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import ${basepackage}.dao.base.BaseRepository;
import ${basepackage}.entity.${className};
	
public interface I${className}Dao extends BaseRepository<${className},<#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>>{

}
