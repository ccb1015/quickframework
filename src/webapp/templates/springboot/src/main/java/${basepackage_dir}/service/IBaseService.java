package ${basepackage}.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.bean.PageInfo;


public interface IBaseService<T,ID extends Serializable> {
	//CRUD
	<S extends T> S save(S entity);
	<S extends T> Iterable<S> save(Iterable<S> entities);
	T findOne(ID id);
	boolean exists(ID id);
	Iterable<T> findAll();
	Iterable<T> findAll(Iterable<ID> ids);
	long count();
	void delete(ID id);
	void delete(T entity);
	void delete(Iterable<? extends T> entities);
	void deleteAll();
	Page<T> findAll(Pageable pageable);
	
	//JpaRepository
	void deleteInBatch(Iterable<T> entities);
	void deleteAllInBatch();
	T getOne(ID id);
	
	List<T> findList(PageInfo pageInfo);
}
