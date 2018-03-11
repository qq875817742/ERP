package com.xinboiedu.erp.invoice.bill.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.xinboiedu.erp.base.BaseAction;
import com.xinboiedu.erp.invoice.bill.business.ebi.BillEbi;
import com.xinboiedu.erp.invoice.bill.vo.BillQueryModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;

public class BillAction extends BaseAction {

	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}
	public BillQueryModel bqm = new BillQueryModel();
	
	
	public String buyBillList(){
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		
		List<Object[]>buyBillList = billEbi.getBuyBill(bqm);
		put("buyBillList", buyBillList);
//	for (int i = 0; i < buyBillList.size(); i++) {
//		Object[] object=buyBillList.get(i);
//		for (Object obj : object) {
//			
//		}
//	}
		return "buyBillList";
	}
	
	public void pieBill() throws IOException{
		
		HttpServletResponse response = getrResponse();
		ServletOutputStream os = response.getOutputStream();
		billEbi.writeJFreeChartToOs(os,bqm);
	}
	
	private InputStream downloadExcelStream;
	public InputStream getDownloadExcelStream() {
		return downloadExcelStream;
	}
	private String xlsName;
	public String getXlsName() throws UnsupportedEncodingException {
		return new String(xlsName.getBytes("utf-8"),"iso8859-1" );
	}
	
	public String downloadExcel() throws Exception{
		xlsName="采购报表.xls";
		downloadExcelStream=billEbi.getWriteExcel(bqm);
		return"downloadExcel";
	}
	
	private List<OrderDetailModel>odmList;
	
	
	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}
	
	
	
	//=========================AJAX=====================
	public String ajaxGetBillDetail(){
		   odmList= billEbi.getBuyBillDetail(bqm);
		return"ajaxGetBillDetail";
	}
	
	
}
