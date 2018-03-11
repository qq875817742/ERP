package com.xinboiedu.erp.invoice.goods.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.invoice.goods.dao.dao.GoodsDao;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.goods.vo.GoodsQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class GoodsImpl extends BaseImpl<GoodsModel> implements GoodsDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		GoodsQueryModel gqm = (GoodsQueryModel) bqm;
		if(gqm.getName()!=null&&gqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+gqm.getName().trim()+"%"));
		}
		if(gqm.getOrigin()!=null&&gqm.getOrigin().trim().length()>0){
			criteria.add(Restrictions.like("origin", "%"+gqm.getOrigin().trim()+"%"));
		}
		if(gqm.getProducer()!=null&&gqm.getProducer().trim().length()>0){
			criteria.add(Restrictions.like("producer", "%"+gqm.getProducer().trim()+"%"));
		}
		if(gqm.getUnit()!=null&&gqm.getUnit().trim().length()>0){
			criteria.add(Restrictions.eq("unit", gqm.getUnit().trim()));
		}
		if(gqm.getGtm()!=null&&gqm.getGtm().getSm()!=null&& gqm.getGtm().getSm().getId() != null &&gqm.getGtm().getSm().getId()!=-1){
			criteria.createAlias("gtm", "g");
			criteria.createAlias("g.sm", "s");
			criteria.add(Restrictions.eq("s.id", gqm.getGtm().getSm().getId()));
		}
		if(gqm.getInPrice()!=null){
			criteria.add(Restrictions.ge("inPrice", gqm.getInPrice()));
		}
		if(gqm.getInPrice2()!=null){
			criteria.add(Restrictions.le("inPrice", gqm.getInPrice2()));
		}
		if(gqm.getOutPrice()!=null){
			criteria.add(Restrictions.ge("outPrice", gqm.getOutPrice()));
		}
		if(gqm.getOutPrice2()!=null){
			criteria.add(Restrictions.le("outPrice", gqm.getOutPrice2()));
		}
		
	}

	@Override
	public List<GoodsModel> getByGtm(Long id) {
		String hql="from GoodsModel where gtm.id=?";
		return (List<GoodsModel>) getHibernateTemplate().find(hql, id);
	}
}
