package com.xinboiedu.erp.invoice.goodsType.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel> {

	List<GoodsTypeModel> getBySm(Long id);

	List<GoodsTypeModel> getUnionBySm(Long id);
}
