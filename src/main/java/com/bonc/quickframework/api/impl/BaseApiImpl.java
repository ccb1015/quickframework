package com.bonc.quickframework.api.impl;

import java.util.List;

import com.bonc.quickframework.bean.BaseBean;
import com.bonc.quickframework.service.IBaseService;

public abstract class BaseApiImpl<T, I> {

	abstract protected IBaseService<T, I> getCurrentService();

	public T queryById(I id) {
		return getCurrentService().findById(id);
	}

	public List<T> queryAll() {
		return getCurrentService().loadAll();
	}

	public List<T> queryList(BaseBean bean) {
		return getCurrentService().findList(bean);
	}

}
