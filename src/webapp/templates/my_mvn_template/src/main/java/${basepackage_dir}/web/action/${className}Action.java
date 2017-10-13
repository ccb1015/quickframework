<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.web.action;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import ${basepackage}.entity.${className};
import ${basepackage}.web.action.original.Abstract${className}Action;

<#include "/java_imports.include">

@Controller(value = "${classNameLower}Action")
@Scope(value = "prototype")
public class ${className}Action extends Abstract${className}Action {


}
