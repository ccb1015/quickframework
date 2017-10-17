<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity 
public class ${className} implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	<#list table.columns as column>
	/**
	 * ${column.description}
	 */	
		<#if !column.fk >
			<#if column.pk>
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
			<#else>
	@Column(length = ${column.size?c},unique = ${(column.unique)?string("true","false")},nullable = ${(column.notNull)?string("false","true")})
			</#if>
			<#if column.isDateTimeColumn>
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
			</#if>
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>

<@generateJavaColumns/>


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

	public ${className}() {
		super();
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
	/**
	 * <#if foreignKey.description??>${foreignKey.description}</#if>
	 */
	@ManyToOne(cascade={CascadeType.PERSIST},optional=true)
	@JoinColumn(name="${foreignKey.parentSqlColumn}")
	private ${fkPojoClass} ${fkFieldName?uncap_first};
	
	public void set${fkFieldName}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkFieldName?uncap_first} = ${fkPojoClassVar};
	}
	public ${fkPojoClass} get${fkFieldName}() {
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
	/**
	 * <#if foreignKey.description??>${foreignKey.description}</#if>
	 */
	<#if foreignKey.mainFk?? && foreignKey.mainFk=="1">
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name="${foreignKey.relTableName}", 
			joinColumns={@JoinColumn(name="${foreignKey.parentSqlColumn}")},
			inverseJoinColumns={@JoinColumn(name="${foreignKey.sqlColumnName}")})
	private List<${fkPojoClass}> ${fkFieldName?uncap_first} = new ArrayList<${fkPojoClass}>(0);

	public void set${fkFieldName}(List<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkFieldName?uncap_first} = ${fkPojoClassVar};
	}
	public List<${fkPojoClass}> get${fkFieldName}() {
		return ${fkFieldName?uncap_first};
	}
	</#if>
		</#if>
	</#list>
</#macro>
