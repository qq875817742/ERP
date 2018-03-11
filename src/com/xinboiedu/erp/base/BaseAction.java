package com.xinboiedu.erp.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xinboiedu.erp.auth.dep.vo.DepQueryModel;
import com.xinboiedu.erp.auth.emp.vo.EmpModel;
import com.xinboiedu.erp.utils.Constants;

public class BaseAction extends ActionSupport{
	public Integer pageNum=1;//当前页面
	public Integer pageCount=4;//每页显示数量。（可改）
	public Integer maxPageNum;//最大页数
	public Integer dataTotal;//总记录数
	//分页计算方法
	public void setCount(Integer totalNum){
		dataTotal=totalNum;
		//总页数
		maxPageNum=(dataTotal+pageCount-1)/pageCount;
		if(pageNum<1)pageNum=1;
		if(pageNum>maxPageNum)pageNum=maxPageNum;
	}
	//保存成功的页码设置
	public void setSavePage(Integer totalNum){
		setCount(totalNum);
		pageNum=maxPageNum;
	}
	//操作request域
	public void put(String name,Object objec){
		ActionContext.getContext().getContextMap().put(name, objec);
	}
	public Object get(String name){
		return ActionContext.getContext().getContextMap().get(name);
	}
	//操作session域
	public void putSession(String name,Object objec){
		ActionContext.getContext().getSession().put(name, objec);
	}
	public Object getSession(String name){
		return ActionContext.getContext().getSession().get(name);
	}
	//获取用户信息
	public EmpModel getLoginInfo(){
		return (EmpModel)getSession(Constants.AUTH_LOGIN_KEY);
	}
	//获取请求响应
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public HttpServletResponse getrResponse(){
		return ServletActionContext.getResponse();
	}
}
