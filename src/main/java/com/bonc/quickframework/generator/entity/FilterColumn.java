package com.bonc.quickframework.generator.entity;

import com.bonc.quickframework.generator.helper.StringHelper;

public class FilterColumn {

	private String classTableName;// 所属表

	private String parentTypeName;// 当前类型
	private String parentColumnName;// 当前列名

	private String typeName;// 过滤类型
	private String columnName;// 过滤列名
	

	private String parentTypeColumnName;
	
	/**
	 * @param classTableName 所属表 , 如：User
	 * @param parentTypeName 当前类型，如：City
	 * @param parentColumnName 当前列名，如：userCity
	 * @param typeName 过滤类型  ，如：Province
	 * @param columnName 过滤列名，如：userProvice
	 * @param parentTypeColumnName 过滤列类型 和 关联对象 关联的属性名称，如：cityProvince(city中的字段)
	 */
	public FilterColumn(String classTableName, String parentTypeName,
			String parentColumnName, String typeName, String columnName,String parentTypeColumnName) {
		super();
		this.classTableName = StringHelper.javaTypeName(classTableName);
		this.parentTypeName = StringHelper.javaTypeName(parentTypeName);
		this.parentColumnName = StringHelper.javaTypeName(parentColumnName);
		this.typeName = StringHelper.javaTypeName(typeName);
		this.columnName = StringHelper.javaTypeName(columnName);
		this.parentTypeColumnName=StringHelper.javaTypeName(parentTypeColumnName);
	}

	public String getClassTableName() {
		return classTableName;
	}

	public void setClassTableName(String classTableName) {
		this.classTableName = classTableName;
	}

	public String getParentTypeName() {
		return parentTypeName;
	}

	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}

	public String getParentColumnName() {
		return parentColumnName;
	}

	public void setParentColumnName(String parentColumnName) {
		this.parentColumnName = parentColumnName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getParentTypeColumnName() {
		return parentTypeColumnName;
	}

	public void setParentTypeColumnName(String parentTypeColumnName) {
		this.parentTypeColumnName = parentTypeColumnName;
	}

}
