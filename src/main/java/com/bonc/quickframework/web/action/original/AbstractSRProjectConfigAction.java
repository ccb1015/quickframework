package com.bonc.quickframework.web.action.original;

import java.util.List;

import javax.annotation.Resource;

import com.bonc.quickframework.bean.GeneratorProjectBean;
import com.bonc.quickframework.entity.GeneratorProject;
import com.bonc.quickframework.service.IGeneratorProjectService;

public class AbstractSRProjectConfigAction extends BaseAction{
	GeneratorProjectBean bean=new GeneratorProjectBean();
	
	@Resource(name = "generatorProjectService")
	protected IGeneratorProjectService generatorProjectService;


	public GeneratorProjectBean getBean() {
		return bean;
	}

	public void setBean(GeneratorProjectBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		List dataList = this.generatorProjectService.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		return "add";
	}
	public String addDo() {
		this.generatorProjectService.saveOrUpdate(this.getBean().getGeneratorProject());
		return SUCCESS;
	}
	public String edit() {
		GeneratorProject generatorProject=this.generatorProjectService.findById(this.getBean().getGeneratorProject().getId());
		this.getBean().setGeneratorProject(generatorProject);
		return "edit";
	}
	public String editDo() {
		this.generatorProjectService.saveOrUpdate(this.getBean().getGeneratorProject());
		return SUCCESS;
	}
	public String delete() {
		this.generatorProjectService.delete(this.getBean().getGeneratorProject().getId());
		return SUCCESS;
	}
}
