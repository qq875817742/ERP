package com.xinboiedu.erp.invoice.orderDetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.invoice.orderDetail.dao.dao.OrderDetailDao;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class OrderDetailImpl extends BaseImpl<OrderDetailModel> implements OrderDetailDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		OrderDetailQueryModel oqm = (OrderDetailQueryModel) bqm;
	}
}
