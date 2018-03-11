package com.xinboiedu.erp.invoice.operDetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.invoice.operDetail.dao.dao.OperDetailDao;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailModel;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class OperDetailImpl extends BaseImpl<OperDetailModel> implements OperDetailDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		OperDetailQueryModel oqm = (OperDetailQueryModel) bqm;
	}
}
