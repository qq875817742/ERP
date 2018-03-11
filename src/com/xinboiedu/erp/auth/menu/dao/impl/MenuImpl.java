package com.xinboiedu.erp.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.xinboiedu.erp.auth.menu.dao.dao.MenuDao;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.menu.vo.MenuQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;
import com.xinboiedu.erp.utils.Constants;

public class MenuImpl extends BaseImpl<MenuModel> implements MenuDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		MenuQueryModel mqm = (MenuQueryModel) bqm;
		criteria.add(Restrictions.not(Restrictions.eq("id", Constants.MENU_SYSTEM_MENU_ID)));
		if(mqm.getName()!=null&&mqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
		}
		if(mqm.getParent()!=null&& mqm.getParent().getId() != null && mqm.getParent().getId()!=-1){
			criteria.add(Restrictions.eq("parent.id", mqm.getParent().getId()));
		}
		
	}

	@Override
	public List<MenuModel> getAllOneLevel() {
		String hql="from MenuModel where parent.id=? or id=?";
		return (List<MenuModel>) getHibernateTemplate().find(hql, Constants.MENU_SYSTEM_MENU_ID, Constants.MENU_SYSTEM_MENU_ID);
	}

	@Override
	public List<MenuModel> getAllOneLevel2() {
		String hql="from MenuModel where parent.id=?";
		return (List<MenuModel>) getHibernateTemplate().find(hql, Constants.MENU_SYSTEM_MENU_ID);
	}

	@Override
	public List<MenuModel> getAllOneLevelByEmp(Long id) {		
		return getTwoLevelByEmpIdAndMenuId(id, Constants.MENU_SYSTEM_MENU_ID);
	}

	@Override
	public List<MenuModel> getTwoLevelByEmpIdAndMenuId(Long id, Long menuId) {
		String hql="select distinct menu from MenuModel menu join menu.roles rms join rms.emps ems where ems.id=? and menu.parent.id=? order by menu.id";
		return (List<MenuModel>) getHibernateTemplate().find(hql,id,menuId);
	}
}
