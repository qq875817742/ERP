package com.xinboiedu.erp.invoice.bill.vo;

import com.xinboiedu.erp.base.BaseQueryModel;

public class BillQueryModel implements BaseQueryModel{
	private Integer type;//查询条件：订单类别
	private Long supplierId;//查询条件：厂商Id
	private Long goodsId;
	
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	
	
}
