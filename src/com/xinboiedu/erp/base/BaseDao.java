package com.xinboiedu.erp.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	void save(T t);

	List<T> getAll();
	
	T get(Serializable id);

	void update(T t);

	void delete(T t);

	List<T> getAll(BaseQueryModel bqm);

	List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount);

	Integer getCount(BaseQueryModel bqm);

}
