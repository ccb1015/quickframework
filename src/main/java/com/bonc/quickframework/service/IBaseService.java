package com.bonc.quickframework.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bonc.quickframework.bean.BaseBean;
import com.bonc.quickframework.bean.BaseBean;

import java.util.*;

/**
 * @author dguanlin email:dguanlin(a)163.com
 * @version 1.0
 * @since 1.0
 */
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
