package com.xinboiedu.erp.invoice.orderDetail.web;

import java.util.List;
import com.xinboiedu.erp.invoice.orderDetail.business.ebi.OrderDetailEbi;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class OrderDetailAction extends BaseAction {

	private OrderDetailEbi orderDetailEbi;
	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}
	public OrderDetailModel om = new OrderDetailModel();
	public OrderDetailQueryModel oqm = new OrderDetailQueryModel();

	public String list() {
		setCount(orderDetailEbi.getCount(oqm));
		List<OrderDetailModel> orderDetailList = orderDetailEbi.getAll(oqm, pageNum, pageCount);
		put("orderDetailList", orderDetailList);
		return "list";
	}
	public String input() {
		if (om.getId() != null) { 
			om = orderDetailEbi.get(om.getId());
		}
		return "input";
	}
	public String save() {
		if (om.getId() == null) {
			orderDetailEbi.save(om);
			setSavePage(orderDetailEbi.getCount(oqm));
		} else {
			orderDetailEbi.update(om);
		}
		return "toList";
	}
	public String delete() {
		orderDetailEbi.delete(om);
		return "toList";
	}
	//=====================================================================
	//=============================AJAX================================
	//=====================================================================

	public OrderDetailModel getOm() {
		return om;
	}
	public String ajaxGetSurplus(){
		om=orderDetailEbi.get(om.getId());
		return "ajaxGetSurplus";
	}
	
	
}
