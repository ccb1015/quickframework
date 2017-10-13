package ${basepackage}.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.bean.BaseBean;
import ${basepackage}.dao.IBaseDao;
import ${basepackage}.service.IBaseService;

<#include "/java_imports.include">
public abstract class BaseServiceImpl<T,I> implements IBaseService<T,I> {

	abstract protected IBaseDao<T,I> getCurrentDao();

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdate(T entity) {
		this.getCurrentDao().saveOrUpdate(entity);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdate(List<T> list) {
		this.getCurrentDao().saveOrUpdate(list);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(I id) {
		this.getCurrentDao().delete(id);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteByCollection(Collection list) {
		this.getCurrentDao().deleteByCollection(list);
	}

	public T findById(I id) {
		return this.getCurrentDao().findById(id);
	}

	public List<T> findList(BaseBean bean) {
		return this.getCurrentDao().findList(bean.getPageInfo(),null);
	}
	public List<T> findList(List<I> ids) {
		return this.getCurrentDao().findList(ids);
	}
	public List<T> loadAll() {
		return this.getCurrentDao().loadAll();
	}

}
