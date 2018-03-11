package com.xinboiedu.erp.invoice.goods.vo;

import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class GoodsModel {
private Long id;
private String name,origin,producer,unit;
private Double inPrice,outPrice;
//private SupplierModel sm;
private GoodsTypeModel gtm;

private String inPriceView;
public String getInPriceView() {
	return inPriceView;
}
private String outPriceView;
public String getOutPriceView() {
	return outPriceView;
}

public GoodsTypeModel getGtm() {
	return gtm;
}
public void setGtm(GoodsTypeModel gtm) {
	this.gtm = gtm;
}
//public SupplierModel getSm() {
//	return sm;
//}
//public void setSm(SupplierModel sm) {
//	this.sm = sm;
//}
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
public String getOrigin() {
	return origin;
}
public void setOrigin(String origin) {
	this.origin = origin;
}
public String getProducer() {
	return producer;
}
public void setProducer(String producer) {
	this.producer = producer;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public Double getInPrice() {
	return inPrice;
}
public void setInPrice(Double inPrice) {
	this.inPrice = inPrice;
	inPriceView=FormatterUtil.FormaterMoney(inPrice);
}
public Double getOutPrice() {
	return outPrice;
}
public void setOutPrice(Double outPrice) {
	this.outPrice = outPrice;
	outPriceView=FormatterUtil.FormaterMoney(outPrice);
}

}
