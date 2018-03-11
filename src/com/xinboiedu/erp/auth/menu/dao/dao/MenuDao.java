package com.xinboiedu.erp.auth.menu.dao.dao;

import java.util.List;

import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel>{

	List<MenuModel> getAllOneLevel();

	List<MenuModel> getAllOneLevel2();

	List<MenuModel> getAllOneLevelByEmp(Long id);

	List<MenuModel> getTwoLevelByEmpIdAndMenuId(Long id, Long menuId);


}
