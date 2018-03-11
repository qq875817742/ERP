package com.xinboiedu.erp.invoice.goods.dao.dao;

import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;

import java.util.List;

import com.xinboiedu.erp.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel>{

	List<GoodsModel> getByGtm(Long id);
}
