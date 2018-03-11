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
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品运输管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="transport_taskList" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>下单时间:</td>
						<td>
							<input value="${oqm.createTimeView}" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							<s:hidden name="oqm.createTime"></s:hidden>
						</td>
						<td>到&nbsp;</td>
						<td>
							<input value="${oqm.createTime2View}" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							<s:hidden name="oqm.createTime2"></s:hidden>
						</td>
						<td>供&nbsp;应&nbsp;商:</td>
						<td>
 
					<s:select name="oqm.sm.id" cssStyle="width:115px" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--" ></s:select> 
						</td>
						<td>下单人:</td>
						<td><s:textfield name="oqm.creater.name" size="14"></s:textfield></td>
						<td>&nbsp;</td>
						<td><a id="query"> 
							<img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>审核时间:</td>
						<td>
							<input type="text" value="${oqm.checkedTimeView}" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							<s:hidden name="oqm.checkedTime"></s:hidden>
						</td>
						<td>到&nbsp;</td>
						<td>
							<input value="${oqm.checkedTime2View}" type="text" size="10" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
							<s:hidden name="oqm.checkedTime2"></s:hidden>
						</td>
						<td>发货方式:</td>	
						<td>
							<s:select cssStyle="width:115px"  name="oqm.sm.needs" list="@com.xinboiedu.erp.invoice.supplier.vo.SupplierModel@NEEDS_MAP" headerKey="-1" headerValue="--请选择--"></s:select>
						</td>
						<td>审核人:</td>
						<td><s:textfield name="oqm.checker.name" size="10"></s:textfield></td>
						<td>跟单人:</td>
						<td><s:textfield name="oqm.completer.name" size="10"></s:textfield></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">订单类别</td>
						<td width="13%">下单时间</td>
						<td width="13%">制单人</td>
						<td width="13%">审核时间</td>
						<td width="13%">审核人</td>
						<td width="15%">供应商</td>
						<td width="13%">发货方式</td>
						<td width="10%">跟单人</td>
					</tr>
					<s:iterator value="orderList">
						 <tr align="center" bgcolor="#FFFFFF">
							<td height="30">${orderTypeView }</td>
							<td>${createTimeView }</td>
							<td>${creater.name }</td>
							<td>${checkedTimeView }</td>
							<td>${checker.name }</td>
							<td>${sm.name }</td>
							<td>${sm.needsView }</td>
							<td>
							<s:if test="status==@com.xinboiedu.erp.invoice.order.vo.OrderModel@ORDER_STATUS_OF_BUY_PASS">
								<img src="${pageContext.request.contextPath }/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<s:a action="transport_toAssignTask" cssClass="xiu">任务指派
										<s:param name="om.id" value="id" />
										</s:a> 
									</span>
							</s:if>
							<s:else>
								${completer.name}
							</s:else>
							</td>
						</tr>
						</s:iterator>
				</table>
				<%@include file="../../tools/page.jsp"  %>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
