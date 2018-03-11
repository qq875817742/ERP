package com.xinboiedu.erp.auth.store.web;

import java.util.List;

import com.xinboiedu.erp.auth.emp.business.ebi.EmpEbi;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.store.business.ebi.StoreEbi;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.auth.store.vo.StoreQueryModel;
import com.xinboiedu.erp.base.BaseAction;
import com.xinboiedu.erp.utils.Constants;

public class StoreAction extends BaseAction {
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}
	public StoreModel sm = new StoreModel();
	public StoreQueryModel sqm = new StoreQueryModel();

	public String list() {
		setCount(storeEbi.getCount(sqm));
		List<StoreModel> storeList = storeEbi.getAll(sqm, pageNum, pageCount);
		put("storeList", storeList);
		return "list";
	}
	public String input() {
		
		List<EmpModel> empList = empEbi.getByRm(Constants.ROLE_STOREMNG);
		put("empList", empList);
		if (sm.getId() != null) { 
			sm = storeEbi.get(sm.getId());
		}
		return "input";
	}
	public String save() {
		if (sm.getId() == null) {
			storeEbi.save(sm);
			setSavePage(storeEbi.getCount(sqm));
		} else {
			storeEbi.update(sm);
		}
		return "toList";
	}
	public String delete() {
		storeEbi.delete(sm);
		return "toList";
	}
}
