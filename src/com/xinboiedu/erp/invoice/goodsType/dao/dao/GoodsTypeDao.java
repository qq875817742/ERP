package com.xinboiedu.erp.invoice.goodsType.dao.dao;

import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;

import java.util.List;

import com.xinboiedu.erp.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel>{

	List<GoodsTypeModel> getBySm(Long id);

	List<GoodsTypeModel> getUnionBySm(Long id);
}
