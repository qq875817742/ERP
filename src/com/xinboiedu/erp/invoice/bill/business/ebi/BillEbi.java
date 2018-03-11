package com.xinboiedu.erp.invoice.bill.business.ebi;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.base.BaseEbi;
import com.xinboiedu.erp.invoice.bill.vo.BillQueryModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
@Transactional
public interface BillEbi  {

	List<Object[]> getBuyBill(BillQueryModel bqm);

	List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm);

	void writeJFreeChartToOs(ServletOutputStream os, BillQueryModel bqm) throws IOException;

	public InputStream getWriteExcel(BillQueryModel bqm) throws Exception;
}
