package com.xinboiedu.erp.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.auth.role.business.ebi.RoleEbi;
import com.xinboiedu.erp.auth.role.dao.dao.RoleDao;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.base.BaseQueryModel;

@Transactional
public class RoleEbo implements RoleEbi{

	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	public void save(RoleModel rm) {
		roleDao.save(rm);
	}
	@Override
	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}
	@Override
	public RoleModel get(Serializable id) {
		return roleDao.get(id);
	}
	@Override
	public void update(RoleModel rm) {
		roleDao.update(rm);
	}
	@Override
	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}
	@Override
	public List<RoleModel> getAll(BaseQueryModel dqm) {
		return roleDao.getAll(dqm);
	}
	@Override
	public List<RoleModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return roleDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return roleDao.getCount(dqm);
	}
	@Override
	public void save(RoleModel rm, Long[] resIds,Long[] menuIds) {
		Set<ResModel> ress=new HashSet<ResModel>();
		for(Long resId:resIds){
			ResModel resModel = new ResModel();
			resModel.setId(resId);
			ress.add(resModel);
		}
		Set<MenuModel> menues=new HashSet<MenuModel>();
		for(Long menuId:menuIds){
			MenuModel menuModel = new MenuModel();
			menuModel.setId(menuId);
			menues.add(menuModel);
		}
		//设置关联关系
		rm.setRess(ress);
		rm.setMenues(menues);
		roleDao.save(rm);
		
	}
	@Override
	public void update(RoleModel rm, Long[] resIds,Long[] menuIds) {
//		RoleModel temp = roleDao.get(rm.getId());
//		temp.setName(rm.getName());
//		temp.setCode(rm.getCode());
		Set<ResModel> ress=new HashSet<ResModel>();
		for(Long resId:resIds){
			ResModel resModel = new ResModel();
			resModel.setId(resId);
			ress.add(resModel);
		}
		rm.setRess(ress);
		Set<MenuModel> menues=new HashSet<MenuModel>();
		for(Long menuId:menuIds){
			MenuModel menuModel = new MenuModel();
			menuModel.setId(menuId);
			menues.add(menuModel);
		}
		rm.setMenues(menues);
		roleDao.update(rm);
	}
}
