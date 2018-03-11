package com.xinboiedu.erp.invoice.goodsType.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.goodsType.business.ebi.GoodsTypeEbi;
import com.xinboiedu.erp.invoice.goodsType.dao.dao.GoodsTypeDao;
import com.xinboiedu.erp.invoice.goodsType.vo.GoodsTypeModel;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class GoodsTypeEbo implements GoodsTypeEbi{

	private GoodsTypeDao goodsTypeDao;
	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}
	@Override
	public void save(GoodsTypeModel gm) {
		goodsTypeDao.save(gm);
	}
	@Override
	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}
	@Override
	public GoodsTypeModel get(Serializable id) {
		return goodsTypeDao.get(id);
	}
	@Override
	public void update(GoodsTypeModel gm) {
		goodsTypeDao.update(gm);
	}
	@Override
	public void delete(GoodsTypeModel gm) {
		goodsTypeDao.delete(gm);
	}
	@Override
	public List<GoodsTypeModel> getAll(BaseQueryModel dqm) {
		return goodsTypeDao.getAll(dqm);
	}
	@Override
	public List<GoodsTypeModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return goodsTypeDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return goodsTypeDao.getCount(dqm);
	}
	@Override
	public List<GoodsTypeModel> getBySm(Long id) {
		
		return goodsTypeDao.getBySm(id);
	}
	@Override
	public List<GoodsTypeModel> getUnionBySm(Long id) {
		return goodsTypeDao.getUnionBySm(id);
	}

}
