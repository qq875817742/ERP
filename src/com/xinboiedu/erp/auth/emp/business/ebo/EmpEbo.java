package com.xinboiedu.erp.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import com.xinboiedu.erp.auth.emp.business.ebi.EmpEbi;
import com.xinboiedu.erp.auth.emp.dao.dao.EmpDao;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.role.vo.RoleModel;
import com.xinboiedu.erp.base.BaseQueryModel;
import com.xinboiedu.erp.utils.MD5Utils;

@Transactional
public class EmpEbo implements EmpEbi{

	private EmpDao empDao;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	@Override
	public void save(EmpModel em) {
		if(em.getUsername()==null||em.getUsername().trim().length()<0){
//			new throw ex
		}
		em.setPassword(MD5Utils.md5(em.getPassword()));
		em.setLoginCount(0);//设置默认登录次数
		em.setLastLoginIp("-");
		em.setLastLoginTime(System.currentTimeMillis());
		empDao.save(em);
	}
	@Override
	public void save(EmpModel em,Long[]roleIds) {
		em.setPassword(MD5Utils.md5(em.getPassword()));
		em.setLoginCount(0);//设置默认登录次数
		em.setLastLoginIp("-");
		em.setLastLoginTime(System.currentTimeMillis());
		// 设置与角色的关联关系
		Set<RoleModel>roles=new HashSet<RoleModel>();
		for (Long roleId:roleIds){
			RoleModel roleModel = new RoleModel();
			roleModel.setId(roleId);
			roles.add(roleModel);
		}
		em.setRoles(roles);
		empDao.save(em);
	}
	@Override
	public List<EmpModel> getAll() {
		return empDao.getAll();
	}
	@Override
	public EmpModel get(Serializable id) {
		return empDao.get(id);
	}
	@Override
	public void update(EmpModel em) {
//		empDao.update(em);
		//快照更新
		EmpModel temp = empDao.get(em.getId());
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setAddress(em.getAddress());
		temp.setBirthday(em.getBirthday());
		temp.setGender(em.getGender());
		temp.setDep(em.getDep());
	}
	public void update(EmpModel em,Long[]roleIds) {
//		empDao.update(em);
		//快照更新
		EmpModel temp = empDao.get(em.getId());
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setAddress(em.getAddress());
		temp.setBirthday(em.getBirthday());
		temp.setGender(em.getGender());
		temp.setDep(em.getDep());
		
		Set<RoleModel>roles=new HashSet<RoleModel>();
		for (Long roleId:roleIds){
			RoleModel roleModel = new RoleModel();
			roleModel.setId(roleId);
			roles.add(roleModel);
		}
		temp.setRoles(roles);
		
	}
	
	
	@Override
	public void delete(EmpModel em) {
		empDao.delete(em);
	}
	@Override
	public List<EmpModel> getAll(BaseQueryModel dqm) {
		return empDao.getAll(dqm);
	}
	@Override
	public List<EmpModel> getAll(BaseQueryModel dqm, Integer pageNum, Integer pageCount) {
		return empDao.getAll(dqm,pageNum,pageCount);
	}
	@Override
	public Integer getCount(BaseQueryModel dqm) {
		return empDao.getCount(dqm);
	}
	@Override
	public EmpModel login(EmpModel temp,String ip) {
		//MD5加密
			if(temp.getPassword()!=null){
				temp.setPassword(MD5Utils.md5(temp.getPassword()));
			}
			EmpModel login = empDao.getByUsernameAndPassword(temp.getUsername(), temp.getPassword());
			if(login!=null){
				login.setLoginCount(login.getLoginCount()+1);
				login.setLastLoginTime(System.currentTimeMillis());	
				login.setLastLoginIp(ip);
			}
			return login;
	}
	@Override
	public boolean changePwd(String username,String password,String newPassword) {
		newPassword=MD5Utils.md5(newPassword);
		password=MD5Utils.md5(password);
		return empDao.changePwd(username, password, newPassword);
	}
	@Override
	public List<EmpModel> getByRm(Long roleTransport) {
	
		return empDao.getByRm(roleTransport);
	}
}
