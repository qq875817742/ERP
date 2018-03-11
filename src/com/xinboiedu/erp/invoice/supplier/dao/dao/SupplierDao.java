package com.xinboiedu.erp.invoice.supplier.dao.dao;

import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;

import java.util.List;

import com.xinboiedu.erp.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel>{

	List<SupplierModel> getAllUnion();

	List<SupplierModel> getAllUnionTwo();
}
