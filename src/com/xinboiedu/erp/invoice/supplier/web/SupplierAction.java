package com.xinboiedu.erp.invoice.supplier.web;

import java.util.List;
import com.xinboiedu.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class SupplierAction extends BaseAction {

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public SupplierModel sm = new SupplierModel();
	public SupplierQueryModel sqm = new SupplierQueryModel();

	public String list() {
		setCount(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm, pageNum, pageCount);
		put("supplierList", supplierList);
		return "list";
	}
	public String input() {
		if (sm.getId() != null) { 
			sm = supplierEbi.get(sm.getId());
		}
		return "input";
	}
	public String save() {
		if (sm.getId() == null) {
			supplierEbi.save(sm);
			setSavePage(supplierEbi.getCount(sqm));
		} else {
			supplierEbi.update(sm);
		}
		return "toList";
	}
	public String delete() {
		supplierEbi.delete(sm);
		return "toList";
	}
}
