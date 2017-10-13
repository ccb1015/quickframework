package com.bonc.quickframework.api.impl;

import org.springframework.stereotype.Service;

import com.bonc.quickframework.api.IDemoApi;
import com.bonc.quickframework.entity.PageInfo;

@Service("demoApi")
public class DemoApiImpl implements IDemoApi {

	public String test(String name) {
		return "test success!";
	}
}
