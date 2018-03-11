package com.xinboiedu.erp.auth.dep.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xinboiedu.erp.auth.dep.dao.dao.DepDao;
import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.dep.vo.DepQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class DepImpl extends BaseImpl<DepModel> implements DepDao{
	@Override
	public void doQBC(DetachedCriteria criteria, BaseQueryModel bqm) {
		DepQueryModel dqm= (DepQueryModel) bqm;
		if(dqm.getName()!=null&&dqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if(dqm.getTele()!=null&&dqm.getTele().trim().length()>0){
			criteria.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
	}

	
}
