package com.xinboiedu.erp.invoice.storeDetail.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.storeDetail.dao.dao.StoreDetailDao;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailModel;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailQueryModel;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class StoreDetailImpl extends BaseImpl<StoreDetailModel> implements StoreDetailDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		StoreDetailQueryModel sqm = (StoreDetailQueryModel) bqm;
	}

	@Override
	public StoreDetailModel getBySmAndGm(GoodsModel gm, StoreModel sm) {
		String hql="from StoreDetailModel where gm.id=? and sm.id=?";
		List<StoreDetailModel> list = (List<StoreDetailModel>) getHibernateTemplate().find(hql, gm.getId(),sm.getId());
		return list.size()>0?list.get(0):null;
	}
}
