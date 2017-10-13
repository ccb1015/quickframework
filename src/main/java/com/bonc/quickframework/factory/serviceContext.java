package com.bonc.quickframework.factory;

import com.bonc.quickframework.service.IGenControlerMethodService;
import com.bonc.quickframework.service.IGenControllerService;
import com.bonc.quickframework.service.IGenModelPropertyService;
import com.bonc.quickframework.service.IGenModelService;
import com.bonc.quickframework.service.IGenPropTypeService;
import com.bonc.quickframework.service.IGenTemplateService;
import com.bonc.quickframework.service.IGenUiDetailService;
import com.bonc.quickframework.service.IGenUiPropertityService;
import com.bonc.quickframework.service.IGenUiService;
import com.bonc.quickframework.service.IGenViewService;
import com.bonc.quickframework.service.IGeneratorDataTypeService;
import com.bonc.quickframework.service.IGeneratorDbserverService;
import com.bonc.quickframework.service.IGeneratorEntityService;
import com.bonc.quickframework.service.IGeneratorFieldService;
import com.bonc.quickframework.service.IGeneratorMethodParamService;
import com.bonc.quickframework.service.IGeneratorProjectService;
import com.bonc.quickframework.service.IGeneratorServiceMethodService;
import com.bonc.quickframework.service.IGeneratorServiceService;
import com.bonc.quickframework.service.IGeneratorUiService;
import com.bonc.quickframework.service.IGeneratorWebserverService;

public class serviceContext {
	public IGeneratorDbserverService getGeneratorDbserverService() {
		return (IGeneratorDbserverService) SpringWiredBean.getInstance()
				.getBeanById("generatorDbserverService");
	}

	public IGeneratorWebserverService getGeneratorWebserverService() {
		return (IGeneratorWebserverService) SpringWiredBean.getInstance()
				.getBeanById("generatorWebserverService");
	}

	public IGeneratorProjectService getGeneratorProjectService() {
		return (IGeneratorProjectService) SpringWiredBean.getInstance()
				.getBeanById("generatorProjectService");
	}

	public IGeneratorEntityService getGeneratorEntityService() {
		return (IGeneratorEntityService) SpringWiredBean.getInstance()
				.getBeanById("generatorEntityService");
	}

	public IGeneratorFieldService getGeneratorFieldService() {
		return (IGeneratorFieldService) SpringWiredBean.getInstance()
				.getBeanById("generatorFieldService");
	}

	public IGeneratorServiceService getGeneratorServiceService() {
		return (IGeneratorServiceService) SpringWiredBean.getInstance()
				.getBeanById("generatorServiceService");
	}

	public IGeneratorServiceMethodService getGeneratorServiceMethodService() {
		return (IGeneratorServiceMethodService) SpringWiredBean.getInstance()
				.getBeanById("generatorServiceMethodService");
	}

	public IGeneratorMethodParamService getGeneratorMethodParamService() {
		return (IGeneratorMethodParamService) SpringWiredBean.getInstance()
				.getBeanById("generatorMethodParamService");
	}

	public IGeneratorDataTypeService getGeneratorDataTypeService() {
		return (IGeneratorDataTypeService) SpringWiredBean.getInstance()
				.getBeanById("generatorDataTypeService");
	}

	public IGeneratorUiService getGeneratorUiService() {
		return (IGeneratorUiService) SpringWiredBean.getInstance().getBeanById(
				"generatorUiService");
	}
	public IGenControlerMethodService getGenControlerMethodService() {
		return (IGenControlerMethodService) SpringWiredBean.getInstance().getBeanById(
				"genControlerMethodService");
	}
	public IGenControllerService getGenControllerService() {
		return (IGenControllerService) SpringWiredBean.getInstance().getBeanById(
				"genControllerService");
	}
	public IGenModelPropertyService getGenModelPropertyService() {
		return (IGenModelPropertyService) SpringWiredBean.getInstance().getBeanById(
				"genModelPropertyService");
	}
	public IGenModelService getGenModelService() {
		return (IGenModelService) SpringWiredBean.getInstance().getBeanById(
				"genModelService");
	}
	public IGenPropTypeService getGenPropTypeService() {
		return (IGenPropTypeService) SpringWiredBean.getInstance().getBeanById(
				"genPropTypeService");
	}
	public IGenTemplateService getGenTemplateService() {
		return (IGenTemplateService) SpringWiredBean.getInstance().getBeanById(
				"genTemplateService");
	}
	public IGenUiDetailService getGenUiDetailService() {
		return (IGenUiDetailService) SpringWiredBean.getInstance().getBeanById(
				"genUiDetailService");
	}
	public IGenUiPropertityService getGenUiPropertityService() {
		return (IGenUiPropertityService) SpringWiredBean.getInstance().getBeanById(
				"genUiPropertityService");
	}
	public IGenUiService getGenUiService() {
		return (IGenUiService) SpringWiredBean.getInstance().getBeanById(
				"genUiService");
	}
	public IGenViewService getGenViewService() {
		return (IGenViewService) SpringWiredBean.getInstance().getBeanById(
				"genViewService");
	}

}
