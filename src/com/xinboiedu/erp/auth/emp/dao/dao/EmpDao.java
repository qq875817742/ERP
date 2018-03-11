package com.xinboiedu.erp.auth.emp.dao.dao;

import java.util.List;

import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel>{

	EmpModel getByUsernameAndPassword(String username, String password);

	boolean changePwd(String usernmae,String password,String newPassword);

	List<EmpModel> getByRm(Long roleTransport);
	
}
