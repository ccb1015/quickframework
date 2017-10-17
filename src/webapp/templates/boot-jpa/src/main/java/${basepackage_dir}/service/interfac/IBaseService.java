package ${basepackage}.service.interfac;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBaseService<T,ID extends Serializable> {
	
	public T findOne(ID id);
	public List<T> findAll();
	public List<T> findAll(List<ID> ids);
	public Page<T> findAll(Pageable pageable);
	public List<T> findAll(Sort sort);
	
	public T save(T entity);
	public List<T> save(List<T> entities);
	public T saveAndFlush(T entity);
	
	public void delete(ID id);
	public void delete(T entity);
	public void deleteAll();
	public void delete(List<T> entities);
	public void deleteInBatch(List<T> entities);
	public void deleteAllInBatch();
	
	public long count();
	public boolean exists(ID id);
	
	public Page<T> findByAuto(T t, Pageable pageable);
	
}
