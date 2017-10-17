package ${basepackage}.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ${basepackage}.repository.BaseRepository;
import ${basepackage}.service.interfac.IBaseService;

public abstract class BaseService<T, ID extends Serializable> implements IBaseService<T, ID>{
	
	abstract protected BaseRepository getCurrentRepository();
	
	/*
	 * 根据ID获取对象
	 */
	@Override
	public T findOne(ID id) {
		return (T) getCurrentRepository().findOne(id);
	}

	/*
	 * 获取所有对象信息
	 */
	@Override
	public List<T> findAll() {
		return getCurrentRepository().findAll();
	}

	/*
	 * 获取给定id的对象信息
	 */
	@Override
	public List<T> findAll(List<ID> ids) {
		return getCurrentRepository().findAll(ids);
	}

	/*
	 * 分页查询对象信息
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		return getCurrentRepository().findAll(pageable);
	}

	/*
	 * 排序查询对象信息
	 */
	@Override
	public List<T> findAll(Sort sort) {
		return getCurrentRepository().findAll(sort);
	}

	/*
	 * 保存对象信息
	 */
	@Override
	@Transactional
	public T save(T entity) {
		return (T) getCurrentRepository().save(entity);
	}

	/*
	 * 批量保存对象信息
	 */
	@Override
	@Transactional
	public List<T> save(List<T> entities) {
		return getCurrentRepository().save(entities);
	}
	
	/*
	 * 保存对象信息
	 */
	@Override
	@Transactional
	public T saveAndFlush(T entity) {
		return (T) getCurrentRepository().saveAndFlush(entity);
	}

	/*
	 * 删除id对象信息
	 */
	@Override
	@Transactional
	public void delete(ID id) {
		getCurrentRepository().delete(id);
	}
	
	/*
	 * 删除给定对象信息
	 */
	@Override
	@Transactional
	public void delete(T entity) {
		getCurrentRepository().delete(entity);
	}

	/*
	 * 删除所有对象信息
	 */
	@Override
	@Transactional
	public void deleteAll() {
		getCurrentRepository().deleteAll();
	}

	/*
	 * 批量删除对象信息
	 */
	@Override
	@Transactional
	public void delete(List<T> entities) {
		getCurrentRepository().delete(entities);
	}
	
	/*
	 * 批量删除对象信息
	 */
	public void deleteInBatch(List<T> entities){
		getCurrentRepository().deleteInBatch(entities);
	}
	
	/*
	 * 批量删除所有对象信息
	 */
	public void deleteAllInBatch(){
		getCurrentRepository().deleteAllInBatch();
	}

	/*
	 * 查询对象数量
	 */
	@Override
	public long count() {
		return getCurrentRepository().count();
	}

	/*
	 * 查询对象是否存在
	 */
	@Override
	public boolean exists(ID id) {
		return getCurrentRepository().exists(id);
	}
	
	/*
	 * 公共动态条件查询
	 */
	@Override
	public Page<T> findByAuto(T t, Pageable pageable){
		return getCurrentRepository().findByAuto(t, pageable);
	}

}
