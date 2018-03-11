package com.xinboiedu.erp.auth.store.business.ebo;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.store.business.ebi.StoreEbi;
import com.xinboiedu.erp.auth.store.dao.dao.StoreDao;
import com.xinboiedu.erp.auth.store.vo.StoreModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class StoreEbo implements StoreEbi{

	private StoreDao storeDao;
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}
	@Override
	public void save(StoreModel sm) {
		storeDao.save(sm);
	}
	@Override
	public List<StoreModel> getAll() {
		return storeDao.getAll();
	}
	@Override
	public StoreModel get(Serializable id) {
		return storeDao.get(id);
	}
	@Override
	public void update(StoreModel sm) {
		storeDao.update(sm);
	}
	@Override
	public void delete(StoreModel sm) {
		storeDao.delete(sm);
	}
	@Override
	public List<StoreModel> getAll(BaseQueryModel dqm) {
		return storeDao.getAll(dqm);
	}
	@Override
	public List<StoreModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return storeDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return storeDao.getCount(dqm);
	}
}
