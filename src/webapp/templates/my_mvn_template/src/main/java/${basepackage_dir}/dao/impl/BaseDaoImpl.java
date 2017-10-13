<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ${basepackage}.dao.IBaseDao;
import ${basepackage}.entity.PageInfo;
import ${basepackage}.dao.IBaseDao;
import ${basepackage}.entity.PageInfo;

<#include "/java_imports.include">
public class BaseDaoImpl<T,I> implements IBaseDao<T,I> {

	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;

	private Class entityClass = null;
	{
		// 泛类的转换
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass = (Class) type.getActualTypeArguments()[0];
	}
	
	public void saveOrUpdate(T entity) {
		this.hibernateTemplate.saveOrUpdate(entity);
	}

	public void saveOrUpdate(List<T> list) {
		this.hibernateTemplate.saveOrUpdateAll((Collection) list);
	}

	public void deleteByCollection(Collection list) {
		this.hibernateTemplate.deleteAll(list);
	}

	public void delete(I id) {
		this.hibernateTemplate.delete(this.findById(id));
		;
	}

	public T findById(I id) {
		return (T) this.hibernateTemplate.get(entityClass,(Serializable) id);
	}
	
	public List<T> findList(List<I> ids){
		return this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(entityClass).add(Restrictions.in("id", ids)));
	}
	
	public List<T> findList(PageInfo pageInfo){
		return this.findList(pageInfo,null);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findList(PageInfo pageInfo,Criterion criterion) {
		final Map<String,Object> map = pageInfo.getQuerys();
		final Map<String, Object> matchMaps = pageInfo.getMatchMaps();
		final Criterion restrictions =criterion;
		final String sortAttribute = pageInfo.getSortAttribute();
		final String sortType = pageInfo.getSortType();
		final int firstResult = pageInfo.getFirstResult();
		final int pageSize = pageInfo.getPageSize();
		//totalCount
		List object = this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					Criteria criteria = createQuery(session,map, matchMaps, restrictions);
					criteria.setProjection(Projections.rowCount());
					return criteria.list();
				}
			});
		int count=0;
		if(object != null && object.size()>0){
			count = Integer.parseInt(object.get(0).toString());
		}
		pageInfo.setTotalCounts(count);
		List<T> list= this.hibernateTemplate.executeFind(new HibernateCallback() {
			public List<?> doInHibernate(Session session)
					throws HibernateException, SQLException {
					Criteria criteria = createQuery(session,map, matchMaps, restrictions);
					//order
					if (StringUtils.isNotBlank(sortAttribute)) {
						if ("asc".equals(sortType.toLowerCase())) {
							criteria.addOrder(Order.asc(sortAttribute));
						}
					}
					return criteria.setFirstResult(firstResult)
							.setMaxResults(pageSize).list();
				}
			});
		return list;
	}
	private Criteria createQuery(Session session,final Map<String, Object> map,
		final Map<String, Object> matchMaps,
		final Criterion restrictions) {
		Criteria criteria = session.createCriteria(entityClass);
		// eq
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				criteria.add(Restrictions.eq(entry.getKey(),entry.getValue()));
			}
		}
		if(restrictions!=null){
			criteria.add(restrictions);
		}
		//like
		if(matchMaps!=null && matchMaps.size()>0){
			Criterion orCriterion=null;
			for (Entry<String,Object> item : matchMaps.entrySet()) {
				Criterion tmp = Restrictions.ilike(item.getKey(), item.getValue()+"",MatchMode.ANYWHERE);
				if(orCriterion==null){
					orCriterion =tmp;
				}else {
					orCriterion = Restrictions.or(orCriterion, tmp);
				}
			}
			if(orCriterion != null){
				criteria.add(orCriterion);
			}
		}
		return criteria;
	}
	public List<T> loadAll() {
		return this.hibernateTemplate.loadAll(entityClass);
	}
}