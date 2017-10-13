package ${basepackage}.factory;

import ${basepackage}.service.*;

public class ServiceFactory {

	<#if tables ??>
	<#list tables as tb>
	public static I${tb.className}Service get${tb.className}Service(){
		return (I${tb.className}Service) SpringUtil.getBean("${tb.className?uncap_first}Service");
	}
	</#list>
	</#if>

}
