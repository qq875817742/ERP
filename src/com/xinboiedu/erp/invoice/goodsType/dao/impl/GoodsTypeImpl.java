package com.xinboiedu.erp.invoice.goodsType.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.invoice.goodsType.dao.dao.GoodsTypeDao;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel) bqm;
		if(gqm.getName()!=null&&gqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+gqm.getName().trim()+"%"));
		}
		if(gqm.getSm()!=null&& gqm.getSm().getId() != null &&gqm.getSm().getId()!=-1){
			criteria.add(Restrictions.eq("sm.id", gqm.getSm().getId()));
		}
	}

	@Override
	public List<GoodsTypeModel> getBySm(Long id) {
		String hql="from GoodsTypeModel where sm.id=?";
		return (List<GoodsTypeModel>) getHibernateTemplate().find(hql, id);
	}

	@Override
	public List<GoodsTypeModel> getUnionBySm(Long id) {
		String hql="select distinct gt from GoodsModel gm join gm.gtm gt where gt.sm.id=?";
		return (List<GoodsTypeModel>) getHibernateTemplate().find(hql, id);
	}
}
