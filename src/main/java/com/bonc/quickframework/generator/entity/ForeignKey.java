package com.bonc.quickframework.generator.entity;

import com.bonc.quickframework.entity.GeneratorField;
import com.bonc.quickframework.generator.helper.StringHelper;

@SuppressWarnings("rawtypes")
public class ForeignKey {

	private Table table;
	private String parentName;//属性名称
	private String parentSqlName;
	private String parentColumn;//列名
	private String parentSqlColumn;
	private String description;//属性备注
	private String mainFk;
	
	private String relTableName;// 关联表（ManyToMany）
	private String relation;// OneToMany、ManyToOne、ManyToMany
	
	private String classTableName;//外键表
	private String sqlTableName;
	private String columnName; // 外键列
	private String sqlColumnName; 
	private String showColumnName;//显示外键列

	private GeneratorField field;
	private FilterColumn filterColumn;
	
	private boolean isSort=false;
	private boolean isShow=false;
	private java.lang.String showField;
	private boolean isSearchCondition=false;
	private java.lang.String searchField;
	private String referenceField;
	private String parentFieldOfReferencedObject;
	
	
	public ForeignKey(String tblName, String columnName, String parentColumn,String parentName,
			String relation,String mainFk,String relTableName,String description,String showColumnName) {
		this.classTableName = StringHelper.javaTypeName(tblName);
		this.sqlTableName = StringHelper.sqlTypeName(tblName);
		this.columnName = StringHelper.javaTypeName(columnName);
		this.sqlColumnName=StringHelper.sqlTypeName(columnName);
		this.relation = relation;
		this.mainFk=mainFk;
		//
		this.relTableName=StringHelper.sqlTypeName(relTableName);
		
		this.parentName=StringHelper.javaTypeName(parentName);
		this.parentSqlName=StringHelper.sqlTypeName(parentName);
		
		this.parentColumn = StringHelper.javaTypeName(parentColumn);
		this.parentSqlColumn = StringHelper.sqlTypeName(parentColumn);

		this.description=description;
		this.showColumnName=StringHelper.javaVarName(showColumnName);
		
	}
	
	public ForeignKey(String tblName, String columnName, String parentColumn,String parentName,
			String relation,String mainFk,String relTableName,String description,String showColumnName,
			boolean isShow, String showField, boolean isSearchCondition, String searchField, boolean isSort, String referenceField,
			String parentFieldOfReferencedObject) {
		this.classTableName = StringHelper.javaTypeName(tblName);
		this.sqlTableName = StringHelper.sqlTypeName(tblName);
		this.columnName = StringHelper.javaTypeName(columnName);
		this.sqlColumnName=StringHelper.sqlTypeName(columnName);
		this.relation = relation;
		this.mainFk=mainFk;
		//
		this.relTableName=StringHelper.sqlTypeName(relTableName);
		
		this.parentName=StringHelper.javaTypeName(parentName);
		this.parentSqlName=StringHelper.sqlTypeName(parentName);
		
		this.parentColumn = StringHelper.javaTypeName(parentColumn);
		this.parentSqlColumn = StringHelper.sqlTypeName(parentColumn);

		this.description=description;
		this.showColumnName=StringHelper.javaVarName(showColumnName);
		
		this.isSort=isSort;
		this.isShow=isShow;
		this.showField=StringHelper.javaVarName(showField);
		this.isSearchCondition=isSearchCondition;
		this.searchField=StringHelper.javaVarName(searchField);
		this.referenceField=StringHelper.javaVarName(referenceField);
		this.parentFieldOfReferencedObject=StringHelper.javaVarName(parentFieldOfReferencedObject);
	}

	// get、set

	public String getColumnName() {
		return columnName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getClassTableName() {
		return classTableName;
	}

	public void setClassTableName(String classTableName) {
		this.classTableName = classTableName;
	}

	public String getSqlTableName() {
		return sqlTableName;
	}

	public void setSqlTableName(String sqlTableName) {
		this.sqlTableName = sqlTableName;
	}

	public void setColumnName(String column) {
		this.columnName = column;
	}

	public String getParentColumn() {
		return parentColumn;
	}

	public String getParentSqlColumn() {
		return parentSqlColumn;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRelTableName() {
		return relTableName;
	}

	public void setRelTableName(String relTableName) {
		this.relTableName = relTableName;
	}

	public String getSqlColumnName() {
		return sqlColumnName;
	}

	public void setSqlColumnName(String sqlColumnName) {
		this.sqlColumnName = sqlColumnName;
	}

	public void setParentSqlColumn(String parentSqlColumn) {
		this.parentSqlColumn = parentSqlColumn;
	}

	public String getParentSqlName() {
		return parentSqlName;
	}

	public void setParentSqlName(String parentSqlName) {
		this.parentSqlName = parentSqlName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShowColumnName() {
		return showColumnName;
	}

	public void setShowColumnName(String showColumnName) {
		this.showColumnName = showColumnName;
	}

	public String getMainFk() {
		return mainFk;
	}

	public void setMainFk(String mainFk) {
		this.mainFk = mainFk;
	}

	public FilterColumn getFilterColumn() {
		return filterColumn;
	}

	public void setFilterColumn(FilterColumn filterColumn) {
		this.filterColumn = filterColumn;
	}

	public void setParentColumn(String parentColumn) {
		this.parentColumn = parentColumn;
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
	
	public boolean isSort() {
		return isSort;
	}

	public void setSort(boolean isSort) {
		this.isSort = isSort;
	}

	public String getReferenceField() {
		return referenceField;
	}

	public void setReferenceField(String referenceField) {
		this.referenceField = referenceField;
	}
	
	public String getParentFieldOfReferencedObject() {
		return parentFieldOfReferencedObject;
	}

	public void setParentFieldOfReferencedObject(
			String parentFieldOfReferencedObject) {
		this.parentFieldOfReferencedObject = parentFieldOfReferencedObject;
	}

	@Override
	public boolean equals(Object obj) {
		ForeignKey o = (ForeignKey) obj;
		return ((StringHelper.isBlank(this.classTableName)&&StringHelper.isBlank(o.getClassTableName()))||this.classTableName.equalsIgnoreCase(o.getClassTableName()))
				&&((StringHelper.isBlank(this.columnName)&&StringHelper.isBlank(o.getColumnName()))|| this.columnName.equalsIgnoreCase(o.getColumnName()))
				&& ((StringHelper.isBlank(this.parentColumn)&&StringHelper.isBlank(o.getParentColumn()))||this.parentColumn.equalsIgnoreCase(o.getParentColumn()))
				&& ((StringHelper.isBlank(this.relation)&&StringHelper.isBlank(o.getRelation()))||this.relation.equalsIgnoreCase(o.getRelation()));
	}

}