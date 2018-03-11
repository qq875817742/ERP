package com.xinboiedu.erp.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.AggregateProjection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xinboiedu.erp.invoice.bill.dao.dao.BillDao;
import com.xinboiedu.erp.invoice.bill.vo.BillQueryModel;
	import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class BillImpl extends HibernateDaoSupport implements BillDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		BillQueryModel qm = (BillQueryModel) bqm;
	}

	@Override
	public List<Object[]> getBuyBill(BillQueryModel bqm) {
		/*SELECT
		   od.goodsId,g.name, SUM(od.num) 
		FROM
		     t_orderdetail od,
		     t_goods g
		WHERE
		     g.id=od.goodsId
		GROUP BY
		    od.goodsId*/
		
//		String hql="select gm,sum(num) from OrderDetailModel group by gm.id";
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderDetailModel.class);
		//select查询的内容
		ProjectionList projectionList = Projections.projectionList();
		
		AggregateProjection sum = Projections.sum("num");
		projectionList.add(sum);
		//分组(投影)
		PropertyProjection groupProperty = Projections.groupProperty("gm");
		projectionList.add(groupProperty);
		criteria.setProjection(projectionList);
		
		//条件
		criteria.createAlias("om", "o");
		if(bqm.getType()!=null&&bqm.getType()!=-1){
			criteria.add(Restrictions.eq("o.orderType", bqm.getType()));			
		}
		if(bqm.getSupplierId()!=null&&bqm.getSupplierId()!=-1){
			criteria.createAlias("o.sm", "s");
			criteria.add(Restrictions.eq("s.id", bqm.getSupplierId()));
		}
		
		
		
		
		List<Object[]> buyBillList = (List<Object[]>) getHibernateTemplate().findByCriteria(criteria);
		return buyBillList;
	}

	@Override
	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderDetailModel.class);
		criteria.add(Restrictions.eq("gm.id", bqm.getGoodsId()));
		//条件
				criteria.createAlias("om", "o");
				if(bqm.getType()!=null&&bqm.getType()!=-1){
					criteria.add(Restrictions.eq("o.orderType", bqm.getType()));			
				}
				if(bqm.getSupplierId()!=null&&bqm.getSupplierId()!=-1){
					criteria.createAlias("o.sm", "s");
					criteria.add(Restrictions.eq("s.id", bqm.getSupplierId()));
				}
				
				List<OrderDetailModel> findByCriteria = (List<OrderDetailModel>) getHibernateTemplate().findByCriteria(criteria);
				return findByCriteria;
	}
}
