package ${basepackage}.dao;

import ${basepackage}.entity.PageInfo;
import ${basepackage}.factory.SpringWiredBean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class DaoSupportUtil{
	
	protected HibernateTemplate hibernateTemplate = (HibernateTemplate)SpringWiredBean.getInstance().getBeanById("hibernateTemplate");

	/**
	 * 保存或者更新实体
	 * @param entity
	 */
	public boolean saveOrUpdate(Object entity){
		try {
			this.hibernateTemplate.saveOrUpdate(entity);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 根据sql查询结果
	 * @param String sql 查询sql语句
	 * @param Object[] values 查询条件里的变量的值
	 * @return List 返回所有符合条件的实体集
	 * ****/
	public List findBySql(String sql, Object[] values) {
		this.hibernateTemplate.setMaxResults(Integer.MAX_VALUE);
		return this.hibernateTemplate.find(sql, values);

	}
	/**
	 * 根据sql查询结果
	 * @param String sql 查询sql语句
	 * @return List 结果集 取出所有符合条件的结果
	 * ****/
	public List findBySql(String sql) {
		this.hibernateTemplate.setMaxResults(Integer.MAX_VALUE);
		return this.hibernateTemplate.find(sql);

	}
	/**
	 * 查询分页数据
	 * @param sql
	 * @param pageInfo
	 * @return
	 */
	public List findPage(String sql, PageInfo pageInfo) {
		if(pageInfo==null)pageInfo=new PageInfo();
		final String sql1 = sql;
		final int firstResult1 = (int) (pageInfo.getFirstResult());
		final int maxResults = (int) pageInfo.getPageSize();
		pageInfo.setTotalCounts(this.findRecordsCounts(sql1));
		return this.hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(sql1);
				query.setFirstResult(firstResult1);
				query.setMaxResults(maxResults);
				List l=query.list();
				return l;
			}
		});
	}
	
	
	/**
	 * 取得符合条件的记录数并保存到 PageInfo的totalCounts属性中
	 * @param String sql sql语句
	 * @param PageInfo pageInfo 可参考PageInfo类文档
	 * @return int 
	 * ****/
	public int findRecordsCounts(String sql) {
		int totalCounts = 0;
		String sql1 = "";
		if (sql.indexOf(" distinct ") >= 0) {
			QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(sql,
					sql, Collections.EMPTY_MAP,
					(SessionFactoryImplementor) this.hibernateTemplate.getSessionFactory());
			queryTranslator.compile(Collections.EMPTY_MAP, false);
			String tempSQL = queryTranslator.getSQLString();
			final String countSQL = "select count(*) from (" + tempSQL
					+ ") tmp_count_t";

			List list = (List)hibernateTemplate.execute(new HibernateCallback(){
		           public Object doInHibernate(Session session)
		                  throws HibernateException {
		        	   Query query =session.createQuery(countSQL);
	//		   			for (int i = 0; i < pageInfo.getValues().length; i++) {
	//						query.setParameter(i, pageInfo.getValues()[i]);
	//					}
		              List list=query.list();
		              return list;
		           }
		       });
			BigDecimal count = list == null || list.size() <= 0 ? new BigDecimal(0)
					: (BigDecimal) list.get(0);
			totalCounts = count.intValue();
		} else {
			if (sql.trim().toLowerCase().indexOf("from") == 0) {
				sql1 = "select count(*)   " + sql ;
			} else {
				sql1 = sql.substring(sql.toLowerCase().indexOf(" from "));
				sql1 = "select count(*)  " + sql1;
			}
			try {
				totalCounts = ((Number) this.findBySql(sql1).get(0)).intValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalCounts;
	}
}