package com.xinboiedu.erp.auth.emp.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xinboiedu.erp.auth.dep.vo.DepModel;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.utils.FormatterUtil;

public class EmpModel {
	//数据字典	
	public static final Integer EMP_GENDER_OF_MAN=1;
	public static final Integer EMP_GENDER_OF_WOMAN=0;

	public static final String  EMP_GENDER_OF_MAN_VIEW="男";
	public static final String  EMP_GENDER_OF_WOMAN_VIEW="女";
	
	public static final Map<Integer, String>GENDER_MAP=new HashMap<Integer, String>();
	static{
		GENDER_MAP.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		GENDER_MAP.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	
	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String tele;
	private String address;
	private Integer gender;
	private Long birthday;
	private DepModel dep;
	
	//对角色多对多
	private Set<RoleModel>roles;
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
	//生日的视图值
	private String birthdayView;
		
	public String getBirthdayView() {
		return birthdayView;
	}
	//在对应的真实值的setter方法中设置值
		public void setBirthday(Long birthday) {
			this.birthday = birthday;
			birthdayView=FormatterUtil.FormaterDate(birthday);
		}
	//性别的视图值	
	public String genderView;
	public String getGenderView() {
		return genderView;
	}
	//在对应的真实值的setter方法中设置值
	public void setGender(Integer gender) {
		this.gender = gender;
		genderView=GENDER_MAP.get(gender);
	}
	
	private Long lastLoginTime;
	private String lastLoginIp;
	private Integer loginCount;
	//辅助值
	private String resList;
	
	
	public String getResList() {
		return resList;
	}
	public void setResList(String resList) {
		this.resList = resList;
	}
	//视图值
	private String lastLoginTimeView;
	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}
	
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		lastLoginTimeView=FormatterUtil.FormaterTimeStamp(lastLoginTime);
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getGender() {
		return gender;
	}
	
	public Long getBirthday() {
		return birthday;
	}
	
	public DepModel getDep() {
		return dep;
	}
	public void setDep(DepModel dep) {
		this.dep = dep;
	}
	
	
	

}
