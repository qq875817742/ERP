package com.xinboiedu.erp.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface ResEbi extends BaseEbi<ResModel> {

	 List<ResModel> getAllResByEmp(Long id);
}
