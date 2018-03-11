package com.xinboiedu.erp.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.xinboiedu.erp.auth.menu.business.ebi.MenuEbi;
import com.xinboiedu.erp.auth.menu.vo.MenuModel;
import com.xinboiedu.erp.auth.menu.vo.MenuQueryModel;
import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.role.business.ebi.RoleEbi;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.base.BaseAction;

public class MenuAction extends BaseAction {
	//菜单注入
	private MenuEbi menuEbi;
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}
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
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();
	public Long[]roleIds;
	
	public String list() {
		setCount(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm, pageNum, pageCount);
		put("menuList", menuList);				
		//加载一级菜单
		List<MenuModel> queryMenuList = menuEbi.getAllOneLevel();
		put("queryMenuList", queryMenuList);
		return "list";
	}
	public String input() {
		//获取角色信息
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);		
		//获取一级菜单信息
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList", menuList);
		
		
		if (mm.getId() != null) { 
			mm = menuEbi.get(mm.getId());
			//修改页面设置回显
			Set<RoleModel> roles = mm.getRoles();
			roleIds=new Long[roles.size()];
			int i=0;
			for (RoleModel roleModel : roles) {
				roleIds[i++]=roleModel.getId();
			}
		}
		return "input";
	}
	public String save() {
		if (mm.getId() == null) {
			menuEbi.save(mm,roleIds);
			setSavePage(menuEbi.getCount(mqm));
		} else {
			menuEbi.update(mm,roleIds);
		}
		return "toList";
	}
	public String delete() {
		menuEbi.delete(mm);
		return "toList";
	}
	
	//获取动态菜单数据
	public void showMenu() throws IOException{
		//返回一个JSON数据
		//从request中获取参数root的值，判断是否值是source
		//如果是返回JSON数组1，否则返回JSON数组2
		HttpServletResponse response = getrResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		String root = getRequest().getParameter("root");
		if("source".equals(root)){
			//获取菜单
			List<MenuModel> menuList = menuEbi.getAllOneLevelByEmp(getLoginInfo().getId());
			for (MenuModel mm : menuList) {
				sb.append("{\"text\":\"");		
				sb.append(mm.getName());
				sb.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");	
				sb.append(mm.getId());
				sb.append("\"},");
			}		
		}else{
			Long menuId = new Long(root);
			List<MenuModel> menuList = menuEbi.getTwoLevelByEmpIdAndMenuId(getLoginInfo().getId(),menuId);
			for (MenuModel mm : menuList) {
				sb.append("{\"text\":\"<a class='hei' target='main' href='");
				sb.append(mm.getUrl());
				sb.append("'>");
				sb.append(mm.getName());
				sb.append("</a>\",\"hasChildren\":false,\"classes\":\"file\"},");
			}
		}
			
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");	
		
		pw.write(sb.toString());
		pw.flush();
	}
	
	
}
