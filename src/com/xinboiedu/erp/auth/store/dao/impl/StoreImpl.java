package com.xinboiedu.erp.auth.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.auth.store.dao.dao.StoreDao;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.auth.store.vo.StoreQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class StoreImpl extends BaseImpl<StoreModel> implements StoreDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		StoreQueryModel sqm = (StoreQueryModel) bqm;
		if(sqm.getName()!=null&&sqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+sqm.getName()+"%"));
		}
		if(sqm.getEm()!=null&&sqm.getEm().getName()!=null&&sqm.getEm().getName().trim().length()>0){
			criteria.createAlias("em","e" );
			criteria.add(Restrictions.ilike("e.name", "%"+sqm.getEm().getName()+"%"));
		}
		
	}
}