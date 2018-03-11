package com.xinboiedu.erp.invoice.storeDetail.business.ebo;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.storeDetail.business.ebi.StoreDetailEbi;
import com.xinboiedu.erp.invoice.storeDetail.dao.dao.StoreDetailDao;
import com.xinboiedu.erp.invoice.storeDetail.vo.StoreDetailModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class StoreDetailEbo implements StoreDetailEbi{

	private StoreDetailDao storeDetailDao;
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	@Override
	public void save(StoreDetailModel sm) {
		storeDetailDao.save(sm);
	}
	@Override
	public List<StoreDetailModel> getAll() {
		return storeDetailDao.getAll();
	}
	@Override
	public StoreDetailModel get(Serializable id) {
		return storeDetailDao.get(id);
	}
	@Override
	public void update(StoreDetailModel sm) {
		storeDetailDao.update(sm);
	}
	@Override
	public void delete(StoreDetailModel sm) {
		storeDetailDao.delete(sm);
	}
	@Override
	public List<StoreDetailModel> getAll(BaseQueryModel dqm) {
		return storeDetailDao.getAll(dqm);
	}
	@Override
	public List<StoreDetailModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return storeDetailDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return storeDetailDao.getCount(dqm);
	}
}
