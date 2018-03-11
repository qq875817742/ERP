package com.xinboiedu.erp.invoice.goodsType.web;

import java.util.List;
import com.xinboiedu.erp.invoice.goodsType.business.ebi.GoodsTypeEbi;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeQueryModel;
import com.xinboiedu.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.base.BaseAction;

public class GoodsTypeAction extends BaseAction {
	//注入商品类别
	private GoodsTypeEbi goodsTypeEbi;
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	//注入供应商
	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	
	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	public Long[]supplierIds;
	
	public String list() {
		setCount(goodsTypeEbi.getCount(gqm));
		//获取所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		//获取所有商品类别信息
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm, pageNum, pageCount);
		put("goodsTypeList", goodsTypeList);
		return "list";
	}
	public String input() {
		//查询所有供应商信息
				List<SupplierModel> supplierList = supplierEbi.getAll();
				put("supplierList", supplierList);
		if (gm.getId() != null) { 
			gm = goodsTypeEbi.get(gm.getId());
		}
		return "input";
	}
	public String save() {
		if (gm.getId() == null) {
			goodsTypeEbi.save(gm);
			setSavePage(goodsTypeEbi.getCount(gqm));
		} else {
			goodsTypeEbi.update(gm);
		}
		return "toList";
	}
	public String delete() {
		goodsTypeEbi.delete(gm);
		return "toList";
	}
	
	//===================AJAX===================\\
	
	private List<GoodsTypeModel> gtmList;
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	public String ajaxGetBySm(){
		//根据供应商id获取对应商品类别信息
		gtmList = goodsTypeEbi.getBySm(gm.getSm().getId());
		return "ajaxGetBySm";
	}
	
	
	
}
