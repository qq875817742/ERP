<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<title>进销存系统-系统主页</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
</head>
<body>
	<div class="container">
		<div class="head">
			<div class="head-left">
				<span style="font-weight:bold; color:#1f4906">欢迎您-</span><br />
				<span style="color:#4a940d" va><s:property value="#session.LOGIN_INF.username"/></span>
			</div>
			<div class="head-right">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="32%">
							<a href="${pageContext.request.contextPath }/emp_toChangePwd.action?em.id=${session.LOGIN_INF.id}" target="main">
								<img src="${pageContext.request.contextPath }/images/head-l.gif"	border="0" />
							</a>
						</td>
						<td width="26%">
							<a href="${pageContext.request.contextPath }/emp_exit">
								<img src="${pageContext.request.contextPath }/images/head-m.gif"	border="0" />
							</a>
						</td>
						<td width="7%">&nbsp;</td>
						<td width="35%"><a href="#"><img src="${pageContext.request.contextPath }/images/head-r.gif"
								border="0" />
						</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--"head"end-->

		<div class="content">
			<div class="left">
				<div style="margin-left:2px;">
					<img src="${pageContext.request.contextPath }/images/left-top.gif" width="162" height="25" />
				</div>
				<div class="left-bottom">
						<%@include file="tools/menu.jsp" %>
				</div>
				<!--"left-bottom"end-->
			</div>
			<!--"left"end-->

			<iframe id="frame-contect" src="${pageContext.request.contextPath }/page_context.action"
				style="width:848px;float:right;height:530px" scrolling="no"
				name="main" frameborder="0"></iframe>
			<!--"content-right"end-->
		</div>
		<!--"content"end-->
		<%@include file="tools/mask.jsp" %>
		<div class="footer">
			<div style="margin-top:5px;">
				<table width="98%" border="0" cellpadding="0" cellspacing="0"
					align="center">
					<tr>
						<td width="82%"><img src="${pageContext.request.contextPath }/images/icon_1.gif" />&nbsp; <a
							class="lanyo" href="www.baidu.com">一节制作 2017</a></td>
						<td width="18%" valign="middle"><img src="${pageContext.request.contextPath }/images/icon_2.gif" />&nbsp;
							<a class="lanyo" href="#">如有疑问请与技术人员联系</a></td>
					</tr>
				</table>
			</div>

		</div>
		<!--"footer"end-->
	</div>
	<!--"container"end-->
</body>
</html>
