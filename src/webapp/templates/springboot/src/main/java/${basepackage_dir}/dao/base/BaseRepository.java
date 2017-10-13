package ${basepackage}.dao.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ${basepackage}.bean.PageInfo;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends
		JpaRepository<T, ID> {

	boolean support(String modelType);

	List<T> findList(PageInfo pageInfo);
}
