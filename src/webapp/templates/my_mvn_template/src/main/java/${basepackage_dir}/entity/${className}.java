<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.entity;

import java.util.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ${className} implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START
	<#list table.columns as column>
	<#if !column.fk >
	private ${column.javaType} ${column.columnNameLower};
	</#if>
	</#list>
	//columns END

<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>
<@generateJavaManyToMany/>

	public String toString() {
		return new ToStringBuilder(this)
		<#list table.columns as column>
			.append("${column.columnName}",get${column.columnName}())
		</#list>
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.columns as column>
			.append(get${column.columnName}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.columns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
}

<#macro generateJavaColumns>
	<#list table.columns as column>
	<#if !column.fk>
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#if>
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.associatedTables?values as foreignKey>
		<#assign fkTable    = foreignKey.sqlTableName>
		<#assign fkPojoClass = foreignKey.classTableName>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
		<#if foreignKey.relation == "OneToMany">
	@JsonIgnore
	private Set<${fkPojoClass}> ${fkPojoClassVar}s = new HashSet<${fkPojoClass}>(0);
	public void set${fkPojoClass}s(Set ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
		</#if>
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.associatedTables?values as foreignKey>
		<#assign fkTable    = foreignKey.sqlTableName>
		<#assign fkPojoClass = foreignKey.classTableName>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
		<#if foreignKey.relation == "ManyToOne">
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass>
			</#if>
	@JsonIgnore
	private ${fkPojoClass} ${fkFieldName?uncap_first};
	
	public void set${fkFieldName}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkFieldName?uncap_first} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkFieldName}() {
		if(new ${fkPojoClass}().equals(${fkFieldName?uncap_first})){
			return null;
		}
		return ${fkFieldName?uncap_first};
	}
		</#if>
	</#list>
</#macro>

<#macro generateJavaManyToMany>
	<#list table.associatedTables?values as foreignKey>
		<#assign fkTable    = foreignKey.sqlTableName>
		<#assign fkPojoClass = foreignKey.classTableName>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
		
		<#if foreignKey.relation == "ManyToMany">
			<#if foreignKey.parentName??>
				<#assign fkFieldName = foreignKey.parentName>
			<#else>
				<#assign fkFieldName = fkPojoClass +"s">
			</#if>
	@JsonIgnore
	private Set<${fkPojoClass}> ${fkFieldName?uncap_first} = new HashSet<${fkPojoClass}>(0);
	public void set${fkFieldName}(Set ${fkPojoClassVar}){
		this.${fkFieldName?uncap_first} = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkFieldName}() {
		return ${fkFieldName?uncap_first};
	}
		</#if>
	</#list>
</#macro>