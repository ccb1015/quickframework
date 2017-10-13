--指定项目条件
--f.id in (
--select f.id from generator_field f
--left join generator_entity e on f.entity_id=e.id
--where e.project_id =90413
--)
--20160229,添加字段类型关联表，自定义类型关联处理
UPDATE generator_field f SET f.ENTITY_TYPE_ID=(
	SELECT e.ID from generator_entity e 
		where e.PROJECT_ID =(
				select e2.PROJECT_ID from generator_entity e2 WHERE e2.Id=f.ENTITY_ID
			)
		AND Lower(e.CODE)=Lower(f.TYPE) 
)
;
--20160229,添加字段类型关联表，基本类型关联处理
UPDATE generator_field f set f.DATA_TYPE_ID=(
	SELECT d.ID from generator_data_type d where Lower(d.CODE)=Lower(f.TYPE)
)

--20160303，更新关联字段code为id
UPDATE generator_field f SET f.show_column_id=(
	SELECT T.ID from generator_field T
    where t.entity_id =f.entity_type_id
		AND Lower(T.code) = Lower(f.show_column)
)
where f.entity_type_id is not null;