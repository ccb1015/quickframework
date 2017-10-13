package com.bonc.quickframework.web.action.original;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.bonc.quickframework.bean.GeneratorFieldBean;
import com.bonc.quickframework.bean.ResultInfo;
import com.bonc.quickframework.entity.GeneratorField;
import com.bonc.quickframework.service.IGeneratorFieldService;
import com.bonc.quickframework.util.ServletUtil;

public class AbstractSRFieldConfigAction extends BaseAction{
	GeneratorFieldBean bean=new GeneratorFieldBean();
	
	@Resource(name = "generatorFieldService")
	protected IGeneratorFieldService generatorFieldService;


	public GeneratorFieldBean getBean() {
		return bean;
	}

	public void setBean(GeneratorFieldBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		List dataList = this.generatorFieldService.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		return "add";
	}
	public String addDo() {
		this.generatorFieldService.saveOrUpdate(this.getBean().getGeneratorField());
		return SUCCESS;
	}
	public String edit() {
		GeneratorField generatorField=this.generatorFieldService.findById(this.getBean().getGeneratorField().getId());
		this.getBean().setGeneratorField(generatorField);
		return "edit";
	}
	public String editDo() {
		this.generatorFieldService.saveOrUpdate(this.getBean().getGeneratorField());
		return SUCCESS;
	}
	public String delete() {
		this.generatorFieldService.delete(this.getBean().getGeneratorField().getId());
		return SUCCESS;
	}
	
	public void asyncDeleteAll() {
		try {
			String idString = this.getBean().getIdList();
			if(idString == null || "".endsWith(idString.trim())){
				ServletUtil.sendAsJson(response,new ResultInfo(false,"id is null").toJSONString() );
			}else{
				List<Long> ids = new ArrayList<Long>();
				String[] tmps = idString.split(",");
				for (String tmp : tmps) {
					if(tmp!=null && !"".equals(tmp.trim())){
						ids.add(Long.parseLong(tmp));
					}
				}
				this.generatorFieldService.deleteByCollection(this.generatorFieldService.findList(ids));
			}
			ServletUtil.sendAsJson(response,new ResultInfo(true,"删除成功").toJSONString() );
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtil.sendAsJson(response,new ResultInfo(false,e.getMessage()).toJSONString() );
		}
	}
}
