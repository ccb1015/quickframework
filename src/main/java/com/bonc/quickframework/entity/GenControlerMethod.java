//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由模板自动生成 - 2016-03-25 15:47:32
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
 
public class GenControlerMethod implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.Long controllerId;
	private java.lang.String businessScript;
	private java.lang.Long scriptType;
	//columns END

	public GenControlerMethod(){
	}

	public GenControlerMethod(
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
	public void setControllerId(java.lang.Long value) {
		this.controllerId = value;
	}
	
	public java.lang.Long getControllerId() {
		return this.controllerId;
	}
	public void setBusinessScript(java.lang.String value) {
		this.businessScript = value;
	}
	
	public java.lang.String getBusinessScript() {
		return this.businessScript;
	}
	public void setScriptType(java.lang.Long value) {
		this.scriptType = value;
	}
	
	public java.lang.Long getScriptType() {
		return this.scriptType;
	}
	
	private GenController genController;
	
	public void setGenController(GenController genController){
		this.genController = genController;
	}
	
	public GenController getGenController() {
		return genController;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Name",getName())
			.append("ControllerId",getControllerId())
			.append("BusinessScript",getBusinessScript())
			.append("ScriptType",getScriptType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getCode())
			.append(getName())
			.append(getControllerId())
			.append(getBusinessScript())
			.append(getScriptType())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GenControlerMethod == false) return false;
		if(this == obj) return true;
		GenControlerMethod other = (GenControlerMethod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getCode(),other.getCode())
			.append(getName(),other.getName())
			.append(getControllerId(),other.getControllerId())
			.append(getBusinessScript(),other.getBusinessScript())
			.append(getScriptType(),other.getScriptType())
			.isEquals();
	}
}

