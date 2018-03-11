package com.xinboiedu.erp.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.xinboiedu.erp.auth.emp.dao.dao.EmpDao;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.emp.vo.EmpQueryModel;
import com.xinboiedu.erp.base.BaseImpl;
import com.xinboiedu.erp.base.BaseQueryModel;

public class EmpImpl extends BaseImpl<EmpModel> implements EmpDao{

	public void doQBC(DetachedCriteria criteria,BaseQueryModel bqm)
	{
		EmpQueryModel eqm = (EmpQueryModel) bqm;
		if(eqm.getUsername()!=null&&eqm.getUsername().trim().length()>0){
			criteria.add(Restrictions.like("username", "%"+eqm.getUsername().trim()+"%"));
		}
		if(eqm.getName()!=null&&eqm.getName().trim().length()>0){
			criteria.add(Restrictions.like("name", "%"+eqm.getName().trim()+"%"));
		}
		if(eqm.getTele()!=null&&eqm.getTele().trim().length()>0){
			criteria.add(Restrictions.like("tele", "%"+eqm.getTele().trim()+"%"));
		}
		if(eqm.getEmail()!=null&&eqm.getEmail().trim().length()>0){
			criteria.add(Restrictions.like("email", "%"+eqm.getEmail().trim()+"%"));
		}
		if(eqm.getGender()!=null&&eqm.getGender()!=-1){
			criteria.add(Restrictions.eq("gender", eqm.getGender()));
		}
		//eqm.dep.id
		if(eqm.getDep()!=null&&eqm.getDep().getId()!=null&&eqm.getDep().getId()!=-1){
//			criteria.add(Restrictions.eq("dep", eqm.getDep()));
			criteria.add(Restrictions.eq("dep.id", eqm.getDep().getId()));
		}
		//时间的判断条件
		//最小时间段
		if(eqm.getBirthday()!=null){
			criteria.add(Restrictions.ge("birthday", eqm.getBirthday()-1));
		}
		//最大时间段
		if(eqm.getBirthday2()!=null){
			criteria.add(Restrictions.le("birthday", eqm.getBirthday2()+86400000));
		}
		
	}

	@Override
	public EmpModel getByUsernameAndPassword(String username, String password) {
		String hql = "from EmpModel where username=? and password=?";
		List<EmpModel> list = (List<EmpModel>) getHibernateTemplate().find(hql , username,password);
		if(list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean changePwd(final String username,final String password,final String newPassword) {
		Integer execute = getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql="update EmpModel set password=? where username=? and password=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, newPassword);
				query.setParameter(1, username);
				query.setParameter(2, password);
				int i = query.executeUpdate();
				return i;
			}
		});
		return execute>0;
	}

	@Override
	public List<EmpModel> getByRm(Long roleTransport) {
		String hql="select distinct em from EmpModel em join em.roles rm where rm.id=?";
		return (List<EmpModel>) getHibernateTemplate().find(hql, roleTransport);
	}
}
