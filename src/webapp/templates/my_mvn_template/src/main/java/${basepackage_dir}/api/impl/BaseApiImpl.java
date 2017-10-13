package ${basepackage}.api.impl;

import java.util.List;

import ${basepackage}.bean.BaseBean;
import ${basepackage}.service.IBaseService;

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
