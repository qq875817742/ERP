package com.xinboiedu.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.operDetail.dao.dao.OperDetailDao;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailModel;
import com.xinboiedu.erp.invoice.order.business.ebi.OrderEbi;
import com.xinboiedu.erp.invoice.order.dao.dao.OrderDao;
import com.xinboiedu.erp.invoice.order.vo.OrderModel;
import com.xinboiedu.erp.invoice.order.vo.OrderQueryModel;
import com.xinboiedu.erp.invoice.orderDetail.dao.dao.OrderDetailDao;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.invoice.storeDetail.dao.dao.StoreDetailDao;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailModel;
import com.xinboiedu.erp.utils.OrderNumUtil;
import com.xinboiedu.erp.Exception.AppException;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class OrderEbo implements OrderEbi{
	
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private OperDetailDao operDetailDao;
	private StoreDetailDao storeDetailDao;
	
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	@Override
	public void save(OrderModel om) {
		orderDao.save(om);
	}
	@Override
	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}
	@Override
	public OrderModel get(Serializable id) {
		return orderDao.get(id);
	}
	@Override
	public void update(OrderModel om) {
		orderDao.update(om);
	}
	@Override
	public void delete(OrderModel om) {
		orderDao.delete(om);
	}
	@Override
	public List<OrderModel> getAll(BaseQueryModel dqm) {
		return orderDao.getAll(dqm);
	}
	@Override
	public List<OrderModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return orderDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return orderDao.getCount(dqm);
	}
	@Override
	public void saveBuyOrder(OrderModel om, Long[] goodsIds, Integer[] nums, Double[] prices, EmpModel loginInfo) {
		
		String orderNum=OrderNumUtil.generatorOrderNum();
		om.setOrderNum(orderNum);
		om.setCreateTime(System.currentTimeMillis());
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		om.setStatus(OrderModel.ORDER_STATUS_OF_BUY_NOCHECK);
		om.setCreater(loginInfo);
		
		Integer totalNum=0;
		Double totalPrice=0.0;
//		odms订单详情
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for (int i = 0; i < prices.length; i++) {
			//订单详情
			OrderDetailModel odm = new OrderDetailModel();
			odm.setNum(nums[i]);
			//设置剩余入库数量
			odm.setSurplus(nums[i]);
			odm.setPrice(prices[i]);
			GoodsModel gm = new GoodsModel();
			gm.setId(goodsIds[i]);
			odm.setGm(gm);
			odm.setOm(om);
			odms.add(odm);// 保存到容器中
			
			totalNum+=nums[i];
			totalPrice+=prices[i];
		}
		om.setOdms(odms);//订单管理订单详情（级联保存）
		om.setTotalNum(totalNum);
		om.setTotalPrice(totalPrice);
		orderDao.save(om);

	}
	@Override
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		//增加条件  订单类型为 采购
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	Integer[] buyCheckOrderTypes=new Integer[]{
			OrderModel.ORDER_ORDERTYPE_OF_BUY,
			OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY	
	};
	@Override
	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		// 条件中有多个条件值
				// and (orderType = ? || orderType = ? )
				// and orderType in(?,?)
		oqm.setOrderTypes(buyCheckOrderTypes);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	@Override
	public Integer getCountBuyCheck(OrderQueryModel oqm) {
		oqm.setOrderTypes(buyCheckOrderTypes);
		return orderDao.getCount(oqm);
	}
	@Override
	public Integer getCountBuy(OrderQueryModel oqm) {
		// 增加条件: 订单类型为 采购
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getCount(oqm);
	}
	@Override
	public void buyCheckPass(Long id, EmpModel loginInfo) {
		//所谓的审核通过就是改变订单状态
		OrderModel temp = orderDao.get(id);
		// 逻辑校验
		// 如果状态不是未审核就不能再往下做了
		if(!OrderModel.ORDER_STATUS_OF_BUY_NOCHECK.equals(temp.getStatus())){
			throw new AppException("请不要调皮");
		}
		
		//状态为通过
		temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_PASS);
		//审核时间
		temp.setCheckedTime(System.currentTimeMillis());
		//审核人
		temp.setChecker(loginInfo);
	}
	public void buyCheckNoPass(Long id, EmpModel loginInfo) {
		//所谓的审核通过就是改变订单状态
		OrderModel temp = orderDao.get(id);
		// 逻辑校验
		// 如果状态不是未审核就不能再往下做了
		if(!OrderModel.ORDER_STATUS_OF_BUY_NOCHECK.equals(temp.getStatus())){
			throw new AppException("请不要调皮");
}
		//状态为通过
		temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_NOPASS);
		//审核时间
		temp.setCheckedTime(System.currentTimeMillis());
		//审核人
		temp.setChecker(loginInfo);
	}
	
	Integer[]taskStatus=new Integer[]{
			OrderModel.ORDER_STATUS_OF_BUY_PASS,
			OrderModel.ORDER_STATUS_OF_BUY_BUYING,
			OrderModel.ORDER_STATUS_OF_BUY_INSTORE,
			OrderModel.ORDER_STATUS_OF_BUY_COMPLETE
	};
	
	@Override
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		oqm.setStatuses(taskStatus);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	@Override
	public Integer getCountTask(OrderQueryModel oqm) {
		oqm.setStatuses(taskStatus);
		return orderDao.getCount(oqm);
	}
	@Override
	public void assignCompleter(OrderModel om) {
		OrderModel temp = orderDao.get(om.getId());
		if(!OrderModel.ORDER_STATUS_OF_BUY_PASS.equals(temp.getStatus())){
			throw new AppException("别捣乱");
		}
		temp.setCompleter(om.getCompleter());
		//设置状态（采购中）
		temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_BUYING);
	}
	@Override
	public Integer getCountTask(OrderQueryModel oqm, EmpModel loginInfo) {
		oqm.setCompleter(loginInfo);
		return orderDao.getCount(oqm);
	}
	@Override
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount, EmpModel loginInfo) {
		oqm.setCompleter(loginInfo);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	@Override
	public void taskCompleted(OrderModel om) {
		OrderModel temp = orderDao.get(om.getId());
		if(!OrderModel.ORDER_STATUS_OF_BUY_BUYING.equals(temp.getStatus())){
			throw new AppException("别捣乱");
		}
		//设置状态（入库中）
		temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_INSTORE);
	}
	//入库状态的订单数据
	Integer[] inStoreStatuses=new Integer[]{
		OrderModel.ORDER_STATUS_OF_BUY_INSTORE	
	};
	
	@Override
	public Integer getCountInStore(OrderQueryModel oqm, EmpModel loginInfo) {
		oqm.setStatuses(inStoreStatuses);
		return orderDao.getCount(oqm);
	}
	@Override
	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		oqm.setStatuses(inStoreStatuses);
		
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	@Override
	public OrderDetailModel inStore(Long odmId, Long storeId, Integer num, EmpModel loginInfo) {
		// 入库
				// 1.订单明细中的剩余数量发生改变(快照)
		OrderDetailModel odm = orderDetailDao.get(odmId);
		OrderModel om = odm.getOm();//订单
		//逻辑校验
		if(!OrderModel.ORDER_STATUS_OF_BUY_INSTORE.equals(om.getStatus())){
			throw new AppException("非法操作");
		}
		//如果入库数量大于剩余数量说明操作有误
		if(num>odm.getSurplus()){
			throw new AppException("非法数据异常");
		}
		//更新剩余数量
		odm.setSurplus(odm.getSurplus()-num);
		//---------------------------------------
			//获取商品数据
			GoodsModel gm = odm.getGm();
			//获取仓库信息
			StoreModel sm = new StoreModel();
			sm.setId(storeId);
			// 2. 库存数量发生改变
			//区查询指定仓库中有没有指定商品
			StoreDetailModel sdm = storeDetailDao.getBySmAndGm(gm,sm);
			if(sdm != null){ 
				//该商品在该仓库中已经存在只要修改数量即可
				sdm.setNum(sdm.getNum() + num);
			}else{ 
				// 该商品在该仓库中不存在，新增仓库明细
				sdm = new StoreDetailModel();
				sdm.setNum(num);
				sdm.setGm(gm);
				sdm.setSm(sm);
				storeDetailDao.save(sdm);//保存仓库明细
			}
			//---------------------------------------
			// 3.数据可追踪，操作明细要保存
			OperDetailModel opdm = new OperDetailModel();
			opdm.setGm(gm);
			opdm.setNum(num);
			opdm.setEm(loginInfo);
			opdm.setOperTime(System.currentTimeMillis());
			opdm.setSm(sm);
			opdm.setType(OperDetailModel.OPER_TYPE_OF_IN);
			operDetailDao.save(opdm);
			// 4.订单中所有商品入库完成 (改变状态)
			//获取订单中的所有的商品剩余数量总和
			Set<OrderDetailModel> odms = om.getOdms();
			int sum = 0;
			for (OrderDetailModel temp : odms) {
				sum += temp.getSurplus();
			}
			if(sum == 0)
			{//全部入库完成
				om.setStatus(OrderModel.ORDER_STATUS_OF_BUY_COMPLETE);
				om.setEndTime(System.currentTimeMillis());
			}
			return odm;
	}
}
