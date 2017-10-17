package ${basepackage}.bean;

import java.io.Serializable;

public class PageInfo implements Serializable{
	
	/*
	 * 分页大小，默认值为10
	 */
	private int pageSize = 10;
	/*
	 * 当前页数
	 */
	private int currentPage = 0;
	/*
	 * 排序类型(ASC,DESC)
	 */
	private String sortType;
	/*
	 * 排序属性
	 */
	private String sortAttribute;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getSortAttribute() {
		return sortAttribute;
	}
	public void setSortAttribute(String sortAttribute) {
		this.sortAttribute = sortAttribute;
	}
	public PageInfo() {
		super();
	}
	public PageInfo(int pageSize, int currentPage, String sortType, String sortAttribute) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.sortType = sortType;
		this.sortAttribute = sortAttribute;
	}
}
