package com.xinboiedu.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.invoice.supplier.vo.SupplierModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel> {

	List<SupplierModel> getAllUnion();

	List<SupplierModel> getAllUnionTwo();
}
