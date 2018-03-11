package com.xinboiedu.erp.Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinboiedu.erp.Exception.AppException;

public class ExceptionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError(e.getMessage());
			return "error";
		}catch (Exception e) {
			//记录日志
			//日志发送到程序员邮箱
			//报警
			e.printStackTrace();
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError("服务器出现异常，请联系管理员！");
			return "error";
		}
		
	}

}
