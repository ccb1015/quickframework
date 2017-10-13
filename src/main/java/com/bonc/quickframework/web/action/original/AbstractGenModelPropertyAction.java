//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由模板自动生成 - 2016-03-25 15:47:30
//     对此文件的更改可能会导致不正确的行为，并且如果 重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------
package com.bonc.quickframework.web.action.original;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.bonc.quickframework.bean.GenModelPropertyBean;
import com.bonc.quickframework.bean.ResultInfo;
import com.bonc.quickframework.entity.GenModelProperty;
import com.bonc.quickframework.service.IGenModelPropertyService;
import com.bonc.quickframework.util.*;

import java.util.*;

/**
 * @author dguanlin email:dguanlin(a)163.com
 * @version 1.0
 * @since 1.0
 */
 
public class AbstractGenModelPropertyAction extends BaseAction {
	
	GenModelPropertyBean bean=new GenModelPropertyBean();
	
	@Resource(name = "genModelPropertyService")
	protected IGenModelPropertyService genModelPropertyService;


	public GenModelPropertyBean getBean() {
		return bean;
	}

	public void setBean(GenModelPropertyBean bean) {
		this.bean = bean;
	}
	
	public String list() {
		List dataList = this.genModelPropertyService.findList(this.getBean());
		this.getBean().setObjectList(dataList);
		return "list";
	}
	public String add() {
		return "add";
	}
	public String addDo() {
		this.genModelPropertyService.saveOrUpdate(this.getBean().getGenModelProperty());
		return SUCCESS;
	}
	public String edit() {
		GenModelProperty genModelProperty=this.genModelPropertyService.findById(this.getBean().getGenModelProperty().getId());
		this.getBean().setGenModelProperty(genModelProperty);
		return "edit";
	}
	public String editDo() {
		this.genModelPropertyService.saveOrUpdate(this.getBean().getGenModelProperty());
		return SUCCESS;
	}
	public String delete() {
		this.genModelPropertyService.delete(this.getBean().getGenModelProperty().getId());
		return SUCCESS;
	}
	
	/**
	 * 异步删除
	 */
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
				this.genModelPropertyService.deleteByCollection(this.genModelPropertyService.findList(ids));
			}
			ServletUtil.sendAsJson(response,new ResultInfo(true,"删除成功").toJSONString() );
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtil.sendAsJson(response,new ResultInfo(false,e.getMessage()).toJSONString() );
		}
	}
	
}