package com.xinboiedu.erp.invoice.order.vo;

import com.xinboiedu.erp.base.BaseQueryModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class OrderQueryModel extends OrderModel implements BaseQueryModel{
	
private Integer totalNum2;
private Double totalPrice2;
public Long createTime2;
public Long checkedTime2;
private String createTime2View;
private String checkedTime2View;
private Integer[] orderTypes;
private Integer[] statuses;

public Integer getTotalNum2() {
	return totalNum2;
}

public void setTotalNum2(Integer totalNum2) {
	this.totalNum2 = totalNum2;
}

public Double getTotalPrice2() {
	return totalPrice2;
}

public void setTotalPrice2(Double totalPrice2) {
	this.totalPrice2 = totalPrice2;
}





public Long getCheckedTime2() {
	return checkedTime2;
}

public void setCheckedTime2(Long checkedTime2) {
	this.checkedTime2 = checkedTime2;
	checkedTime2View=FormatterUtil.FormaterDate(checkedTime2);
}

public Long getCreateTime2() {
	return createTime2;
}

public String getCheckedTime2View() {
	return checkedTime2View;
}

public String getCreateTime2View() {
	return createTime2View;
}

public void setCreateTime2(Long createTime2) {
	this.createTime2 = createTime2;
	createTime2View=FormatterUtil.FormaterDate(createTime2);
}



public Integer[] getOrderTypes() {
	return orderTypes;
}

public void setOrderTypes(Integer[] orderTypes) {
	this.orderTypes = orderTypes;
}



public Integer[] getStatuses() {
	return statuses;
}

public void setStatuses(Integer[] statuses) {
	this.statuses = statuses;
}

}
