package com.bonc.quickframework.api.impl;

import org.springframework.stereotype.Service;

import com.bonc.quickframework.api.IHelloworldApi;
import com.bonc.quickframework.entity.PageInfo;

@Service("helloworldApi")
public class HelloworldApiImpl implements IHelloworldApi {

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	@Override
	public PageInfo getPageInfo(String currentPage) {

		PageInfo pageInfo = new PageInfo();
		if (currentPage != null) {
			pageInfo.setCurrentPage(Integer.valueOf(currentPage));
		}
		return pageInfo;
	}
}
