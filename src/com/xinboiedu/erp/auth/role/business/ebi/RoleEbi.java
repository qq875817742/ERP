package com.xinboiedu.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface RoleEbi extends BaseEbi<RoleModel> {

	void save(RoleModel rm, Long[] resIds,Long[] menuIds);

	void update(RoleModel rm, Long[] resIds,Long[] menuIds);
}
