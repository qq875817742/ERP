<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	function showMsg(msg,uuid,pageNum){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="${pageContext.request.contextPath }/role_delete.action?rm.id="+uuid+"&pageNum="+pageNum;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="role_list.action" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">角色名称</td>
						<td width="142">  <s:textfield name="rqm.name"></s:textfield></td>
						<td width="60">角色编码</td>
						<td width="149"> <s:textfield name="rqm.code"></s:textfield></td>
						<td width="70"><a id="query"> <img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="${pageContext.request.contextPath }/role_input.action"><img src="${pageContext.request.contextPath }/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
			<s:if test="#roleList.size() == 0">
				<center>
					<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
				</center>
			   </s:if>
			   <s:else>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="40%" height="30">角色名称</td>
						<td width="40%">角色编码</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="roleList" var="role">
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">${role.name }</td>
						<td>${role.code }</td>
						<td>
							<img src="${pageContext.request.contextPath }/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<s:a action="role_input.action" cssClass="xiu">修改
								<s:param name="rm.id" value="#role.id"></s:param>
								<s:param name="pageNum" value="pageNum"></s:param>								
								</s:a>
							</span> 
							<img src="${pageContext.request.contextPath }/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',${role.id},${pageNum })">删除</a>
							</span>
						</td>
					</tr>
					</s:iterator>
				</table>
				<%@include file="../tools/page.jsp" %>
				</s:else>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
