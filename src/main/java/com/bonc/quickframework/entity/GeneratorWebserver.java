//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由模板自动生成 - 2016-01-14 10:01:23
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
 
public class GeneratorWebserver implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	//columns START
	private java.lang.Long id;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String ip;
	private java.lang.Integer port;
	private java.lang.String userName;
	private java.lang.String password;
	private java.lang.String serverType;
	private java.lang.Integer version;
	private java.lang.String url;
	//columns END

	public GeneratorWebserver(){
	}

	public GeneratorWebserver(
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
	public void setIp(java.lang.String value) {
		this.ip = value;
	}
	
	public java.lang.String getIp() {
		return this.ip;
	}
	public void setPort(java.lang.Integer value) {
		this.port = value;
	}
	
	public java.lang.Integer getPort() {
		return this.port;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setServerType(java.lang.String value) {
		this.serverType = value;
	}
	
	public java.lang.String getServerType() {
		return this.serverType;
	}
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	private Set<GeneratorProject> generatorProjects = new HashSet<GeneratorProject>(0);
	public void setGeneratorProjects(Set generatorProject){
		this.generatorProjects = generatorProject;
	}
	
	public Set<GeneratorProject> getGeneratorProjects() {
		return generatorProjects;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Name",getName())
			.append("Ip",getIp())
			.append("Port",getPort())
			.append("UserName",getUserName())
			.append("Password",getPassword())
			.append("ServerType",getServerType())
			.append("Version",getVersion())
			.append("Url",getUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getCode())
			.append(getName())
			.append(getIp())
			.append(getPort())
			.append(getUserName())
			.append(getPassword())
			.append(getServerType())
			.append(getVersion())
			.append(getUrl())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GeneratorWebserver == false) return false;
		if(this == obj) return true;
		GeneratorWebserver other = (GeneratorWebserver)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getCode(),other.getCode())
			.append(getName(),other.getName())
			.append(getIp(),other.getIp())
			.append(getPort(),other.getPort())
			.append(getUserName(),other.getUserName())
			.append(getPassword(),other.getPassword())
			.append(getServerType(),other.getServerType())
			.append(getVersion(),other.getVersion())
			.append(getUrl(),other.getUrl())
			.isEquals();
	}
}

