package com.bonc.quickframework.web.action.original;

import java.util.List;

import javax.annotation.Resource;

import com.bonc.quickframework.bean.GeneratorDbserverBean;
import com.bonc.quickframework.entity.GeneratorDbserver;
import com.bonc.quickframework.service.IGeneratorDbserverService;

public class AbstractSRDataBaseConfigAction extends BaseAction{
	
	GeneratorDbserverBean bean=new GeneratorDbserverBean();
	
	@Resource(name = "generatorDbserverService")
	protected IGeneratorDbserverService generatorDbserverService;


	public GeneratorDbserverBean getBean() {
		return bean;
	}

	public void setBean(GeneratorDbserverBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		List dataList = this.generatorDbserverService.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		return "add";
	}
	public String addDo() {
		this.generatorDbserverService.saveOrUpdate(this.getBean().getGeneratorDbserver());
		return SUCCESS;
	}
	public String edit() {
		GeneratorDbserver generatorDbserver=this.generatorDbserverService.findById(this.getBean().getGeneratorDbserver().getId());
		this.getBean().setGeneratorDbserver(generatorDbserver);
		return "edit";
	}
	public String editDo() {
		this.generatorDbserverService.saveOrUpdate(this.getBean().getGeneratorDbserver());
		return SUCCESS;
	}
	public String delete() {
		this.generatorDbserverService.delete(this.getBean().getGeneratorDbserver().getId());
		return SUCCESS;
	}
}
