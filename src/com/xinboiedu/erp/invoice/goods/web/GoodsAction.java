package com.xinboiedu.erp.invoice.goods.web;

import java.util.List;
import com.xinboiedu.erp.invoice.goods.business.ebi.GoodsEbi;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.goods.vo.GoodsQueryModel;
import com.xinboiedu.erp.invoice.goodsType.business.ebi.GoodsTypeEbi;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.base.BaseAction;

public class GoodsAction extends BaseAction {
	//注入商品信息
	private GoodsEbi goodsEbi;
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	//注入供应商信息
	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	//注入商品类别信息
	private GoodsTypeEbi goodsTypeEbi;
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	public String list() {
		setCount(goodsEbi.getCount(gqm));
		//获取所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList", supplierList);
		//获取商品信息
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm, pageNum, pageCount);
		put("goodsList", goodsList);
		return "list";
	}
	public String input() {
		//获取具有商品类别的供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		put("supplierList", supplierList);
		Long supplierId=null;
		if (gm.getId() != null) { 
			//修改页面
			gm = goodsEbi.get(gm.getId());
			supplierId=gm.getGtm().getSm().getId();
		}else{
			supplierId=supplierList.get(0).getId();
		}
		//获取所有商品类别
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getBySm(supplierId);
		put("goodsTypeList", goodsTypeList);
		return "input";
	}
	public String save() {
		if (gm.getId() == null) {
			goodsEbi.save(gm);
			setSavePage(goodsEbi.getCount(gqm));
		} else {
			goodsEbi.update(gm);
		}
		return "toList";
	}
	public String delete() {
		goodsEbi.delete(gm);
		return "toList";
	}
}
