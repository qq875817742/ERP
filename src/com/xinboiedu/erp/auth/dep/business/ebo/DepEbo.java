package com.xinboiedu.erp.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xinboiedu.erp.auth.dep.business.ebi.DepEbi;
import com.xinboiedu.erp.auth.dep.dao.dao.DepDao;
import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.dep.vo.DepQueryModel;
import com.xinboiedu.erp.base.BaseQueryModel;


@Transactional
public class DepEbo implements DepEbi{
private DepDao depDao;

public void setDepDao(DepDao depDao) {
	this.depDao = depDao;
}

@Override
public void save(DepModel dm) {
	depDao.save(dm);
}

@Override
public List<DepModel> getAll() {
	return depDao.getAll();
	
}

@Override
public DepModel get(Serializable id) {	
	return depDao.get(id);
}

@Override
public void update(DepModel dm) {
	depDao.update(dm);
	
}

@Override
public void delete(DepModel dm) {
	depDao.delete(dm);
}

@Override
public List<DepModel> getAll(BaseQueryModel bqm) {
	
	return depDao.getAll(bqm);
}

@Override
public List<DepModel> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
	return depDao.getAll(bqm,pageNum,pageCount);
}

@Override
public Integer getCount(BaseQueryModel bqm) {
	return depDao.getCount(bqm);
}


}
