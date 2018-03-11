package com.xinboiedu.erp.invoice.order.web;

import java.util.List;

import com.xinboiedu.erp.invoice.goods.business.ebi.GoodsEbi;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.goodsType.business.ebi.GoodsTypeEbi;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.order.business.ebi.OrderEbi;
import com.xinboiedu.erp.invoice.order.vo.OrderModel;
import com.xinboiedu.erp.invoice.order.vo.OrderQueryModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.utils.Constants;
import com.xinboiedu.erp.Exception.AppException;
import com.xinboiedu.erp.auth.emp.business.ebi.EmpEbi;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.store.business.ebi.StoreEbi;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.base.BaseAction;

public class OrderAction extends BaseAction {
	//注入供应商
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}
	
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	private OrderEbi orderEbi;
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	public String list() {
		setCount(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return "list";
	}
	public String input() {
		if (om.getId() != null) { 
			om = orderEbi.get(om.getId());
		}
		return "input";
	}
	public String save() {
		if (om.getId() == null) {
			orderEbi.save(om);
			setSavePage(orderEbi.getCount(oqm));
		} else {
			orderEbi.update(om);
		}
		return "toList";
	}
	public String delete() {
		orderEbi.delete(om);
		return "toList";
	}
	//-----------------------------------------------------------------------------
	//====================================================================================
	//=========================采购模块   开始=================================================
	//====================================================================================

	public String buyList(){
		setCount(orderEbi.getCountBuy(oqm));
		List<OrderModel> orderList = orderEbi.getAllBuy(oqm, pageNum, pageCount);
		put("orderList", orderList);
		
		return"buyList";
	}
	public String buyInput(){
		//加载所有的供应商
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		put("supplierList", supplierList);
		
		if(supplierList.size()==0){
		throw	new  AppException("没有相关供应商信息");
		}
		//加载第一个供应商的类别
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getUnionBySm(supplierList.get(0).getId());
		put("goodsTypeList", goodsTypeList);
		//加载第一个类别的商品
		List<GoodsModel> goodsList=goodsEbi.getByGtm(goodsTypeList.get(0).getId());
		//加载第一个商品的进货价
		put("goodsList", goodsList);

		return "buyInput";
	}
	public Long[] goodsIds;
	public Integer[] nums;
	public Double[] prices;
	
	//保存订单
	public String buySave(){
		orderEbi.saveBuyOrder(om,goodsIds,nums,prices,getLoginInfo());		
		return "toBuyList";
	}
	//详情页面
	public String buyDetail(){
		//获取订单数据
		om = orderEbi.get(om.getId());
		return "buyDetail";
	}
//	===================================================================================
//	============================采购订单  审批==============================================
//    ===================================================================================
	public String buyCheckList(){
		setCount(orderEbi.getCountBuyCheck(oqm));
		List<OrderModel> orderList = orderEbi.getAllBuyCheck(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return "buyCheckList";
	}
	public String buyCheckDetail(){
		// 查询订单信息
		om = orderEbi.get(om.getId());
		return "buyCheckDetail";
	}
	// 审核通过
	public String buyCheckPass(){
		orderEbi.buyCheckPass(om.getId(),getLoginInfo());
		return"toBuyCheckList";
	}
	// 审核驳回
	public String buyCheckNoPass(){
		orderEbi.buyCheckNoPass(om.getId(),getLoginInfo());
		return"toBuyCheckList";
	}
//	===================================================================================
//	============================采购订单  结束==============================================
//    ===================================================================================
	
//================================================================================
//================================商品运输 开始===========================================
//===================================================================================	
	//运输管理列表
	public String taskList(){
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		put("supplierList", supplierList);
		
		setCount(orderEbi.getCountTask(oqm));
		List<OrderModel> orderList = orderEbi.getAllTask(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return "taskList";
	}
	//任务指派
	public String toAssignTask(){
		//获取配跟单人信息
		//根据角色（根据部门获取）
	List<EmpModel> empList  =	empEbi.getByRm(Constants.ROLE_TRANSPORT);
	put("empList", empList);
		//查询订单信息
		om=orderEbi.get(om.getId());
		return "toAssignTask";
	}
	//指派跟单员
	public String assignSave(){		
		orderEbi.assignCompleter(om);
		return "toTasksList";
	}
	
	//当前用户运输任务查询
	public String tasks(){
		//当前登录人只能看你到自己的跟单任务
		setCount(orderEbi.getCountTask(oqm,getLoginInfo()));
		List<OrderModel> orderList = orderEbi.getAllTask(oqm, pageNum, pageCount,getLoginInfo());
		put("orderList", orderList);
		return"tasks";
	}
	//任务详情
	public String taskDetail(){
		om=orderEbi.get(om.getId());
		return "taskDetail";
	}
	//运输任务完成
	public String taskCompleted(){
		orderEbi.taskCompleted(om);
		return"toTasks";
	}
	//================================================================================
	//================================商品运输 结束===========================================
	//===================================================================================	
	
//	===================================================================================
//	============================仓库管理 开始==============================================
//  ===================================================================================
	
	public String inStoreList(){
		//获取所有需要入库的订单 （采购:入库  销售退货:入库）
		setCount(orderEbi.getCountInStore(oqm,getLoginInfo()));
		List<OrderModel> orderList = orderEbi.getAllInStore(oqm, pageNum, pageCount);
		put("orderList", orderList);
		return "inStoreList";
	}
	 //入库详情页面
	public String inStoreDetail(){
		 //获取所有仓库信息
		List<StoreModel> storeList = storeEbi.getAll();
		put("storeList",storeList);
		  //获取订单数据
		om=orderEbi.get(om.getId());
		return "inStoreDetail";
	}
	
//	public String stock(){
//		 //获取所有仓库信息
//		List<StoreModel> storeList = storeEbi.getAll();
//		put("storeList",storeList);
//		
//		return "stock";
//	}
//	public String oper(){
//		 //获取所有仓库信息
//		List<StoreModel> storeList = storeEbi.getAll();
//		put("storeList",storeList);
//		return"oper";
//	}
	
//	===================================================================================
//	============================仓库管理   结束==============================================
//  ===================================================================================
	
	
	
	
	
//====================================================================================
//==============================采购模块  结束============================================
//====================================================================================
	
	//============================AJAX==========================
	public Long supplierId;	
	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private	GoodsModel gm;
	
	
	public GoodsModel getGm() {
		return gm;
	}
	public List<GoodsModel> getGmList() {
		return gmList;
	}
	
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	
	public String ajaxGetGtmAndGm(){
		//获取商品类别
		gtmList = goodsTypeEbi.getUnionBySm(supplierId);
		//获取第一个商品类别的商品
		gmList = goodsEbi.getByGtm(gtmList.get(0).getId());
		//获取第一个商品
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	public Long goodsTypeId;
	public String ajaxGetGm(){
		//获取商品列表
		gmList = goodsEbi.getByGtm(goodsTypeId);
		//判断当前商品列表数据是否在前台中已经使用，如果已经使用就删除掉不要传递到前台去
	    for (int i = gmList.size() - 1; i >= 0; i--) {
	    	Long id = gmList.get(i).getId();
	    	if(used.contains("'" +id + "'")){
	    		gmList.remove(i);
	    	}
		}
		 gm = gmList.get(0);
		return "ajaxGetGm";
	}
	
	public Long goodsId;
	public String ajaxGetPrice(){
		gm = goodsEbi.get(goodsId);
		return "ajaxGetPrice";
	}
	public String used;
	public String ajaxGetGtmAndGm2(){
		//获取商品类别
		gtmList = goodsTypeEbi.getUnionBySm(supplierId);
		GoodsType:for (int i = gtmList.size()-1; i >=0; i--) {
			List<GoodsModel> tempList = goodsEbi.getByGtm(gtmList.get(i).getId());
			for (GoodsModel goodsModel : tempList) {
				if(!used.contains("'"+goodsModel.getId()+"'")){
					continue GoodsType;
				}
			}
			gtmList.remove(i);
			}
		
		//获取第一个商品类别的商品
		gmList = goodsEbi.getByGtm(gtmList.get(0).getId());
		for (int i = gmList.size()-1; i >=0; i--) {
			Long id = gmList.get(i).getId();
			if(used.contains("'"+id+"'")){
				gmList.remove(i);
			}
		}		
		//获取第一个商品
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	public Long odmId;
	public Long storeId;
	public Integer num;
	OrderDetailModel odm;
	public OrderDetailModel getOdm() {
		return odm;
	}
	public String ajaxInStore (){
		odm=orderEbi.inStore(odmId,storeId,num,getLoginInfo());
		return "ajaxInStore";
	}
}
