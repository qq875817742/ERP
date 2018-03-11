package com.xinboiedu.erp.invoice.supplier.business.ebo;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.xinboiedu.erp.invoice.supplier.dao.dao.SupplierDao;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class SupplierEbo implements SupplierEbi{

	private SupplierDao supplierDao;
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	@Override
	public void save(SupplierModel sm) {
		supplierDao.save(sm);
	}
	@Override
	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}
	@Override
	public SupplierModel get(Serializable id) {
		return supplierDao.get(id);
	}
	@Override
	public void update(SupplierModel sm) {
		supplierDao.update(sm);
	}
	@Override
	public void delete(SupplierModel sm) {
		supplierDao.delete(sm);
	}
	@Override
	public List<SupplierModel> getAll(BaseQueryModel dqm) {
		return supplierDao.getAll(dqm);
	}
	@Override
	public List<SupplierModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return supplierDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return supplierDao.getCount(dqm);
	}
	@Override
	public List<SupplierModel> getAllUnion() {
		return supplierDao.getAllUnion();
	}
	@Override
	public List<SupplierModel> getAllUnionTwo() {
		return supplierDao.getAllUnionTwo();
	}
}
