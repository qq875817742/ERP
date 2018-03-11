package com.xinboiedu.erp.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xinboiedu.erp.auth.res.business.ebi.ResEbi;
import com.xinboiedu.erp.auth.res.vo.ResModel;


public class AllResListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//读取所有资源信息，放入ServletContext中
		ServletContext sc = event.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);
		ResEbi resEbi = (ResEbi) wac.getBean("resEbi");
		List<ResModel> allResList = resEbi.getAll();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < allResList.size(); i++) {
			sb.append(allResList.get(i).getUrl()).append(";");
		}
		String resStr = sb.toString();
		//放入域对象中
		sc.setAttribute("allRes", resStr);
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
