package com.bonc.quickframework.generator.entity;

import com.bonc.quickframework.entity.GeneratorField;
import com.bonc.quickframework.generator.helper.StringHelper;

public class Column {

	private String columnName;
	private String sqlName;
	private int size=20;
	private boolean pk=false;
	private boolean fk=false;//--
	private boolean notNull=false;
	private boolean unique=false;
	private String defaultValue;
	private String javaType;
	private String description;
	private boolean search=false;
	
	private boolean isShow=false;
	private boolean isSort=false;
	private java.lang.String showField;
	private boolean isSearchCondition=false;
	private java.lang.String searchField;
	private java.lang.String parentFieldOfReferencedObject;
	
	
	private GeneratorField field;
	
	//
	private Table table;
	private boolean isDateTimeColumn = false;

	public Column(){}
	
	public Column(String name,String description){
		this.columnName=StringHelper.javaTypeName(name);
		this.sqlName=StringHelper.sqlTypeName(this.columnName);
		this.description=StringHelper.isBlank(description)?this.columnName:description;
	}
	
	// 是否为日期
	public boolean getIsDateTimeColumn() {
		return this.javaType.equalsIgnoreCase("Date")
				|| this.javaType.equalsIgnoreCase("java.util.Date");
	}

	//
	public String getColumnNameLower() {
		return StringHelper.uncapitalize(this.columnName);
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public boolean getPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public boolean getFk() {
		return fk;
	}

	public void setFk(boolean fk) {
		this.fk = fk;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int length) {
		this.size = length;
	}

	public boolean getNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public boolean getUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getJavaType() {
		return this.javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public GeneratorField getField() {
		return field;
	}

	public void setField(GeneratorField field) {
		this.field = field;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public boolean isSort() {
		return isSort;
	}

	public void setSort(boolean isSort) {
		this.isSort = isSort;
	}
	
	public java.lang.String getShowField() {
		return showField;
	}

	public void setShowField(java.lang.String showField) {
		this.showField = showField;
	}

	public boolean isSearchCondition() {
		return isSearchCondition;
	}

	public void setSearchCondition(boolean isSearchCondition) {
		this.isSearchCondition = isSearchCondition;
	}

	public java.lang.String getSearchField() {
		return searchField;
	}

	public void setSearchField(java.lang.String searchField) {
		this.searchField = searchField;
	}

	public java.lang.String getParentFieldOfReferencedObject() {
		return parentFieldOfReferencedObject;
	}

	public void setParentFieldOfReferencedObject(
			java.lang.String parentFieldOfReferencedObject) {
		this.parentFieldOfReferencedObject = parentFieldOfReferencedObject;
	}
	
	

	
}
