package com.bonc.quickframework.util;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ServletUtil {
	public static String joinUriAndParms(String uri, String params) {
		if (org.apache.commons.lang.StringUtils.isBlank(params))
			return uri;
		if (params.startsWith("&"))
			params = params.substring(1);

		if (uri == null)
			return params;
		if (uri.indexOf(63) < 0)
			return uri + '?' + params;
		if (uri.endsWith("&"))
			return uri + params;

		return uri + "&" + params;
	}

	public static final String getForwardRequestURI(HttpServletRequest req) {
		String uri = (String) req
				.getAttribute("javax.servlet.forward.request_uri");

		if ((uri == null) || (uri.length() == 0))
			uri = req.getRequestURI();
		return uri;
	}

	public static final String getServletPath(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		if ((null != servletPath) && (!("".equals(servletPath)))) {
			return servletPath;
		}

		String requestUri = request.getRequestURI();
		int startIndex = (request.getContextPath().equals("")) ? 0 : request
				.getContextPath().length();

		int endIndex = (request.getPathInfo() == null) ? requestUri.length()
				: requestUri.lastIndexOf(request.getPathInfo());

		if (startIndex > endIndex) {
			endIndex = startIndex;
		}

		servletPath = requestUri.substring(startIndex, endIndex);
		if ((null != servletPath) && (!("".equals(servletPath))))
			return servletPath;

		return ((request.getPathInfo() == null) ? "" : request.getPathInfo());
	}

	public static final void sendAsJson(HttpServletResponse response, String str) {
		response.setContentType("application/json;charset=UTF-8");
		if (null != str)
			try {
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static final void sendAsText(HttpServletResponse response, String str) {
		response.setContentType("text/html;charset=UTF-8");
		if (null != str)
			try {
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static final Object getRequestValue(HttpServletRequest request,
			String parameter) {
		Object value = request.getParameter(parameter);
		if (org.apache.commons.lang.StringUtils.isEmpty((String) value))
			value = request.getAttribute(parameter);
		return value;
	}
}
