package ${basepackage}.web.action.original;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import ${basepackage}.bean.BaseBean;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {// ServletContextAware, SessionAware

	/**
	 * 通过struts注入对象
	 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	// protected ServletContext context;
	//protected Map<String, Object> session;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/*
	 * @Override public void setServletContext(ServletContext context) {
	 * this.context = context; }
	 * 
	 * @Override public void setSession(Map<String, Object> session) {
	 * this.session = session; }
	 */
}
