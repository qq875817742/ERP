package com.xinboiedu.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.menu.business.ebi.MenuEbi;
import com.xinboiedu.erp.auth.menu.dao.dao.MenuDao;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class MenuEbo implements MenuEbi{

	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	@Override
	public void save(MenuModel mm) {
		menuDao.save(mm);
	}
	@Override
	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}
	@Override
	public MenuModel get(Serializable id) {
		return menuDao.get(id);
	}
	@Override
	public void update(MenuModel mm) {
		menuDao.update(mm);
	}
	@Override
	public void delete(MenuModel mm) {
		// 传入的 MenuModel对象处于游离状态。只有id属性其他属性值都为null（没有与子菜单关系）
		MenuModel model = menuDao.get(mm.getId());
		menuDao.delete(model);
	}
	@Override
	public List<MenuModel> getAll(BaseQueryModel dqm) {
		return menuDao.getAll(dqm);
	}
	@Override
	public List<MenuModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return menuDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return menuDao.getCount(dqm);
	}
	@Override
	public List<MenuModel> getAllOneLevel() {
		return menuDao.getAllOneLevel();
	}
	@Override
	public List<MenuModel> getAllOneLevel2() {
		return menuDao.getAllOneLevel2();
	}
	@Override
	public List<MenuModel> getAllOneLevelByEmp(Long id) {
		return menuDao.getAllOneLevelByEmp(id);
	}
	@Override
	public List<MenuModel> getTwoLevelByEmpIdAndMenuId(Long id, Long menuId) {
		return menuDao.getTwoLevelByEmpIdAndMenuId(id,menuId);
	}
	@Override
	public void save(MenuModel mm, Long[] roleIds) {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long roleId:roleIds){
			RoleModel temp = new RoleModel();
			temp.setId(roleId);
			roles.add(temp);
		}
		mm.setRoles(roles);
		menuDao.save(mm);
	}
	@Override
	public void update(MenuModel mm, Long[] roleIds) {
		MenuModel temp = menuDao.get(mm.getId());
		
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long roleId:roleIds){
			RoleModel rm = new RoleModel();
			rm.setId(roleId);
			roles.add(rm);
		}
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		temp.setParent(mm.getParent());
		temp.setRoles(roles);
	
	}
	
	
	
}
