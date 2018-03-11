package com.xinboiedu.erp.auth.res.dao.dao;

import java.util.List;

import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.base.BaseDao;

public interface ResDao extends BaseDao<ResModel>{

	 List<ResModel> getAllResByEmp(Long id);
}
