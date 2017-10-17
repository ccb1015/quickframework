package ${basepackage}.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import ${basepackage}.bean.PageInfo;

public class PageInfoUtil {
	public static Pageable retirevePageInfo(PageInfo pageInfo){
		Direction sortType = "DESC".equalsIgnoreCase(pageInfo.getSortType())?Direction.DESC:Direction.ASC;
		String sortAttribute = ("".equals(pageInfo.getSortAttribute()) || pageInfo.getSortAttribute() == null)?
				"id":pageInfo.getSortAttribute();
		int pageSize = pageInfo.getPageSize();
		int currentPage = pageInfo.getCurrentPage();
		
		Sort sort = new Sort(sortType, sortAttribute);
		Pageable pageable = new PageRequest(currentPage, pageSize, sort);
		return pageable;
	}
}
