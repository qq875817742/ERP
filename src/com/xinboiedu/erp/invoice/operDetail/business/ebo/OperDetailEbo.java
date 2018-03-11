package com.xinboiedu.erp.invoice.operDetail.business.ebo;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.operDetail.business.ebi.OperDetailEbi;
import com.xinboiedu.erp.invoice.operDetail.dao.dao.OperDetailDao;
import com.xinboiedu.erp.invoice.operDetail.vo.OperDetailModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class OperDetailEbo implements OperDetailEbi{

	private OperDetailDao operDetailDao;
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}
	@Override
	public void save(OperDetailModel om) {
		operDetailDao.save(om);
	}
	@Override
	public List<OperDetailModel> getAll() {
		return operDetailDao.getAll();
	}
	@Override
	public OperDetailModel get(Serializable id) {
		return operDetailDao.get(id);
	}
	@Override
	public void update(OperDetailModel om) {
		operDetailDao.update(om);
	}
	@Override
	public void delete(OperDetailModel om) {
		operDetailDao.delete(om);
	}
	@Override
	public List<OperDetailModel> getAll(BaseQueryModel dqm) {
		return operDetailDao.getAll(dqm);
	}
	@Override
	public List<OperDetailModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return operDetailDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return operDetailDao.getCount(dqm);
	}
}
