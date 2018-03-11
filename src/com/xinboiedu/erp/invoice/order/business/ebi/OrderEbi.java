package com.xinboiedu.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.order.vo.OrderModel;
import com.xinboiedu.erp.invoice.order.vo.OrderQueryModel;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface OrderEbi extends BaseEbi<OrderModel> {

	void saveBuyOrder(OrderModel om, Long[] goodsIds, Integer[] nums, Double[] prices, EmpModel loginInfo);

	List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	Integer getCountBuyCheck(OrderQueryModel oqm);

	Integer getCountBuy(OrderQueryModel oqm);

	void buyCheckPass(Long id, EmpModel loginInfo);

	void buyCheckNoPass(Long id, EmpModel loginInfo);

	List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	Integer getCountTask(OrderQueryModel oqm);

	void assignCompleter(OrderModel om);

	Integer getCountTask(OrderQueryModel oqm, EmpModel loginInfo);

	List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount, EmpModel loginInfo);

	void taskCompleted(OrderModel om);

	Integer getCountInStore(OrderQueryModel oqm, EmpModel loginInfo);

	List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	OrderDetailModel inStore(Long odmId, Long storeId, Integer num,EmpModel loginInfo);

}
