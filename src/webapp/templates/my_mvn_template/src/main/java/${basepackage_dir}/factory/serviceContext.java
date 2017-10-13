package ${basepackage}.factory;

import javax.annotation.Resource;

import ${basepackage}.factory.SpringWiredBean;
import ${basepackage}.service.*;
public class serviceContext {
	
	<#if tables ??>
		<#list tables as tb>
	private I${tb.className}Service ${tb.className?uncap_first}Service;
	public I${tb.className}Service get${tb.className}Service(){
		return (I${tb.className}Service) SpringWiredBean.getInstance().getBeanById("${tb.className?uncap_first}Service");
	}
		</#list>
	</#if>
}
