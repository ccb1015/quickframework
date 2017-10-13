<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.bean;

import ${basepackage}.entity.${className};

public class ${className}Bean extends BaseBean {

	${className} ${classNameLower} = new ${className}();

	/* get、set */
	public ${className} get${className}() {
		return ${classNameLower};
	}

	public void set${className}(${className} ${classNameLower}) {
		this.${classNameLower} = ${classNameLower};
	}

}
