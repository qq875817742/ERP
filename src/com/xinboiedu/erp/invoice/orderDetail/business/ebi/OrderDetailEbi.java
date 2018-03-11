package com.xinboiedu.erp.invoice.orderDetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.orderDetail.vo.OrderDetailModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel> {
}
