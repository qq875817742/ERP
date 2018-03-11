package com.xinboiedu.erp.auth.role.vo;

import java.util.Set;

import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.res.vo.ResModel;

public class RoleModel {
	private Long id;
	private String name;
	private String code;
	
	private Set<ResModel> ress;
	private Set<MenuModel> menues;
	private Set<EmpModel> emps;
	
	
	public Set<EmpModel> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpModel> emps) {
		this.emps = emps;
	}
	public Set<MenuModel> getMenues() {
		return menues;
	}
	public void setMenues(Set<MenuModel> menues) {
		this.menues = menues;
	}
	public Set<ResModel> getRess() {
		return ress;
	}
	public void setRess(Set<ResModel> ress) {
		this.ress = ress;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
