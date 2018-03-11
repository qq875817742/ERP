package com.xinboiedu.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel> {

	List<GoodsModel> getByGtm(Long id);
}
