package com.xinboiedu.erp.invoice.supplier.vo;

import java.util.HashMap;
import java.util.Map;

public class SupplierModel {
	public static final Integer SUPPLIER_NEEDS_IS_YES=1;
	public static final Integer SUPPLIER_NEEDS_IS_NO=0;
	public static final String  SUPPLIER_NEEDS_YES_VIEW="送货";
	public static final String  SUPPLIER_NEEDS_NO_VIEW="自提";
	
	public static final Map<Integer, String>NEEDS_MAP=new HashMap<Integer, String>();
	static{
		NEEDS_MAP.put(SUPPLIER_NEEDS_IS_YES, SUPPLIER_NEEDS_YES_VIEW);
		NEEDS_MAP.put(SUPPLIER_NEEDS_IS_NO, SUPPLIER_NEEDS_NO_VIEW);
	}
	
	
	private Long id;
	private String name,address,contact,tele;
	private Integer needs;
	
	private String needsView;
	public String getNeedsView() {
		return needsView;
	}
	
	public void setNeeds(Integer needs) {
		this.needs = needs;
		needsView=NEEDS_MAP.get(needs);
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public Integer getNeeds() {
		return needs;
	}
	
	
}
