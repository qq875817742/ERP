package com.xinboiedu.erp.invoice.orderDetail.vo;

import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.order.vo.OrderModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class OrderDetailModel {
	private Long id;
	private Integer num;
	private Double price;	
	private String priceView;
	private Integer surplus;//剩余量
	
	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	//视图值
	private String totalView;
	public String getTotalView() {
		return totalView;
	}
	
	public String getPriceView() {
		return priceView;
	}
	
	private GoodsModel gm;
	private OrderModel om;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		priceView=FormatterUtil.FormaterMoney(price);
		totalView=FormatterUtil.FormaterMoney(num*price);
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public OrderModel getOm() {
		return om;
	}
	public void setOm(OrderModel om) {
		this.om = om;
	}
	
}
