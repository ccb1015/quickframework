package ${basepackage}.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ${basepackage}.repository.custom.BaseRepositoryCustom;

public class BaseRepositoryCustomImpl<T, I> implements BaseRepositoryCustom<T, I> {
	
	@PersistenceContext
	protected EntityManager em;
}
