<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.web.controller.original;

import ${basepackage}.bean.${className}Bean;
import ${basepackage}.bean.PageInfo;
import ${basepackage}.bean.ResultInfo;
import ${basepackage}.factory.ServiceFactory;
import ${basepackage}.service.I${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class Abstract${className}Controller {

	@Autowired
	protected I${className}Service ${classNameLower}Service;

	/**
	 * 添加页面
	 */
	@RequestMapping(value = { "/add" },method = RequestMethod.GET)
	public String add(Model model,${className}Bean bean){
		this.initialData(model);
		return "${classNameLower}/edit.jsp";
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping(value = { "/edit" },method = RequestMethod.GET)
	public String edit(Model model,${className}Bean bean){
		this.initialData(model);
		bean.set${className}(${classNameLower}Service.findOne(bean.get${className}().getId()));
		model.addAttribute("bean",bean);
		return "${classNameLower}/edit.jsp";
	}

	/**
	 * 初始化数据(下拉选项等)
	 */
	private void initialData(Model model) {
<#list table.associatedTables?values as foreignKey>
	<#assign fkPojoClass = foreignKey.classTableName>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	<#if foreignKey.relation == "ManyToOne">
		model.addAttribute("${fkPojoClassVar}s", ServiceFactory.get${fkPojoClass}Service().findAll());
	</#if>
	<#if foreignKey.relation == "ManyToMany" && (foreignKey.mainFk?? && foreignKey.mainFk=="1")>
		model.put("${fkPojoClassVar}s", ServiceFactory.get${fkPojoClass}Service().loadAll());
	</#if>
</#list>
	}
	/**
	 * 保存对象
	 */
	@RequestMapping(value = { "/save" },method = RequestMethod.POST)
	public String saveOrUpdate(Model model,${className}Bean bean){
		${classNameLower}Service.save(bean.get${className}());
		return "redirect:list";
	}

	/**
	 * 列表页面
	 */
	@RequestMapping(value = { "/list" })
	public String list(Model model,${className}Bean bean) {
		PageInfo pageInfo = bean.getPageInfo();
		//简单搜索
<#list table.columns as column>
	<#if column.search>
		${column.javaType} ${column.columnNameLower} = bean.get${table.className}().get${column.columnName}();
		if(${column.columnNameLower} != null && !"".equals(${column.columnNameLower}.toString())){
			bean.getPageInfo().getMatchMap().put("${column.columnNameLower}", ${column.columnNameLower});
		}
	</#if>
</#list>
		//下拉搜索
<#list table.associatedTables?values as foreignKey>
	<#if foreignKey.field?? && foreignKey.field.isSearch?? && foreignKey.field.isSearch==1>
		<#if foreignKey.field.entityTypeId??>
			<#assign fkPojoClass = foreignKey.classTableName>
			<#assign fkPojoClassVar = fkPojoClass?uncap_first>
			<#assign valueColumn = foreignKey.columnName?uncap_first>
			<#assign fkFieldName = foreignKey.parentName>
		if(bean.get${foreignKey.table.className}().get${fkFieldName}()!=null){
			Long ${fkFieldName?uncap_first}Id = bean.get${foreignKey.table.className}().get${fkFieldName}().getId();
			if(${fkFieldName?uncap_first}Id != null && !"".equals(${fkFieldName?uncap_first}Id.toString())){
				bean.getPageInfo().getEqualsMap().put("${fkFieldName?uncap_first}.id", ${fkFieldName?uncap_first}Id);
			}
		}
		//选项
		model.addAttribute("${fkPojoClassVar}s", ServiceFactory.get${fkPojoClass}Service().findAll());
		</#if>
	</#if>
</#list>
		
		List list=${classNameLower}Service.findList(pageInfo);
		bean.setObjectList(list);
		model.addAttribute("bean", bean);
		return "${classNameLower}/list.jsp" ;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = { "/asyncDeleteAll" },method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo asyncDeleteAll(${className}Bean bean){
		try{
			String idString = bean.getIdList();
			if(idString == null || "".equals(idString.trim())){
				return new ResultInfo(false,"id is null");
			}else{
				List<Long> ids = new ArrayList<Long>();
				String[] tmps = idString.split(",");
				for (String tmp : tmps) {
					if(tmp!=null && !"".equals(tmp.trim())){
						ids.add(Long.parseLong(tmp));
					}
				}
				${classNameLower}Service.deleteInBatch(${classNameLower}Service.findAll(ids));
			}
			return new ResultInfo(true);
		}catch(Exception e){
			return new ResultInfo(false, e.getMessage());
		}
	}
	
}
