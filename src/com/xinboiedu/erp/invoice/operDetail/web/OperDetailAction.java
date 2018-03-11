package com.xinboiedu.erp.invoice.operDetail.web;

import java.util.List;
import com.xinboiedu.erp.invoice.operDetail.business.ebi.OperDetailEbi;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailModel;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class OperDetailAction extends BaseAction {

	private OperDetailEbi operDetailEbi;
	public void setOperDetailEbi(OperDetailEbi operDetailEbi) {
		this.operDetailEbi = operDetailEbi;
	}
	public OperDetailModel om = new OperDetailModel();
	public OperDetailQueryModel oqm = new OperDetailQueryModel();

	public String list() {
		setCount(operDetailEbi.getCount(oqm));
		List<OperDetailModel> operDetailList = operDetailEbi.getAll(oqm, pageNum, pageCount);
		put("operDetailList", operDetailList);
		return "list";
	}
	public String input() {
		if (om.getId() != null) { 
			om = operDetailEbi.get(om.getId());
		}
		return "input";
	}
	public String save() {
		if (om.getId() == null) {
			operDetailEbi.save(om);
			setSavePage(operDetailEbi.getCount(oqm));
		} else {
			operDetailEbi.update(om);
		}
		return "toList";
	}
	public String delete() {
		operDetailEbi.delete(om);
		return "toList";
	}
}
