package com.xinboiedu.erp.invoice.bill.dao.dao;

import java.util.List;

import com.xinboiedu.erp.base.BaseDao;
import com.xinboiedu.erp.invoice.bill.vo.BillQueryModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;

public interface BillDao{


	List<Object[]> getBuyBill(BillQueryModel bqm);

	List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm);
}
