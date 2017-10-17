package ${basepackage}.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> 
		implements BaseRepository<T, ID>{
	
	protected final Class<T> domainClass;
	protected EntityManager em;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
		this.em = em;
	}

	public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

	@Override
	public Page<T> findByAuto(T example, Pageable pageable) {
		return findAll(MySpecs.byAuto(em, example), pageable);
	}
}
