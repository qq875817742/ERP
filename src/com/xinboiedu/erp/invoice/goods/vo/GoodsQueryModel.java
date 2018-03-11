package com.xinboiedu.erp.invoice.goods.vo;

import com.xinboiedu.erp.base.BaseQueryModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class GoodsQueryModel extends GoodsModel implements BaseQueryModel{
	
	private Double inPrice2;
	private Double outPrice2;
	private String inPrice2View;
	private String outPrice2View;
	
	public String getInPrice2View() {
		return inPrice2View;
	}
	public String getOutPrice2View() {
		return outPrice2View;
	}
	public Double getInPrice2() {
		return inPrice2;
	}
	public void setInPrice2(Double inPrice2) {
		this.inPrice2 = inPrice2;
		inPrice2View=FormatterUtil.FormaterMoney(inPrice2);
	}
	public Double getOutPrice2() {
		return outPrice2;
	}
	public void setOutPrice2(Double outPrice2) {
		this.outPrice2 = outPrice2;
		outPrice2View=FormatterUtil.FormaterMoney(outPrice2);
	}
	
}
