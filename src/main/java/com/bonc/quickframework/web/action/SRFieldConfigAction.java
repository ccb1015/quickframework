package com.bonc.quickframework.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bonc.quickframework.bean.GeneratorFieldBean;
import com.bonc.quickframework.bean.ResultInfo;
import com.bonc.quickframework.entity.GeneratorEntity;
import com.bonc.quickframework.entity.GeneratorField;
import com.bonc.quickframework.factory.serviceContext;
import com.bonc.quickframework.service.IGeneratorEntityService;
import com.bonc.quickframework.util.ServletUtil;
import com.bonc.quickframework.web.action.original.AbstractSRFieldConfigAction;
import com.opensymphony.xwork2.ActionContext;

@Controller(value = "SRFieldConfigAction")
@Scope(value = "prototype")
public class SRFieldConfigAction extends AbstractSRFieldConfigAction{

	@Resource(name = "generatorEntityService")
	protected IGeneratorEntityService generatorEntityService;
	
	@Override
	public String list() {
		Long entityId = this.getBean().getGeneratorField().getEntityId();
		if (entityId != null && entityId != 0) {
			this.getBean().getPageInfo().getQuerys().put("entityId", entityId);
		}
		String code = this.getBean().getGeneratorField().getCode();
		if(code != null && !"".equals(code.toString())){
			this.getBean().getPageInfo().getMatchMaps().put("code", code);
		}
		String name = this.getBean().getGeneratorField().getName();
		if(name != null && !"".equals(name.toString())){
			this.getBean().getPageInfo().getMatchMaps().put("name", name);
		}
		
		//查找项目
		List list =null;
		if (entityId != null && entityId != 0) {
			GeneratorEntity entity=generatorEntityService.findById(entityId);
			GeneratorFieldBean currBean=new GeneratorFieldBean();
			currBean.getPageInfo().getQuerys().put("projectId", entity.getProjectId());
			currBean.getPageInfo().setPageSize(Integer.MAX_VALUE);
			list = generatorEntityService.findList(currBean);
		}else{
			list=generatorEntityService.loadAll();
		}
		ActionContext.getContext().put("generatorEntitys",list);
		return super.list();
	}
	
	@Override
	public String add() {
		this.findEntitys();
		ActionContext.getContext().put("uis", new serviceContext().getGeneratorUiService().loadAll());
		ActionContext.getContext().put("dataTypes", new serviceContext().getGeneratorDataTypeService().loadAll());
		return super.add();
	};

	@Override
	public String edit() {
		this.findEntitys();
		ActionContext.getContext().put("uis", new serviceContext().getGeneratorUiService().loadAll());
		ActionContext.getContext().put("dataTypes", new serviceContext().getGeneratorDataTypeService().loadAll());
		return super.edit();
	}

	private void findEntitys(){
		Long entityId = this.getBean().getGeneratorField().getEntityId();
		List list =null;
		if (entityId != null && entityId != 0) {
			GeneratorEntity entity=generatorEntityService.findById(entityId);
			this.getBean().getPageInfo().getQuerys().put("projectId", entity.getProjectId());
			this.getBean().getPageInfo().setPageSize(Integer.MAX_VALUE);
			list = generatorEntityService.findList(this.getBean());
			//当前实体字段
			ActionContext.getContext().put("generatorFields", entity.getGeneratorFields());
		}else{
			list=generatorEntityService.loadAll();
		}
		ActionContext.getContext().put("generatorEntitys", list);
	}
	
	public void findFieldsJson(){
		try {
			Long entityId = this.getBean().getGeneratorField().getEntityId();
			if (entityId != null && entityId != 0) {
				this.getBean().getPageInfo().getQuerys()
						.put("entityId", entityId);
			}
			this.getBean().getPageInfo().setPageSize(Integer.MAX_VALUE);
			List<GeneratorField> list = generatorFieldService.findList(this.getBean());
			Map params=new HashMap<String, Object>();
			params.put("data", list);
			ResultInfo resultInfo = new ResultInfo(true, "", params);
			ServletUtil.sendAsJson(response, resultInfo.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtil.sendAsJson(response,new ResultInfo(false,e.getMessage()).toJSONString() );
		}
	}
}
