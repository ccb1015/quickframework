package ${basepackage}.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;

import ${basepackage}.entity.PageInfo;

<#include "/java_imports.include">
public interface IBaseDao<T,I> {

	public void saveOrUpdate(T entity);
	public void saveOrUpdate(List<T> list);

	public void delete(I id);
	public void deleteByCollection(Collection list);
	
	public T findById(I id);
	public List<T> loadAll();
	public List<T> findList(List<I> ids);
	public List<T> findList(PageInfo pageInfo,Criterion criterion);

}