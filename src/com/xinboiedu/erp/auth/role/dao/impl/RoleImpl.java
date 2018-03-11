package com.xinboiedu.erp.auth.role.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.auth.role.dao.dao.RoleDao;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.auth.role.vo.RoleQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class RoleImpl extends BaseImpl<RoleModel> implements RoleDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		RoleQueryModel rqm = (RoleQueryModel) bqm;
		if(rqm.getName()!=null&&rqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+rqm.getName().trim()+"%"));
		}
		if(rqm.getCode()!=null&&rqm.getCode().trim().length()>0){
			criteria.add(Restrictions.like("code", "%"+rqm.getCode().trim()+"%"));
		}
	}
}
