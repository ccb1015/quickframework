//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由模板自动生成 - 2016-03-25 15:47:28
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
 
public class GenUiPropertity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String name;
	private java.lang.Long typeId;
	private java.lang.String propKey;
	private java.lang.String propValue;
	private java.lang.String script;
	private java.lang.Long uiId;
	//columns END

	public GenUiPropertity(){
	}

	public GenUiPropertity(
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
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setTypeId(java.lang.Long value) {
		this.typeId = value;
	}
	
	public java.lang.Long getTypeId() {
		return this.typeId;
	}
	public void setPropKey(java.lang.String value) {
		this.propKey = value;
	}
	
	public java.lang.String getPropKey() {
		return this.propKey;
	}
	public void setPropValue(java.lang.String value) {
		this.propValue = value;
	}
	
	public java.lang.String getPropValue() {
		return this.propValue;
	}
	public void setScript(java.lang.String value) {
		this.script = value;
	}
	
	public java.lang.String getScript() {
		return this.script;
	}
	public void setUiId(java.lang.Long value) {
		this.uiId = value;
	}
	
	public java.lang.Long getUiId() {
		return this.uiId;
	}
	
	private GenPropType genPropType;
	
	public void setGenPropType(GenPropType genPropType){
		this.genPropType = genPropType;
	}
	
	public GenPropType getGenPropType() {
		return genPropType;
	}
	
	private GenUi genUi;
	
	public void setGenUi(GenUi genUi){
		this.genUi = genUi;
	}
	
	public GenUi getGenUi() {
		return genUi;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Name",getName())
			.append("TypeId",getTypeId())
			.append("PropKey",getPropKey())
			.append("PropValue",getPropValue())
			.append("Script",getScript())
			.append("UiId",getUiId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getName())
			.append(getTypeId())
			.append(getPropKey())
			.append(getPropValue())
			.append(getScript())
			.append(getUiId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GenUiPropertity == false) return false;
		if(this == obj) return true;
		GenUiPropertity other = (GenUiPropertity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getName(),other.getName())
			.append(getTypeId(),other.getTypeId())
			.append(getPropKey(),other.getPropKey())
			.append(getPropValue(),other.getPropValue())
			.append(getScript(),other.getScript())
			.append(getUiId(),other.getUiId())
			.isEquals();
	}
}

