//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由模板自动生成 - 2016-01-19 11:56:16
//     对此文件的更改可能会导致不正确的行为，并且如果 重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------
package com.bonc.quickframework.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;

/**
 * @author dguanlin email:dguanlin(a)163.com
 * @version 1.0
 * @since 1.0
 */
 
public class GeneratorMethodParam implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String paramMapType;
	private java.lang.String paramMapValue;
	private java.lang.String dataType;
	private java.lang.Long methodId;
	//columns END

	public GeneratorMethodParam(){
	}

	public GeneratorMethodParam(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setParamMapType(java.lang.String value) {
		this.paramMapType = value;
	}
	
	public java.lang.String getParamMapType() {
		return this.paramMapType;
	}
	public void setParamMapValue(java.lang.String value) {
		this.paramMapValue = value;
	}
	
	public java.lang.String getParamMapValue() {
		return this.paramMapValue;
	}
	public void setDataType(java.lang.String value) {
		this.dataType = value;
	}
	
	public java.lang.String getDataType() {
		return this.dataType;
	}
	public void setMethodId(java.lang.Long value) {
		this.methodId = value;
	}
	
	public java.lang.Long getMethodId() {
		return this.methodId;
	}
	
	private GeneratorServiceMethod generatorServiceMethod;
	
	public void setGeneratorServiceMethod(GeneratorServiceMethod generatorServiceMethod){
		this.generatorServiceMethod = generatorServiceMethod;
	}
	
	public GeneratorServiceMethod getGeneratorServiceMethod() {
		return generatorServiceMethod;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Name",getName())
			.append("Description",getDescription())
			.append("ParamMapType",getParamMapType())
			.append("ParamMapValue",getParamMapValue())
			.append("DataType",getDataType())
			.append("MethodId",getMethodId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getCode())
			.append(getName())
			.append(getDescription())
			.append(getParamMapType())
			.append(getParamMapValue())
			.append(getDataType())
			.append(getMethodId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GeneratorMethodParam == false) return false;
		if(this == obj) return true;
		GeneratorMethodParam other = (GeneratorMethodParam)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getCode(),other.getCode())
			.append(getName(),other.getName())
			.append(getDescription(),other.getDescription())
			.append(getParamMapType(),other.getParamMapType())
			.append(getParamMapValue(),other.getParamMapValue())
			.append(getDataType(),other.getDataType())
			.append(getMethodId(),other.getMethodId())
			.isEquals();
	}
}

