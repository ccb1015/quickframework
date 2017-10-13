package com.bonc.quickframework.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bonc.quickframework.entity.PageInfo;

import java.util.*;

import org.hibernate.criterion.Criterion;

/**
 * @author dguanlin email:dguanlin(a)163.com
 * @version 1.0
 * @since 1.0
 */
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