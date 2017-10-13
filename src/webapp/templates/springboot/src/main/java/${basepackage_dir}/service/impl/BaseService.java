package ${basepackage}.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ${basepackage}.bean.PageInfo;
import ${basepackage}.dao.base.BaseRepository;
import ${basepackage}.service.IBaseService;

public abstract class BaseService<T, ID extends Serializable> implements
		IBaseService<T, ID> {

	public abstract BaseRepository<T, ID> getCurrentDao();

	@Override
	public <S extends T> S save(S entity) {
		return this.getCurrentDao().save(entity);
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return this.getCurrentDao().save(entities);
	}

	@Override
	public T findOne(ID id) {
		return this.getCurrentDao().findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return this.getCurrentDao().exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return this.getCurrentDao().findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		return this.getCurrentDao().findAll(ids);
	}

	@Override
	public long count() {
		return this.getCurrentDao().count();
	}

	@Override
	public void delete(ID id) {
		this.getCurrentDao().delete(id);
	}

	@Override
	public void delete(T entity) {
		this.getCurrentDao().delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		this.getCurrentDao().delete(entities);
	}

	@Override
	public void deleteAll() {
		this.getCurrentDao().deleteAll();
	}

	// ---------------

	@Override
	public Page<T> findAll(Pageable pageable) {
		return this.getCurrentDao().findAll(pageable);
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		this.getCurrentDao().deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch() {
		this.getCurrentDao().deleteAllInBatch();
	}

	@Override
	public T getOne(ID id) {
		return this.getCurrentDao().getOne(id);
	}

	@Override
	public List<T> findList(PageInfo pageInfo) {
		return this.getCurrentDao().findList(pageInfo);
	}
}
