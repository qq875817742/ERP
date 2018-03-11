package com.xinboiedu.erp.invoice.supplier.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.invoice.supplier.dao.dao.SupplierDao;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class SupplierImpl extends BaseImpl<SupplierModel> implements SupplierDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		SupplierQueryModel sqm = (SupplierQueryModel) bqm;
		if(sqm.getName()!=null&&sqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+sqm.getName().trim()+"%"));
		}
		if(sqm.getContact()!=null&&sqm.getContact().trim().length()>0){
			criteria.add(Restrictions.like("contact", "%"+sqm.getContact().trim()+"%"));
		}
		if(sqm.getTele()!=null&&sqm.getTele().trim().length()>0){
			criteria.add(Restrictions.like("tele", "%"+sqm.getTele().trim()+"%"));
		}
		if(sqm.getAddress()!=null&&sqm.getAddress().trim().length()>0){
			criteria.add(Restrictions.like("address", "%"+sqm.getAddress().trim()+"%"));
		}
		if(sqm.getNeeds()!=null&&sqm.getNeeds()!=-1){
			criteria.add(Restrictions.eq("needs", sqm.getNeeds()));
		}
		
	}

	@Override
	public List<SupplierModel> getAllUnion() {
		String hql="select distinct s from GoodsTypeModel gtm join gtm.sm s";
		return (List<SupplierModel>) getHibernateTemplate().find(hql);
	}

	@Override
	public List<SupplierModel> getAllUnionTwo() {
		String hql="select distinct s from GoodsModel gm join gm.gtm gt join gt.sm s";
		return (List<SupplierModel>) getHibernateTemplate().find(hql);
	}
	
	
}
