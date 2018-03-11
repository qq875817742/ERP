package com.xinboiedu.erp.invoice.goodsType.vo;

import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;

public class GoodsTypeModel {
private Long id;
private String name;
private SupplierModel sm;


public SupplierModel getSm() {
	return sm;
}
public void setSm(SupplierModel sm) {
	this.sm = sm;
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
