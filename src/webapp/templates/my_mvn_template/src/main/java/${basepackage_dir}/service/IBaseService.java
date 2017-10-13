package ${basepackage}.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import ${basepackage}.bean.BaseBean;
import ${basepackage}.bean.BaseBean;

<#include "/java_imports.include">
public interface IBaseService<T,I> {
	public void saveOrUpdate(T entity);
	public void saveOrUpdate(List<T> list);

	public void delete(I id);
	public void deleteByCollection(Collection list);
	
	public T findById(I id);
	public List<T> loadAll();
	public List<T> findList(List<I> ids);
	public List<T> findList(BaseBean bean);
	
}
