package com.xinboiedu.erp.invoice.storeDetail.dao.dao;

import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailModel;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel>{

	StoreDetailModel getBySmAndGm(GoodsModel gm, StoreModel sm);
}
