package com.xinboiedu.erp.invoice.storeDetail.web;

import java.util.List;
import com.xinboiedu.erp.invoice.storeDetail.business.ebi.StoreDetailEbi;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailModel;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class StoreDetailAction extends BaseAction {

	private StoreDetailEbi storeDetailEbi;
	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}
	public StoreDetailModel sm = new StoreDetailModel();
	public StoreDetailQueryModel sqm = new StoreDetailQueryModel();

	public String list() {
		setCount(storeDetailEbi.getCount(sqm));
		List<StoreDetailModel> storeDetailList = storeDetailEbi.getAll(sqm, pageNum, pageCount);
		put("storeDetailList", storeDetailList);
		return "list";
	}
	public String input() {
		if (sm.getId() != null) { 
			sm = storeDetailEbi.get(sm.getId());
		}
		return "input";
	}
	public String save() {
		if (sm.getId() == null) {
			storeDetailEbi.save(sm);
			setSavePage(storeDetailEbi.getCount(sqm));
		} else {
			storeDetailEbi.update(sm);
		}
		return "toList";
	}
	public String delete() {
		storeDetailEbi.delete(sm);
		return "toList";
	}
}
