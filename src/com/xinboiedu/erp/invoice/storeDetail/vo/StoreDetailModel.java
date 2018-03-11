package com.xinboiedu.erp.invoice.storeDetail.vo;

import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;

public class StoreDetailModel {

	private Long id;
	private Integer num;
	private StoreModel sm;
	private GoodsModel gm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public StoreModel getSm() {
		return sm;
	}
	public void setSm(StoreModel sm) {
		this.sm = sm;
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	
}
