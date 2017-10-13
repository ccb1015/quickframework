package com.bonc.quickframework.generator.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bonc.quickframework.entity.GeneratorField;
import com.bonc.quickframework.generator.helper.StringHelper;

public class Table {
	private String className;
	private String sqlName;
	private String description;
	private LinkedHashSet<Column> columns;
	private LinkedList<Column> primaryKeyColumns;
	private Integer searchFieldCount = 0;
	protected LinkedHashMap<String, ForeignKey> associatedTables;//关联表
	
	public Table(String tableName,String tableDescription){
		this.className=StringHelper.javaTypeName(tableName);
		this.sqlName=StringHelper.sqlTypeName(this.className).toUpperCase();
		this.description=StringHelper.isBlank(tableDescription)?this.className:tableDescription;
		
		this.columns = new LinkedHashSet();
		this.primaryKeyColumns=new LinkedList<Column>();
		this.associatedTables=new LinkedHashMap<String, ForeignKey>();
	}

	public void addForeignKey(String tableName, String columnName,String parentColumn,String parentName, String relation,
			String mainFk,String relTableName,String description,String showColumnName,boolean isShow,boolean isSearchCondition,
			String ShowField,String SearchField, boolean isSort, String referenceField, String parentFieldOfReferencedObject){
		this.addForeignKey(tableName, columnName, parentColumn, parentName, relation, mainFk, relTableName, description,
				showColumnName,null,null,isShow,isSearchCondition,ShowField,SearchField,isSort,referenceField,parentFieldOfReferencedObject);
	}
	
	/**
	 * @param tableName 关联表 对象名称
	 * @param columnName 关联列名称
	 * @param parentColumn 当前列名称
	 * @param parentName 当前字段名称
	 * @param relation 关联关系
	 * @param relTableName 中间表名称（ManyToMany）
	 * @param description 属性备注
	 * @param showColumnName 显示列（关联表）
	 */
	public void addForeignKey(String tableName, String columnName,String parentColumn,String parentName, String relation,String mainFk,
			String relTableName,String description,String showColumnName,GeneratorField field, FilterColumn filterColumn,
			boolean isShow,boolean isSearchCondition,String ShowField,String SearchField,boolean isSort,String referenceField,
			String parentFieldOfReferencedObject) {
		ForeignKey fk = new ForeignKey(tableName, columnName, parentColumn,parentName,relation,mainFk,relTableName,
				description,showColumnName,isShow,ShowField,isSearchCondition,SearchField,isSort,referenceField,parentFieldOfReferencedObject);
		for (Iterator itr = associatedTables.keySet().iterator(); itr.hasNext();) {
			ForeignKey value = associatedTables.get(itr.next());
			if(value.equals(fk)){
				if(StringHelper.isNotBlank(parentName)){
					value.setParentName(StringHelper.javaTypeName(parentName));
				}
				if(StringHelper.isNotBlank(description)){
					value.setDescription(description);
				}
				if(StringHelper.isNotBlank(showColumnName)){
					value.setShowColumnName(StringHelper.javaVarName(showColumnName));
				}
				return;
			}
		}
		fk.setField(field);
		fk.setFilterColumn(filterColumn);
		fk.setTable(this);
		this.associatedTables.put(tableName, fk);
	}
	
	//是否单列为主键
	public boolean getSingleId(){
		return this.getPrimaryKeyColumns()!=null && this.getPrimaryKeyColumns().size()==1;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LinkedHashSet<Column> getColumns() {
		return columns;
	}

	public void setColumns(LinkedHashSet<Column> columns) {
		this.columns = columns;
	}

	public LinkedList<Column> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}

	public void setPrimaryKeyColumns(LinkedList<Column> primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}

	public LinkedHashMap<String, ForeignKey> getAssociatedTables() {
		
		List<Map.Entry> mappingList = new ArrayList(this.associatedTables.entrySet());
		  //通过比较器实现比较排序
		  Collections.sort(mappingList, new Comparator<Map.Entry>(){
			  public int compare(Map.Entry mapping1,Map.Entry mapping2){
					GeneratorField f1= ( (ForeignKey)mapping1.getValue()).getField();
					GeneratorField f2= ( (ForeignKey)mapping2.getValue()).getField();
					if(f1==null){
						return 1;
					}else if(f2==null){
						return -1;
					}
					Long seq1= f1.getSequence();
					Long seq2= f2.getSequence();
				 	if(seq1==null){
						return 1;
					}else if(seq2==null){
						return -1;
					}
					return seq1 >= seq2 ? 1 : -1;
			  }
		  });
		//return associatedTables;
		 LinkedHashMap maps=new LinkedHashMap<String, ForeignKey>();
		 for(Map.Entry mapping:mappingList){
			 maps.put(mapping.getKey(), mapping.getValue());
		  }
		 return maps;
	}

	public void setAssociatedTables(LinkedHashMap<String, ForeignKey> associatedTables) {
		this.associatedTables = associatedTables;
	}

	public Integer getSearchFieldCount() {
		return searchFieldCount;
	}

	public void setSearchFieldCount(Integer searchFieldCount) {
		this.searchFieldCount = searchFieldCount;
	}

}
