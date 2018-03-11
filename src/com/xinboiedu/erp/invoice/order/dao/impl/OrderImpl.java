package com.xinboiedu.erp.invoice.order.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.invoice.order.dao.dao.OrderDao;
import com.xinboiedu.erp.invoice.order.vo.OrderModel;
import com.xinboiedu.erp.invoice.order.vo.OrderQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class OrderImpl extends BaseImpl<OrderModel> implements OrderDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		OrderQueryModel oqm = (OrderQueryModel) bqm;
		//增加条件
		if(oqm.getOrderType()!=null&&oqm.getOrderType()!=-1){
			criteria.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if(oqm.getOrderTypes()!=null&&oqm.getOrderTypes().length>0){
			criteria.add(Restrictions.in("orderType", oqm.getOrderTypes()));
		}
		//下单人查询
		if(oqm.getCreater()!=null&&oqm.getCreater().getName()!=null&&oqm.getCreater().getName().trim().length()>0){
			criteria.createAlias("creater", "c1");
			criteria.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName()+"%"));	
		}
		//审核人查询
		if(oqm.getChecker()!=null&&oqm.getChecker().getName()!=null&&oqm.getChecker().getName().trim().length()>0){
			criteria.createAlias("checker", "c2");
			criteria.add(Restrictions.like("c2.name", "%"+oqm.getChecker().getName()+"%"));	
		}
		//跟单人查询
		if(oqm.getCompleter()!=null&&oqm.getCompleter().getName()!=null&&oqm.getCompleter().getName().length()>0){
			criteria.createAlias("completer", "p1");
			criteria.add(Restrictions.like("p1.name","%"+oqm.getCompleter().getName()+"%"));
		}
		//状态查询
		if(oqm.getStatus()!=null&&oqm.getStatus()!=-1){
			criteria.add(Restrictions.eq("status", oqm.getStatus()));
		}
		if(oqm.getStatuses()!=null&&oqm.getStatuses().length>0){
			criteria.add(Restrictions.in("status", oqm.getStatuses()));
		}
		//总量查询
		if(oqm.getTotalNum()!=null){
			criteria.add(Restrictions.ge("totalNum", oqm.getTotalNum()));
		}
		if(oqm.getTotalNum2()!=null){
			criteria.add(Restrictions.le("totalNum", oqm.getTotalNum2()));
		}		
		//总额查询
		if(oqm.getTotalPrice()!=null){
			criteria.add(Restrictions.ge("totalPrice", oqm.getTotalPrice()));
		}
		if(oqm.getTotalPrice2()!=null){
			criteria.add(Restrictions.le("totalPrice", oqm.getTotalPrice2()));
		}
		//下单时间
		if(oqm.getCreateTime()!=null){
			criteria.add(Restrictions.ge("createTime", oqm.getCreateTime()-1));
		}
		if(oqm.getCreateTime2()!=null){
			criteria.add(Restrictions.le("createTime", oqm.getCreateTime2()+86400000));
		}
		//审核时间
		if(oqm.getCheckedTime()!=null){
			criteria.add(Restrictions.ge("checkedTime", oqm.getCheckedTime()-1));
		}
		if(oqm.getCheckedTime2()!=null){
			criteria.add(Restrictions.le("checkedTime", oqm.getCheckedTime2()+86400000));
		}
		//供应商
		if(oqm.getSm()!=null&& oqm.getSm().getId() != null &&oqm.getSm().getId()!=-1){
			criteria.add(Restrictions.eq("sm.id", oqm.getSm().getId()));
		}
		
		//提货方式
		if(oqm.getSm()!=null&&oqm.getSm().getNeeds()!=null&&oqm.getSm().getNeeds()!=-1){
			criteria.createAlias("sm", "s1");
			criteria.add(Restrictions.eq("s1.needs", oqm.getSm().getNeeds()));
		}
		//订单号
		if(oqm.getOrderNum()!=null){
			
			criteria.add(Restrictions.like("orderNum", "%"+oqm.getOrderNum()+"%"));
		}
		
	}
}
