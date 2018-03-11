package com.xinboiedu.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.base.BaseEbi;
@Transactional
public interface MenuEbi extends BaseEbi<MenuModel> {

	List<MenuModel> getAllOneLevel();

	void save(MenuModel mm, Long[] roleIds);

	void update(MenuModel mm, Long[] roleIds);

	List<MenuModel> getAllOneLevel2();

	List<MenuModel> getAllOneLevelByEmp(Long id);

	List<MenuModel> getTwoLevelByEmpIdAndMenuId(Long id, Long menuId);
}
