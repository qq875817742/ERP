package com.xinboiedu.erp.auth.res.business.ebo;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.res.dao.dao.ResDao;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional(propagation=Propagation.REQUIRES_NEW)
public class ResEbo implements ResEbi{

	private ResDao resDao;
	public void setResDao(ResDao resDao) {
		this.resDao = resDao;
	}
	@Override
	public void save(ResModel rm) {
		resDao.save(rm);
	}
	@Override
	public List<ResModel> getAll() {
		return resDao.getAll();
	}
	@Override
	public ResModel get(Serializable id) {
		return resDao.get(id);
	}
	@Override
	public void update(ResModel rm) {
		resDao.update(rm);
	}
	@Override
	public void delete(ResModel rm) {
		resDao.delete(rm);
	}
	@Override
	public List<ResModel> getAll(BaseQueryModel dqm) {
		return resDao.getAll(dqm);
	}
	@Override
	public List<ResModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return resDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return resDao.getCount(dqm);
	}
	@Override
	public  List<ResModel> getAllResByEmp(Long id) {
		return resDao.getAllResByEmp(id);
	}
}
