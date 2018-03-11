package com.xinboiedu.erp.Interceptor;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinboiedu.erp.Exception.AppException;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.res.vo.ResModel;
import com.xinboiedu.erp.utils.Constants;

public class AuthInterceptor extends AbstractInterceptor{

	//原本Struts
	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String[] ivcocates =new String[]{"page_login","emp_login","checkImg"};
		for (int i = 0; i < ivcocates.length; i++) {
			if(ivcocates[i].equals(invocation.getProxy().getActionName())){
				return invocation.invoke();
			}
		}
		EmpModel emp = (EmpModel) ActionContext.getContext().getSession().get(Constants.AUTH_LOGIN_KEY);
		if(emp==null){
			//未登录
			return"toLogin";
		}
		//1.必须保证当前操作是已经登录
		//2.获取本次操作
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName=actionName+"."+methodName;
		//2.1获取所有资源列表，判断本次操作是否在资源列表中，如果不在就放行（不在资源列表说明当前操作不需要权限判断）
		ServletContext sc = ServletActionContext.getServletContext();
		String allRes = (String) sc.getAttribute("allRes");
		if(!allRes.contains(allName))
		{
			return invocation.invoke();
		}
		
		//3.获取登录人信息（所具备的资源）
		//3.1判断当前操作是否在当前登录人的资源列表中
		//如果在则放行（说明有权限操作）
		String resList = emp.getResList();
		if(resList.contains(allName))
		{
			return invocation.invoke();
		}
		//说明用户没有权限
		throw new AppException("对不起!权限不足,请不要进行非法操作！");

}
}