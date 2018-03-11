package com.xinboiedu.erp.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.dep.vo.DepQueryModel;

public abstract class BaseImpl<T> extends HibernateDaoSupport{

	private Class<T> clazz;
	public BaseImpl()
	{
		//获取当前类运行的泛型父类
		Type type = this.getClass().getGenericSuperclass();
		//转换为参数化类型
		ParameterizedType pt = (ParameterizedType) type;
		//获取实际类型定义
		Type[] ts = pt.getActualTypeArguments();
		//赋值
		this.clazz = (Class<T>) ts[0];
	}
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	public List<T> getAll() {
		
		return getHibernateTemplate().loadAll(clazz);
	}

	public T get(Serializable id) {
		
		return getHibernateTemplate().get(clazz, id);
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	public List<T> getAll(BaseQueryModel bqm) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		doQBC(criteria, bqm);
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

	public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		//添加条件
		doQBC(criteria, bqm);
		return (List<T>) getHibernateTemplate().findByCriteria(criteria, (pageNum-1)*pageCount, pageCount);
	}

	public Integer getCount(BaseQueryModel bqm) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		//添加条件
		criteria.setProjection(Projections.rowCount());
		doQBC(criteria, bqm);
		 List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		return list.get(0).intValue();
	}
	public abstract void doQBC(DetachedCriteria criteria,BaseQueryModel bqm);
}
