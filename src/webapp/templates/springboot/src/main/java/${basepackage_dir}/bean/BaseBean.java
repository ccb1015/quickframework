package ${basepackage}.bean;

import java.util.List;

public class BaseBean {

	private PageInfo pageInfo = new PageInfo();

	private String idList;

	private List objectList;

	/* get、set */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public List getObjectList() {
		return objectList;
	}

	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}

}
