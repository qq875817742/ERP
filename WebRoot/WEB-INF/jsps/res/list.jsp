<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		top.$('hid-action').value="${pageContext.request.contextPath }/res_delete.action?rm.id="+uuid+"&pageNum="+pageNum;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">资源管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="res_list.action" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">资源名称</td>
						<td width="123">
							<s:textfield name="rqm.name"></s:textfield>
						</td>
						<td width="62">资源类别</td>
						<td width="142">
							<select class="kuan">
								<option value="-1">----请-选-择----</option>
								<option value="1">URL</option>
								<option value="2">action访问</option>
							</select>
						</td>
						<td width="60">操作类别</td>
						<td width="149">
							<select class="kuan">
								<option value="-1">----请-选-择----</option>
								<option value="1">可操作</option>
								<option value="2">可视</option>
							</select>
						</td>
						<td width="70"><a id="query"> <img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="${pageContext.request.contextPath }/res_input.action"><img src="${pageContext.request.contextPath }/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
			<s:if test="#resList.size() == 0">
				<center>
					<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
				</center>
			   </s:if>
			   <s:else>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td height="30">资源名称</td>
						<td>资源类别</td>
						<td>操作类别</td>
						<td>资源值</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="resList">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30">${name }</td>
						<td>action访问</td>
						<td>可访问</td>
						<td align="left">${url }</td>
						<td>
							<img src="${pageContext.request.contextPath }/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<s:a action="res_input.action" cssClass="xiu">修改
								<s:param name="rm.id" value="id"></s:param>
								<s:param name="pageNum" value="pageNum"></s:param>								
								</s:a>
							</span> 
							<img src="${pageContext.request.contextPath }/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',${id},${pageNum })">删除</a>
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
