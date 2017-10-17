package ${basepackage}.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class MySpecs {
	public static <T> Specification<T> byAuto(final EntityManager em, final T example){
		
		final Class<T> type = (Class<T>) example.getClass();
		
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				EntityType<T> entity = em.getMetamodel().entity(type);
				for(javax.persistence.metamodel.Attribute<T, ?> attr: entity.getDeclaredAttributes()){
					Object attrValue = getValue(example, attr);
					if (attrValue != null) {
						if (attr.getJavaType() == String.class) {
							//string 类型使用模糊匹配
							if (!StringUtils.isEmpty(attrValue)) {
								predicates.add(cb.like(root.get(Attribute(entity, attr.getName(), String.class)),
										pattern((String) attrValue)));
							}
						}else if (attr.getJavaType() == byte.class || attr.getJavaType() == short.class
								|| attr.getJavaType() == int.class || attr.getJavaType() == long.class
								|| attr.getJavaType() == float.class || attr.getJavaType() == double.class
								|| attr.getJavaType() == boolean.class || attr.getJavaType() == char.class){
							//基本类型使用精确匹配
							predicates.add(cb.equal(root.get(Attribute(entity, attr.getName(), attrValue.getClass())),
									attrValue));
						}else if (Collection.class.isAssignableFrom(attr.getJavaType()) ) {
							//多对多
							if (((Collection)attrValue).size()>0) {
								Object object = ((List)attrValue).get(0);
								Class clazz = object.getClass();
								addPredicate(predicates, clazz, object, attr, root, query, cb);
								System.out.println("数组");
							}else {
								System.out.println("空数组");
								continue;
							}
						}else {
							//自定义类型
							Class clazz = attr.getJavaType();
							addPredicate(predicates, clazz, attrValue, attr, root, query, cb);
						}
					}
				}
				query.distinct(true);
				Predicate[] p = new Predicate[predicates.size()];  
				return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(p));
			}
			
			private List<Predicate> addPredicate(List<Predicate> predicates, Class clazz, Object object,
					javax.persistence.metamodel.Attribute<T, ?> attr, Path<T> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				EntityType<T> innerEntity = em.getMetamodel().entity(clazz);
				Join<Object, Object> join= ((From<Object, Object>) root).join(attr.getName(),JoinType.LEFT);
				for(javax.persistence.metamodel.Attribute<T, ?> innerAttr: innerEntity.getDeclaredAttributes()){
					Object innerAttrValue = getInnerValue(object, (javax.persistence.metamodel.Attribute<Object, ?>) innerAttr);
					if (innerAttrValue != null) {
						if (innerAttr.getJavaType() == String.class) {
							//string 类型使用模糊匹配
							if (!StringUtils.isEmpty(innerAttrValue)) {
								predicates.add(cb.like((Path)join.get(innerAttr.getName()),pattern((String) innerAttrValue)));
							}
						}else if (innerAttr.getJavaType() == byte.class || innerAttr.getJavaType() == short.class
								|| innerAttr.getJavaType() == int.class || innerAttr.getJavaType() == long.class
								|| innerAttr.getJavaType() == float.class || innerAttr.getJavaType() == double.class
								|| innerAttr.getJavaType() == boolean.class || innerAttr.getJavaType() == char.class){
							//基本类型使用精确匹配
							predicates.add(cb.equal(join.get(innerAttr.getName()),innerAttrValue));
						}else if (Collection.class.isAssignableFrom(innerAttr.getJavaType()) ) {
							//多对多
							if (((Collection)innerAttrValue).size()>0) {
								Object objectInCollection = ((List)innerAttrValue).get(0);
								Class clazzInCollection = object.getClass();
								addPredicate(predicates, clazzInCollection, objectInCollection, attr, root, query, cb);
								System.out.println("数组");
							}else {
								System.out.println("空数组");
								continue;
							}
						}else {
							//自定义类型
							Class InnerClazz = innerAttr.getJavaType();
							this.addPredicate(predicates, InnerClazz, innerAttrValue, innerAttr, (Path<T>) join, query, cb);
						}
					}
				}
				return predicates;
			}
			
			private Object getInnerValue(Object example, javax.persistence.metamodel.Attribute<Object, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			
			private <T> Object getValue(T example, javax.persistence.metamodel.Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}
			
			private <E, T> SingularAttribute<T, E> Attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) {
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}
		};
	}
	

	
	static private String pattern(String str){
		return "%"+str+"%";
	}
}
