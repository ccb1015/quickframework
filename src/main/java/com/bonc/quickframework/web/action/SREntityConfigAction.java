package com.bonc.quickframework.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bonc.quickframework.service.IGeneratorProjectService;
import com.bonc.quickframework.web.action.original.AbstractSREntityConfigAction;

@Controller(value = "SREntityConfigAction")
@Scope(value = "prototype")
public class SREntityConfigAction extends AbstractSREntityConfigAction{
	
	@Resource(name = "generatorProjectService")
	protected IGeneratorProjectService generatorProjectService;

	@Override
	public String list() {
		Long projectId = this.getBean().getGeneratorEntity().getProjectId();
		if (projectId != null && projectId != 0) {
			this.getBean().getPageInfo().getQuerys()
					.put("projectId", projectId);
		}
		String code = this.getBean().getGeneratorEntity().getCode();
		if(code != null && !"".equals(code.toString())){
			this.getBean().getPageInfo().getMatchMaps().put("code", code);
		}
		String name = this.getBean().getGeneratorEntity().getName();
		if(name != null && !"".equals(name.toString())){
			this.getBean().getPageInfo().getMatchMaps().put("name", name);
		}
		return super.list();
	}
}
