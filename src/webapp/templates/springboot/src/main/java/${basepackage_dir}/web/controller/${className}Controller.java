<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${basepackage}.bean.PageInfo;
import ${basepackage}.factory.SpringUtil;
import ${basepackage}.entity.*;
import ${basepackage}.service.*;
import ${basepackage}.web.controller.original.Abstract${className}Controller;

@Controller
@RequestMapping(value = "/${classNameLower}")
public class ${className}Controller extends Abstract${className}Controller {

	//private Logger logger = LoggerFactory.getLogger(HomeController.class);

	

	
}
