package ${basepackage}.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import ${basepackage}.bean.PageInfo;

public class BaseRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	protected EntityManager em;
	private final Class<T> domainClass;
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.domainClass = domainClass;
		this.em=entityManager;
	}

	@Override
	public boolean support(String modelType) {
		return domainClass.getName().equals(modelType);
	}
	
	@Override
	public List<T> findList(PageInfo pageInfo) {
		List<T> list=null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(domainClass);
		Root<T> o = q.from(domainClass);
		q.select(o);
		//condition
		Specification<T> spec = getSpec(pageInfo);
		
		//totoal
		long totalCounts = this.count(spec);
		pageInfo.setTotalCounts(Integer.valueOf(totalCounts+""));
		
		//where
		Predicate predicate = spec.toPredicate(o, q, cb);
		q.where(predicate);
		
		//order
		javax.persistence.criteria.Order order=null;
		String sortAttribute = pageInfo.getSortAttribute();
		String sortType = pageInfo.getSortType();
		if (StringUtils.isNotBlank(sortAttribute)) {
			if ("desc".equals(sortType.toLowerCase())) {
				order = cb.desc(o.get(sortAttribute));
			}else{
				order = cb.asc(o.get(sortAttribute));
			}
		}
		if(order!=null){
			q.orderBy(order);
		}
		
		list = em.createQuery(q).setFirstResult(pageInfo.getFirstResult())
				.setMaxResults(pageInfo.getPageSize())
				.getResultList();
		
		return list;
	}
	/**
	 * 拼接查询条件
	 * @param pageInfo
	 * @return
	 */
	private Specification<T> getSpec(PageInfo pageInfo) {
		
		final Map<String, Object> equalsMap = pageInfo.getEqualsMap();
		final Map<String, Object> matchMaps = pageInfo.getMatchMap();
//		final Map<String, Object> gteMaps = pageInfo.getGteMap();
//		final Map<String, Object> lteMaps = pageInfo.getLteMap();
		
		Specification<T> spec = new Specification<T>() {  
		    public Predicate toPredicate(Root<T> o, CriteriaQuery<?> q, CriteriaBuilder cb) {  
		        List<Predicate> conditions = new ArrayList<Predicate>();
		       //eq
				if (equalsMap != null) {
					for (Map.Entry<String, Object> entry : equalsMap.entrySet()) {
//						ParameterExpression<Object> expression = cb.parameter(Object.class,"test");
//						Predicate condition = cb.equal(obj.get("name"), expression);
//						Predicate condition = cb.equal(obj.get("name"),"test");
						conditions.add(cb.equal(o.get(entry.getKey()),entry.getValue()));
					}
				}
				//like
				if (matchMaps != null && matchMaps.size()>0) {
					List<Predicate> querys = new ArrayList<Predicate>();
					for (Map.Entry<String, Object> entry : matchMaps.entrySet()) {
						querys.add(cb.like(o.get(entry.getKey()).as(String.class),"%"+entry.getValue()+"%"));
					}
					if(querys.size()>0){
						Predicate[] tmps = (Predicate[])querys.toArray(new Predicate[querys.size()]);
						conditions.add(cb.or(tmps));
					}
				}
				//
			/*	if (equalsMap != null) {
					for (Map.Entry<String, Object> entry : equalsMap.entrySet()) {
						conditions.add(cb.equal(o.get(entry.getKey()),entry.getValue()));
						cb.greaterThanOrEqualTo(o.get(entry.getKey()),entry.getValue()+"");
					}
				}*/
				
		        Predicate[] p = new Predicate[conditions.size()];  
		        return cb.and(conditions.toArray(p));  
		    }  
		};
		return spec;
	}
	
}
