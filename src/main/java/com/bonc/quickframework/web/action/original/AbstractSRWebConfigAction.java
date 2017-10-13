package com.bonc.quickframework.web.action.original;

import java.util.List;

import javax.annotation.Resource;

import com.bonc.quickframework.bean.GeneratorWebserverBean;
import com.bonc.quickframework.entity.GeneratorWebserver;
import com.bonc.quickframework.service.IGeneratorWebserverService;

public class AbstractSRWebConfigAction extends BaseAction{
	GeneratorWebserverBean bean=new GeneratorWebserverBean();
	
	@Resource(name = "generatorWebserverService")
	protected IGeneratorWebserverService generatorWebserverService;


	public GeneratorWebserverBean getBean() {
		return bean;
	}

	public void setBean(GeneratorWebserverBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		List dataList = this.generatorWebserverService.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		return "add";
	}
	public String addDo() {
		this.generatorWebserverService.saveOrUpdate(this.getBean().getGeneratorWebserver());
		return SUCCESS;
	}
	public String edit() {
		GeneratorWebserver generatorWebserver=this.generatorWebserverService.findById(this.getBean().getGeneratorWebserver().getId());
		this.getBean().setGeneratorWebserver(generatorWebserver);
		return "edit";
	}
	public String editDo() {
		this.generatorWebserverService.saveOrUpdate(this.getBean().getGeneratorWebserver());
		return SUCCESS;
	}
	public String delete() {
		this.generatorWebserverService.delete(this.getBean().getGeneratorWebserver().getId());
		return SUCCESS;
	}
}
