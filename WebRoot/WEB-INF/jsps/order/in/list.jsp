<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_inStoreList" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">订单号:</td>
						<td width="40%"><s:textfield name="oqm.orderNum"></s:textfield></td>
						<td width="10%">跟单人:</td>
						<td width="25%"><s:textfield name="oqm.completer.name"></s:textfield></td>
						<td width="15%"><a id="query"> 
							<img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">订单号</td>
						<td width="20%">跟单人</td>
						<td width="20%">种类</td>
						<td width="20%">入库</td>
					</tr>
					<s:iterator value="orderList">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">${orderNum }</td>
							<td>${completer.name }</td>
							<td>${odms.size()}</td>
							<td>
								<img src="${pageContext.request.contextPath }/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 			
									<s:a action="order_inStoreDetail" cssClass="xiu">
									入库
									<s:param name="om.id" value="id"></s:param>
									</s:a>
								</span>
							</td>
						</tr>
					</s:iterator>
							</td>
						</tr>
				</table>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
