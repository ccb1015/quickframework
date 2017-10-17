<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.domain.*;
import ${basepackage}.repository.*;
import ${basepackage}.service.interfac.I${className}Service;

@Service(value="${classNameLower}Service")
public class ${className}Service extends BaseService<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> implements I${className}Service{
	
	@Autowired
	${className}Repository ${classNameLower}Repository;
	
	@Override
	protected BaseRepository getCurrentRepository() {
		return this.${classNameLower}Repository;
	}

}
