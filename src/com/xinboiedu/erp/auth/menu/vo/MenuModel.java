package com.xinboiedu.erp.auth.menu.vo;

import java.util.Set;

import com.xinboiedu.erp.auth.role.vo.RoleModel;

public class MenuModel {

	private Long id;
	private String name,url;
	//对父菜单多对一关系
	private MenuModel parent;
	//对子菜单一对多关系
	private Set<MenuModel> subMenues;
	
	//与角色的关系多对多
	private Set<RoleModel> roles;
	
	
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	public Set<MenuModel> getSubMenues() {
		return subMenues;
	}
	public void setSubMenues(Set<MenuModel> subMenues) {
		this.subMenues = subMenues;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public MenuModel getParent() {
		return parent;
	}
	public void setParent(MenuModel parent) {
		this.parent = parent;
	}
	
}
