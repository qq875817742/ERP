package com.xinboiedu.erp.auth.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface StoreEbi extends BaseEbi<StoreModel> {
}
