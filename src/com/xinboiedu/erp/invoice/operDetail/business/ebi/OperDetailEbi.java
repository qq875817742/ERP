package com.xinboiedu.erp.invoice.operDetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface OperDetailEbi extends BaseEbi<OperDetailModel> {
}
