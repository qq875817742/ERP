package com.xinboiedu.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.auth.res.dao.dao.ResDao;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.auth.res.vo.ResQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class ResImpl extends BaseImpl<ResModel> implements ResDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		ResQueryModel rqm = (ResQueryModel) bqm;
		if(rqm.getName()!=null&&rqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+rqm.getName().trim()+"%"));
		}
	}

	@Override
	public List<ResModel> getAllResByEmp(Long id) {
		String hql="select distinct res from EmpModel em join em.roles rm join rm.ress res where em.id=?";
		return (List<ResModel>) getHibernateTemplate().find(hql, id);
	}

}