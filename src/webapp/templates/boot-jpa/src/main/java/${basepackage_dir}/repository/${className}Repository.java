<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.repository;

import org.springframework.stereotype.Repository;

import ${basepackage}.domain.${className};
import ${basepackage}.repository.custom.${className}RepositoryCustom;  
  
@Repository  
public interface ${className}Repository extends BaseRepository<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>>, ${className}RepositoryCustom{ 

}  