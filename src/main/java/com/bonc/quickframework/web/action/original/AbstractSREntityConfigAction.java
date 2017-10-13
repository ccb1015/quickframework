package com.bonc.quickframework.web.action.original;

import java.util.List;

import javax.annotation.Resource;

import com.bonc.quickframework.bean.GeneratorEntityBean;
import com.bonc.quickframework.entity.GeneratorEntity;
import com.bonc.quickframework.factory.serviceContext;
import com.bonc.quickframework.service.IGeneratorEntityService;
import com.opensymphony.xwork2.ActionContext;

public class AbstractSREntityConfigAction extends BaseAction{
	
	GeneratorEntityBean bean=new GeneratorEntityBean();
	
	@Resource(name = "generatorEntityService")
	protected IGeneratorEntityService generatorEntityService;


	public GeneratorEntityBean getBean() {
		return bean;
	}

	public void setBean(GeneratorEntityBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		List list=new serviceContext().getGeneratorProjectService().loadAll();
		ActionContext.getContext().put("generatorProjects", list);
		List dataList = this.generatorEntityService.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		List list=new serviceContext().getGeneratorProjectService().loadAll();
		ActionContext.getContext().put("generatorProjects", list);
		return "add";
	}
	public String addDo() {
		this.generatorEntityService.saveOrUpdate(this.getBean().getGeneratorEntity());
		return SUCCESS;
	}
	public String edit() {
		List list=new serviceContext().getGeneratorProjectService().loadAll();
		ActionContext.getContext().put("generatorProjects", list);
		GeneratorEntity generatorEntity=this.generatorEntityService.findById(this.getBean().getGeneratorEntity().getId());
		this.getBean().setGeneratorEntity(generatorEntity);
		return "edit";
	}
	public String editDo() {
		this.generatorEntityService.saveOrUpdate(this.getBean().getGeneratorEntity());
		return SUCCESS;
	}
	public String delete() {
		this.generatorEntityService.delete(this.getBean().getGeneratorEntity().getId());
		return SUCCESS;
	}
}
