<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.bean.PageInfo;
import ${basepackage}.dao.*;
import ${basepackage}.dao.base.BaseRepository;
import ${basepackage}.entity.*;
import ${basepackage}.service.*;

@Transactional
@Service
public class ${className}Service extends BaseService<${className}, <#list table.columns as column><#if column.pk>${column.javaType}</#if></#list>> implements I${className}Service {
	@Autowired
	I${className}Dao ${classNameLower}Dao;

	@Override
	public BaseRepository<${className}, Long> getCurrentDao() {
		return ${classNameLower}Dao;
	}
}
