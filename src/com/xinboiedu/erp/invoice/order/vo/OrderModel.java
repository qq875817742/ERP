package com.xinboiedu.erp.invoice.order.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class OrderModel {
	public static final Integer ORDER_ORDERTYPE_OF_BUY=1;
	public static final Integer ORDER_ORDERTYPE_OF_SALE=2;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_BUY=3;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_SALE=4;
	
	public static final String ORDER_ORDERTYPE_OF_BUY_VIEW="采购";
	public static final String ORDER_ORDERTYPE_OF_SALE_VIEW="销售";
	public static final String ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW="采购退货";
	public static final String  ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW="销售退货";
	
	public static final Map<Integer,String> ORDERTYPE_MAP=new HashMap<Integer,String>();
	
	//订单状态(采购)
		// 未审核
		// 通过
		// 驳回
		// 采购中
		// 入库中
		// 结单
	public static final Integer ORDER_STATUS_OF_BUY_NOCHECK=111;
	public static final Integer ORDER_STATUS_OF_BUY_PASS=121;
	public static final Integer ORDER_STATUS_OF_BUY_NOPASS=120;
	public static final Integer ORDER_STATUS_OF_BUY_BUYING=131;
	public static final Integer ORDER_STATUS_OF_BUY_INSTORE=141;
	public static final Integer ORDER_STATUS_OF_BUY_COMPLETE=199;
	
	public static final String ORDER_STATUS_OF_BUY_NOCHECK_VIEW="未审核";
	public static final String ORDER_STATUS_OF_BUY_PASS_VIEW="通过";
	public static final String ORDER_STATUS_OF_BUY_NOPASS_VIEW="驳回";
	public static final String ORDER_STATUS_OF_BUY_BUYING_VIEW="采购中";
	public static final String ORDER_STATUS_OF_BUY_INSTORE_VIEW="入库中";
	public static final String ORDER_STATUS_OF_BUY_COMPLETE_VIEW="结单";
	
	
	//销售
	public static final Integer ORDER_STATUS_OF_SALE_NOCHECK=211;
	public static final Integer ORDER_STATUS_OF_SALE_PASS=221;
	
	public static final String ORDER_STATUS_OF_SALE_NOCHECK_VIEW="未审核";
	public static final String ORDER_STATUS_OF_SALE_PASS_VIEW="通过";
	//状态的map容器
	
	public static final Map<Integer,String > STATUS_MAP=new HashMap<Integer, String>();
	//采购
	public static final Map<Integer,String > STATUS_OF_BUY_MAP=new HashMap<Integer, String>();
	//销售
	public static final Map<Integer,String > STATUS_OF_SALE_MAP=new HashMap<Integer, String>();
	
	
	static{
		ORDERTYPE_MAP.put(ORDER_ORDERTYPE_OF_BUY, ORDER_ORDERTYPE_OF_BUY_VIEW);
		ORDERTYPE_MAP.put(ORDER_ORDERTYPE_OF_SALE, ORDER_ORDERTYPE_OF_SALE_VIEW);
		ORDERTYPE_MAP.put(ORDER_ORDERTYPE_OF_RETURN_BUY, ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW);
		ORDERTYPE_MAP.put(ORDER_ORDERTYPE_OF_RETURN_SALE, ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW);
		
		STATUS_OF_BUY_MAP.put(ORDER_STATUS_OF_BUY_NOCHECK, ORDER_STATUS_OF_BUY_NOCHECK_VIEW);
		STATUS_OF_BUY_MAP.put(ORDER_STATUS_OF_BUY_PASS, ORDER_STATUS_OF_BUY_PASS_VIEW);
		STATUS_OF_BUY_MAP.put(ORDER_STATUS_OF_BUY_NOPASS, ORDER_STATUS_OF_BUY_NOPASS_VIEW);
		STATUS_OF_BUY_MAP.put(ORDER_STATUS_OF_BUY_BUYING, ORDER_STATUS_OF_BUY_BUYING_VIEW);
		STATUS_OF_BUY_MAP.put(ORDER_STATUS_OF_BUY_INSTORE, ORDER_STATUS_OF_BUY_INSTORE_VIEW);
		STATUS_OF_BUY_MAP.put(ORDER_STATUS_OF_BUY_COMPLETE, ORDER_STATUS_OF_BUY_COMPLETE_VIEW);
		
		
		STATUS_OF_SALE_MAP.put(ORDER_STATUS_OF_SALE_NOCHECK, ORDER_STATUS_OF_SALE_NOCHECK_VIEW);
		STATUS_OF_SALE_MAP.put(ORDER_STATUS_OF_SALE_PASS, ORDER_STATUS_OF_SALE_PASS_VIEW);
		
		STATUS_MAP.putAll(STATUS_OF_BUY_MAP);
		STATUS_MAP.putAll(STATUS_OF_SALE_MAP);
	}
	

private Long id;
private String orderNum;//订单号
private Integer totalNum;//订单商品总量
private EmpModel creater;//制单人
private EmpModel checker;//跟单员
private EmpModel completer;//结单人
private SupplierModel sm;//供应商



private Long createTime;//制单时间
private Long checkedTime;//检查时间
private Long endTime;//结束时间
private Integer orderType;//订单类型
private Integer status;//状态
private Double totalPrice;//订单总金额

private String createTimeView;
private String checkedTimeView;
private String endTimeView;
private String orderTypeView;
private String statusView;
private String totalPriceView;

//对订单详情的一对多
private Set<OrderDetailModel> odms;

public Set<OrderDetailModel> getOdms() {
	return odms;
}

public void setOdms(Set<OrderDetailModel> odms) {
	this.odms = odms;
}

public String getOrderNum() {
	return orderNum;
}

public void setOrderNum(String orderNum) {
	this.orderNum = orderNum;
}

public EmpModel getCreater() {
	return creater;
}

public void setCreater(EmpModel creater) {
	this.creater = creater;
}

public EmpModel getChecker() {
	return checker;
}

public void setChecker(EmpModel checker) {
	this.checker = checker;
}

public EmpModel getCompleter() {
	return completer;
}

public void setCompleter(EmpModel completer) {
	this.completer = completer;
}

public SupplierModel getSm() {
	return sm;
}

public void setSm(SupplierModel sm) {
	this.sm = sm;
}

public Long getCreateTime() {
	return createTime;
}

public void setCreateTime(Long createTime) {
	this.createTime = createTime;
	createTimeView=FormatterUtil.FormaterDate(createTime);
}

public Long getCheckedTime() {
	return checkedTime;
}

public void setCheckedTime(Long checkedTime) {
	this.checkedTime = checkedTime;
	checkedTimeView=FormatterUtil.FormaterDate(checkedTime);
}

public Long getEndTime() {
	return endTime;
}

public void setEndTime(Long endTime) {
	this.endTime = endTime;
	endTimeView=FormatterUtil.FormaterDate(endTime);
}

public Integer getOrderType() {
	return orderType;
}

public void setOrderType(Integer orderType) {
	this.orderType = orderType;
	orderTypeView=ORDERTYPE_MAP.get(orderType);
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
	statusView=STATUS_MAP.get(status);
}

public Double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
	totalPriceView=FormatterUtil.FormaterMoney(totalPrice);
}

public Integer getTotalNum() {
	return totalNum;
}

public void setTotalNum(Integer totalNum) {
	this.totalNum = totalNum;
}





public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
public String getCreateTimeView() {
	return createTimeView;
}

public String getCheckedTimeView() {
	return checkedTimeView;
}

public String getEndTimeView() {
	return endTimeView;
}

public String getOrderTypeView() {
	return orderTypeView;
}

public String getStatusView() {
	return statusView;
}

public String getTotalPriceView() {
	return totalPriceView;
}

}
