package com.xinboiedu.erp.auth.emp.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel> {
	EmpModel login(EmpModel temp, String ip);
	boolean changePwd(String username,String password, String newPassword);
	void save(EmpModel em, Long[] roleIds);
	void update(EmpModel em, Long[] roleIds);
	List<EmpModel> getByRm(Long roleTransport);
}
