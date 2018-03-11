package com.xinboiedu.erp.invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.goods.business.ebi.GoodsEbi;
import com.xinboiedu.erp.invoice.goods.dao.dao.GoodsDao;
import com.xinboiedu.erp.invoice.goods.vo.GoodsModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class GoodsEbo implements GoodsEbi{

	private GoodsDao goodsDao;
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	@Override
	public void save(GoodsModel gm) {
		goodsDao.save(gm);
	}
	@Override
	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}
	@Override
	public GoodsModel get(Serializable id) {
		return goodsDao.get(id);
	}
	@Override
	public void update(GoodsModel gm) {
		goodsDao.update(gm);
	}
	@Override
	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}
	@Override
	public List<GoodsModel> getAll(BaseQueryModel dqm) {
		return goodsDao.getAll(dqm);
	}
	@Override
	public List<GoodsModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return goodsDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return goodsDao.getCount(dqm);
	}
	@Override
	public List<GoodsModel> getByGtm(Long id) {
		
		return goodsDao.getByGtm(id);
	}
}
