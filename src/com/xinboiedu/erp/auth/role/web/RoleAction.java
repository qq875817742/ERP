package com.xinboiedu.erp.auth.role.web;

import java.util.List;
import java.util.Set;

import com.xinboiedu.erp.auth.menu.business.ebi.MenuEbi;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.auth.role.business.ebi.RoleEbi;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.auth.role.vo.RoleQueryModel;
import com.xinboiedu.erp.base.BaseAction;

public class RoleAction extends BaseAction {
	
	//角色注入
	private RoleEbi roleEbi;
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	//资源注入
	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	//菜单注入
	private  MenuEbi menuEbi;		
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}
	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();
	//用于接收资源信息
	public Long[]resIds;
	public Long[]menuIds;
	
	public String list() {
		setCount(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm, pageNum, pageCount);
		put("roleList", roleList);
		return "list";
	}
	public String input() {
		
		List<ResModel> resList = resEbi.getAll();
		put("resList",resList);
		
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList",menuList);
		if (rm.getId() != null) { 
			rm = roleEbi.get(rm.getId());
			//修改页面设置回显
			Set<ResModel> ress = rm.getRess();
			resIds=new Long[ress.size()];
			int i=0;
			for (ResModel resModel : ress) {
				resIds[i++]=resModel.getId();
			}
			//修改页面设置回显
			Set<MenuModel> menus = rm.getMenues();
			menuIds=new Long[menus.size()];
			int j=0;
			for (MenuModel menuModel : menus) {
				menuIds[j++]=menuModel.getId();
			}
			
		
		}
		return "input";
	}
	public String save() {
		if (rm.getId() == null) {
			roleEbi.save(rm,resIds,menuIds);
			setSavePage(roleEbi.getCount(rqm));
		} else {
			roleEbi.update(rm,resIds,menuIds);
		}
		return "toList";
	}
	public String delete() {
		roleEbi.delete(rm);
		return "toList";
	}
}
