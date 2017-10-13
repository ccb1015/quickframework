package com.bonc.quickframework.web.action;

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bonc.quickframework.bean.ResultInfo;
import com.bonc.quickframework.entity.GeneratorDbserver;
import com.bonc.quickframework.entity.GeneratorProject;
import com.bonc.quickframework.entity.GeneratorWebserver;
import com.bonc.quickframework.generator.GeneratorFacade;
import com.bonc.quickframework.service.IGeneratorDbserverService;
import com.bonc.quickframework.service.IGeneratorWebserverService;
import com.bonc.quickframework.util.ServletUtil;
import com.bonc.quickframework.web.action.original.AbstractSRProjectConfigAction;
import com.opensymphony.xwork2.ActionContext;

@Controller(value = "SRProjectConfigAction")
@Scope(value = "prototype")
public class SRProjectConfigAction extends AbstractSRProjectConfigAction{
	@Resource(name = "generatorDbserverService")
	protected IGeneratorDbserverService dbService;

	@Resource(name = "generatorWebserverService")
	protected IGeneratorWebserverService webService;

	
	@Override
	public String add() {
		this.findServer();
		return super.add();
	};
	@Override
	public String edit() {
		this.findServer();
		return super.edit();
	}
	
	private void findServer(){
		ActionContext.getContext().put("dbServers", dbService.loadAll());
		ActionContext.getContext().put("webServers", webService.loadAll());
	}
	/**
	 * 生成代码
	 */
	public void generate() {
		String result = "";
		try {
			String delete = request.getParameter("delete");
			
			Long projectId = this.getBean().getGeneratorProject().getId();
			GeneratorProject project = this.generatorProjectService
					.findById(projectId);
			GeneratorDbserver db = dbService.findById(project.getDbserverId());
			GeneratorWebserver web = webService.findById(project
					.getWebserverId());

			String basePath = ServletActionContext.getServletContext()
					.getRealPath(File.separator);
			

			String inputPath = basePath + "templates" + File.separator + project.getTemaplateName();
			String outputPath = project.getPath();

			GeneratorFacade.makeProjectCode(project, web, db,
					project.getGeneratorEntitys(),project.getGeneratorServices(),
					inputPath, outputPath,"1".equals(delete));
			result = new ResultInfo(true, "生成代码成功").toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			String msg= e.getMessage()!=null? e.getMessage():e.getStackTrace().toString();
			result = new ResultInfo(false, msg).toJSONString();
		}
		ServletUtil.sendAsJson(response, result);
	}
}
