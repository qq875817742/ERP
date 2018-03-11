package com.xinboiedu.erp.invoice.orderDetail.business.ebo;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.orderDetail.business.ebi.OrderDetailEbi;
import com.xinboiedu.erp.invoice.orderDetail.dao.dao.OrderDetailDao;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class OrderDetailEbo implements OrderDetailEbi{

	private OrderDetailDao orderDetailDao;
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	@Override
	public void save(OrderDetailModel om) {
		orderDetailDao.save(om);
	}
	@Override
	public List<OrderDetailModel> getAll() {
		return orderDetailDao.getAll();
	}
	@Override
	public OrderDetailModel get(Serializable id) {
		return orderDetailDao.get(id);
	}
	@Override
	public void update(OrderDetailModel om) {
		orderDetailDao.update(om);
	}
	@Override
	public void delete(OrderDetailModel om) {
		orderDetailDao.delete(om);
	}
	@Override
	public List<OrderDetailModel> getAll(BaseQueryModel dqm) {
		return orderDetailDao.getAll(dqm);
	}
	@Override
	public List<OrderDetailModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return orderDetailDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return orderDetailDao.getCount(dqm);
	}
}
