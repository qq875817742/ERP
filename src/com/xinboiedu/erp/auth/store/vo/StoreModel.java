package com.xinboiedu.erp.auth.store.vo;

import com.xinboiedu.erp.auth.emp.vo.EmpModel;

public class StoreModel {
	private Long id;
	private String name;
	private String address;
	private EmpModel em;
	public EmpModel getEm() {
		return em;
	}
	public void setEm(EmpModel em) {
		this.em = em;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
