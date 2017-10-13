package com.bonc.quickframework.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bonc.quickframework.entity.GeneratorEntity;
import com.bonc.quickframework.entity.GeneratorField;
import com.bonc.quickframework.entity.GeneratorProject;
import com.bonc.quickframework.generator.entity.Column;
import com.bonc.quickframework.generator.entity.FilterColumn;
import com.bonc.quickframework.generator.entity.Table;
import com.bonc.quickframework.generator.helper.StringHelper;

public class DataParser {

	/**
	 * 项目信息
	 */
	public static Map<String, Object> parseProjectData(GeneratorProject project,String outRootPath) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (project == null) {
			throw new IllegalStateException("project info can't be null.");
		}
		map.put("now", new Date());
		map.put("projectId", project.getId());
		map.put("projectCode", project.getCode());
		map.put("projectName", project.getName());
		map.put("basepackage", project.getBasepackage());
		//map.put("namespace", project.getNamespace());

		map.put("basepackage_dir", project.getBasepackage().replace('.', '/'));
		//代码所在磁盘和路径
		String diskName="";
		String codePath = "";
		if(StringHelper.isNotBlank(outRootPath)){
			codePath = (new File(outRootPath).getAbsolutePath()).replace("\\", "/");
			if(codePath.indexOf(":/")>0){
				diskName = codePath.split(":/")[0] + ":";
			}
		}
		map.put("codeDisk", diskName);
		map.put("codePath", codePath);
		return map;
	}

	/**
	 * entitys 信息
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static List<Table> parseTableData(Collection<GeneratorEntity> entitys) throws Exception{
		List<Table> tables = new ArrayList<Table>();
		// table
		Map<String, Table> mapTableCache = new HashMap<String, Table>();
		for (GeneratorEntity entity : entitys) {
			Table table = new Table(entity.getCode(), entity.getName());
			mapTableCache.put(table.getSqlName(), table);
		}
		// cloumn
		for (GeneratorEntity entity : entitys) {
			//Set<GeneratorField> fields = entity.getGeneratorFields();
			List<GeneratorField> fields=new ArrayList<GeneratorField>();
			fields.addAll(entity.getGeneratorFields());
			Collections.sort(fields, new Comparator<GeneratorField>(){
				public int compare(GeneratorField obj0, GeneratorField obj1) {
					if(obj0.getSequence()==null){
						return 1;
					}else if(obj1.getSequence()==null){
						return -1;
					}
					return obj0.getSequence() >= obj1.getSequence() ? 1 : -1;
				}
			});
			
			for (GeneratorField f : fields) {
				boolean isFk=StringHelper.isNotBlank(f.getFkTypeColumn());
				if(isFk)continue;
				Column column = new Column(f.getCode(), f.getName());
				column.setPk(StringHelper.convert2Boolean(f.getPk()));
				column.setJavaType(findFieldJavaType(f));
				if (f.getLength() != null) {
					column.setSize(f.getLength()==null?20:Integer.valueOf(f.getLength()+ ""));
				}
				column.setNotNull(StringHelper.convert2Boolean(f.getNotNull()));
				column.setUnique(StringHelper.convert2Boolean(f.getIsUnique()));
				column.setDefaultValue(f.getDefaultValue());
				Table tb = mapTableCache.get(StringHelper.sqlTypeName(entity
						.getCode()));
				column.setFk(isFk);
				column.setTable(tb);
				Boolean isSeach = StringHelper.convert2Boolean(f.getIsSearch());
				column.setSearch(isSeach);
				column.setField(f);//直接持有field
				
				//2017.9.12添加
				column.setShow(StringHelper.convert2Boolean(f.getIsShow()));
				column.setSearchCondition(StringHelper.convert2Boolean(f.getIsSearchCondition()));
				column.setShowField(f.getShowField());
				column.setSearchField(f.getSearchField());
				
				//2017.10.11添加表头排序
				column.setSort(StringHelper.convert2Boolean(f.getIsSort()));
								
				//2017.9.30添加
				//找到自关联的实体，取出自关联的属性名
				String parentFieldOfReferencedObject = null;
				for (GeneratorEntity innerEntity : entitys) {
					if (f.getEntityTypeId()==innerEntity.getId()) {
						for (GeneratorField innerf : innerEntity.getGeneratorFields()) {
							if (innerf.getEntityId()==innerf.getEntityTypeId()) {
								parentFieldOfReferencedObject = innerf.getCode();
							}
						}
					}
				}
				if (parentFieldOfReferencedObject != null) {
					column.setParentFieldOfReferencedObject(parentFieldOfReferencedObject);
				}
				
				
				if(isSeach){
					tb.setSearchFieldCount(tb.getSearchFieldCount()+1);
				}
				if(!column.getFk()){
					tb.getColumns().add(column);
				}
				if (column.getPk()) {
					tb.getPrimaryKeyColumns().add(column);
				}
				
			}
		}
		// 外键
		for (GeneratorEntity entity : entitys) {
			Table currTb = mapTableCache.get(StringHelper.sqlTypeName(entity
					.getCode()));// 当前表
			Set<GeneratorField> fields = entity.getGeneratorFields();
			for (GeneratorField f : fields) {
				String fkTableColumn = f.getFkTypeColumn();
				if (StringHelper.isNotBlank(fkTableColumn)) {
					String fkTableName = f.getEntityType().getCode();
					Table fkTb = mapTableCache.get(StringHelper.sqlTypeName(fkTableName));// 外键表
					if(fkTb.getPrimaryKeyColumns()==null){
						throw new Exception("table["+fkTb.getClassName()+"]没有主键.");
					}
					Column fkPkColumn= fkTb.getPrimaryKeyColumns().getFirst();
					
					//找到自关联的实体，取出自关联的属性名
					String parentFieldOfReferencedObject = null;
					for (GeneratorEntity innerEntity : entitys) {
						if (f.getEntityTypeId()==innerEntity.getId()) {
							for (GeneratorField innerf : innerEntity.getGeneratorFields()) {
								if (innerf.getEntityId()==innerf.getEntityTypeId()) {
									parentFieldOfReferencedObject = innerf.getCode();
								}
							}
						}
					}
					
					//是否展示，是否作为搜索条件
					String referenceField = f.getCode();
					boolean isSort = StringHelper.convert2Boolean(f.getIsSort());
					boolean isShow = StringHelper.convert2Boolean(f.getIsShow());
					boolean isSearchCondition = StringHelper.convert2Boolean(f.getIsSearchCondition());
					String ShowField = f.getShowField();
					String SearchField = f.getSearchField();
					/*if(fkTableColumn.equalsIgnoreCase("OneToMany")){
						String parentTable=currTb.getClassName();
						String parentColumn=currTb.getPrimaryKeyColumns().getFirst().getColumnName();
						String fkColumn=currTb.getClassName()+"_"+currTb.getPrimaryKeyColumns().getFirst().getColumnName();
						
						currTb.addForeignKey(fkTableName,fkColumn,f.getCode(),null,"OneToMany",null,null,f.getName(),null,f,null);
						fkTb.addForeignKey(parentTable,parentColumn,fkColumn ,null,"ManyToOne",null,null,null,null);
					}*/
					if(fkTableColumn.equalsIgnoreCase("ManyToOne")){
						String parentTable=currTb.getClassName();
						String parentColumn=fkTableName+"_"+fkPkColumn.getColumnName();
						//String parentColumn=parentTable+"_"+fkTableName+"_"+fkPkColumn.getColumnName();
						String fkColumn=fkPkColumn.getColumnName();
						if(fkTableName.equals(parentTable)){//自关联（避免覆盖）
							fkTableName=StringHelper.javaTypeName(fkTableName);
							parentTable=StringHelper.sqlTypeName(parentTable);
						}
						//过滤数据字段
						FilterColumn filterColumn=null;
						if(f.getFilterFieldId()!=null && f.getFilterFieldId()!=0){
							if(f.getFilterType()==null){
								throw new Exception("实体("+currTb.getClassName()+")的字段("+f.getCode()+")关联属性和过滤列表设置不正确.");
							}
							GeneratorField filter = f.getFilterField();
							filterColumn=new FilterColumn(currTb.getClassName(),f.getEntityType().getCode(), 
									f.getCode(), filter.getEntityType().getCode(), filter.getCode(),f.getFilterType().getCode());
						}
						if(f.getShowColumn()==null){
							throw new Exception("实体("+currTb.getClassName()+")的字段("+f.getCode()+")未设置显示属性.");
						}
						currTb.addForeignKey(fkTableName,fkColumn,parentColumn,f.getCode() ,"ManyToOne",null,null,f.getName(),
								f.getShowColumn().getCode(),f,filterColumn,isShow,isSearchCondition,ShowField,SearchField,isSort,
								referenceField,parentFieldOfReferencedObject);
//						fkTb.addForeignKey(parentTable,parentColumn ,fkColumn,null,"OneToMany",fkTb.getDescription(),
//								null,null,null,isShow,isSearchCondition,ShowField,SearchField,referenceField);
					}
					if(fkTableColumn.equalsIgnoreCase("ManyToMany")){
						String relTableName =currTb.getClassName()+"_"+fkTableName+"_rel";
						String parentTable=currTb.getClassName();
						String parentColumn=currTb.getClassName()+"_"+currTb.getPrimaryKeyColumns().getFirst().getColumnName();
						String fkColumn=fkTableName+"_"+fkPkColumn.getColumnName();
						if(f.getShowColumn()==null){
							throw new Exception("实体("+currTb.getClassName()+")的字段("+f.getCode()+")未设置显示属性.");
						}
						currTb.addForeignKey(fkTableName, fkColumn,parentColumn,f.getCode(),"ManyToMany",String.valueOf(f.getMainFk()),
								relTableName,f.getName(),f.getShowColumn().getCode(),f,null,isShow,isSearchCondition,
								ShowField,SearchField,isSort,referenceField,parentFieldOfReferencedObject);
//						fkTb.addForeignKey(parentTable,parentColumn,fkColumn ,null,"ManyToMany",fkTb.getDescription(),relTableName,
//								null,null,isShow,isSearchCondition,ShowField,SearchField,referenceField);
					}
				}
			}
		}
		//
		for (Map.Entry<String, Table> m : mapTableCache.entrySet()) {
			tables.add(m.getValue());
		}
		return tables;
	}

	
	private static String findFieldJavaType(GeneratorField field) throws Exception{
		
		if("ManyToMany".equalsIgnoreCase(field.getFkTypeColumn())){
			if(field.getEntityType()!=null){
				return StringHelper.javaTypeName(field.getEntityType().getCode());
			}
		}else{
			if(field.getDataType()!=null){
				return field.getDataType().getFullType();
			}
		}
		
		if(field.getDataType()!=null){
			return field.getDataType().getFullType();
		}
		throw new Exception("字段【"+field.getCode()+"(id:"+field.getId()+")"+"】类型设置有误");
	}
}
