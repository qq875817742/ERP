package com.xinboiedu.erp.auth.emp.web;

import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.xinboiedu.erp.auth.dep.business.ebi.DepEbi;
import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.emp.business.ebi.EmpEbi;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.emp.vo.EmpQueryModel;
import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.auth.role.business.ebi.RoleEbi;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.base.BaseAction;
import com.xinboiedu.erp.utils.Constants;
import com.xinboiedu.erp.utils.IPUtil;

public class EmpAction extends BaseAction {
	//员工注入
	private EmpEbi empEbi;
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	//部门注入
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	//角色注入
	private RoleEbi roleEbi;
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	//用于接收角色信息
	public Long[] roleIds;
	
	//资源注入
	private ResEbi resEbi;	
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();
	private String checkCode;//接收验证码
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	private String newPassword;
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	//员工列表
	public String list() {
		getAllDep();
		setCount(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm, pageNum, pageCount);
		put("empList", empList);
		return "list";
	}
	//获取部门列表
	public void getAllDep(){
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
	}
	public String input() {
		//查询所有的部门信息		
		getAllDep();
		//查询所有角色
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);
		if (em.getId() != null) { 
			em = empEbi.get(em.getId());
			//修改页面设置回显
			Set<RoleModel> roles = em.getRoles();
			roleIds=new Long[roles.size()];
			int i=0;
			for (RoleModel roleModel : roles) {
				roleIds[i++]=roleModel.getId();
			}
		}
		return "input";
	}
	
	//跳转到修改密码页面
	public String toChangePwd(){
		
		return "toChangePwd";
	}
	//修改密码完成后
	public String changePwd(){
	boolean flag=empEbi.changePwd(getLoginInfo().getUsername(),em.getPassword(),newPassword);
		if(flag){
			putSession(Constants.AUTH_LOGIN_KEY, null);
			return "toLogin";
		}else{
			this.addActionError("密码错误!");
			return "toChangePwd";
		}
	}
	
	//保存和更新
	public String save() {
		if (em.getId() == null) {
			empEbi.save(em,roleIds);
			setSavePage(empEbi.getCount(eqm));
		} else {
			empEbi.update(em,roleIds);
		}
		return "toList";
	}
	//删除
	public String delete() {
		empEbi.delete(em);
		return "toList";
	}
	
	
	
	//登录
		@InputConfig(resultName="loginErro")
		public String login()
		{
			String serverCode = (String) getSession("checkcode");
			if(checkCode == null || serverCode == null || !serverCode.equalsIgnoreCase(checkCode.trim()))
			{
				this.addActionMessage("验证码错误！");//登录失败回显信息
				return "loginErro";
			}
			String ip = IPUtil.getIP(getRequest());
			EmpModel loginEmp = empEbi.login(em,ip);
			if(loginEmp == null)
			{//登录失败
				this.addActionError("用户名或者密码错误！");//登录失败回显信息
				return "loginErro";
			}else
			{//登录成功
				//获取当前用的资源列表
				List<ResModel> resList = resEbi.getAllResByEmp(loginEmp.getId());
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < resList.size(); i++) {
					sb.append(resList.get(i).getUrl()).append(";");
				}
				loginEmp.setResList(sb.toString());
				putSession(Constants.AUTH_LOGIN_KEY, loginEmp);
				return "loginSuccess";
			}
		}
		//注销
		public String exit(){
			putSession(Constants.AUTH_LOGIN_KEY, null);
			return "toLogin";
		}
}
