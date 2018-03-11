package com.xinboiedu.erp.auth.dep.web;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xinboiedu.erp.auth.dep.business.ebi.DepEbi;
import com.xinboiedu.erp.auth.dep.dao.dao.DepDao;
import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.dep.vo.DepQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class DepAction extends BaseAction{

	private DepEbi depEbi;

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	//接收参数
	public DepModel dm=new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	
	
	//跳转到列表页面+条件查询
	public String list(){
		setCount(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		ActionContext.getContext().getContextMap().put("depList", depList);
		return"list";
	}
	
	//跳转到添加页面
	public String input(){
		if(dm.getId()!=null){
		dm=depEbi.get(dm.getId());
		}
		return "input";
	}
	
	//添加部门
	public String save(){
		if(dm.getId()==null){
		depEbi.save(dm);
		setSavePage(depEbi.getCount(dqm));
		}else{
		depEbi.update(dm);
		}
		return"toList";
	}
	//删除
	public String delete(){
		depEbi.delete(dm);
		return"toList";
	}
	
	
	
}
