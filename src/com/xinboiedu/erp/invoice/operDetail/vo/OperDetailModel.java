package com.xinboiedu.erp.invoice.operDetail.vo;

import java.util.HashMap;
import java.util.Map;

import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class OperDetailModel {
	public static final Integer OPER_TYPE_OF_IN=1;
	public static final Integer OPER_TYPE_OF_OUT=0;
	
	public static final String OPER_TYPE_OF_IN_VIEW="入库";
	public static final String OPER_TYPE_OF_OUT_VIEW="出库";
	
	public static final Map<Integer,String>OPERTYPE_MAP=new HashMap<Integer,String>();
	static{
		OPERTYPE_MAP.put(OPER_TYPE_OF_IN, OPER_TYPE_OF_IN_VIEW);
		OPERTYPE_MAP.put(OPER_TYPE_OF_OUT, OPER_TYPE_OF_OUT_VIEW);
	}
	
	
	
	private Long id;
	private Long operTime;
	private Integer num;
	private Integer type;
	private EmpModel em;
	
	private String typeView;
	public String getTypeView() {
		return typeView;
	}
	
	private String operTimeView;
	public String getOperTimeView() {
		return operTimeView;
	}
	
	private GoodsModel gm;
	private StoreModel sm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOperTime() {
		return operTime;
	}
	public void setOperTime(Long operTime) {
		this.operTime = operTime;
		operTimeView=FormatterUtil.FormaterDate(operTime);
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		typeView=OPERTYPE_MAP.get(type);
	}
	public EmpModel getEm() {
		return em;
	}
	public void setEm(EmpModel em) {
		this.em = em;
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public StoreModel getSm() {
		return sm;
	}
	public void setSm(StoreModel sm) {
		this.sm = sm;
	}
	
}
