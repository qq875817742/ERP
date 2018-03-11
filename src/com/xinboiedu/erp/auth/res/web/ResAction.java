package com.xinboiedu.erp.auth.res.web;

import java.util.List;
import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.auth.res.vo.ResQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class ResAction extends BaseAction {

	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	public String list() {
		setCount(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm, pageNum, pageCount);
		put("resList", resList);
		return "list";
	}
	public String input() {
		if (rm.getId() != null) { 
			rm = resEbi.get(rm.getId());
		}
		return "input";
	}
	public String save() {
		if (rm.getId() == null) {
			resEbi.save(rm);
			setSavePage(resEbi.getCount(rqm));
		} else {
			resEbi.update(rm);
		}
		return "toList";
	}
	public String delete() {
		resEbi.delete(rm);
		return "toList";
	}
}
