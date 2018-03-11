<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		
		$(".unit").click(function(){
			$("[name='gqm.unit']").val($(this).text());
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	function showMsg(msg,uuid,pageNum){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="${pageContext.request.contextPath }/goods_delete.action?gm.id="+uuid+"&pageNum="+pageNum;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="goods_list.action" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>供应商:</td>
						<td>
							<s:select name="gqm.gtm.sm.id" cssClass="kuan" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--" ></s:select>
						</td>
						<td height="30">商&nbsp;品&nbsp;名</td>
						<td><s:textfield name="gqm.name" size="10"></s:textfield></td>
						<td>生产厂家</td>
						<td><s:textfield name="gqm.origin" size="10"></s:textfield></td>
						<td>单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
						<td><s:textfield name="gqm.unit" size="10"></s:textfield></td>
						<td width="70"><a href="${pageContext.request.contextPath }/goods_input.action"><img src="${pageContext.request.contextPath }/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">进货价格</td>
						<td><s:textfield name="gqm.inPrice" value="%{gqm.inPriceView}" size="10"></s:textfield></td>
						<td>到</td>
						<td><s:textfield name="gqm.inPrice2" value="%{gqm.inPrice2View}" size="10"></s:textfield></td>
						<td height="30">销售价格</td>
						<td><s:textfield name="gqm.outPrice" value="%{gqm.outPriceView}" size="10"></s:textfield></td>
						<td>到</td>
						<td><s:textfield name="gqm.outPrice2" value="%{gqm.outPrice2View}" size="10"></s:textfield></td>
						<td><a id="query"> <img src="${pageContext.request.contextPath }/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
			<s:if test="#goodsList.size() == 0">
				<center>
					<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
				</center>
			   </s:if>
			   <s:else>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="12%" height="30">供应商</td>
						<td width="12%">商品名</td>
						<td width="12%">生产厂家</td>
						<td width="12%">产地</td>
						<td width="12%">进货价格</td>
						<td width="12%">销售价格</td>
						<td width="12%">单位</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="goodsList">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">${gtm.sm.name}</td>
							<td>${name }</td>
							<td>${producer }</td>
							<td>${origin}</td>
							<td align="right">${inPriceView }&nbsp;元&nbsp;</td>
							<td align="right">${outPriceView }&nbsp;元&nbsp;</td>
							<td><a href="javascript:void(0)" class="unit">${unit }</a> </td>
							<td>
								<img src="${pageContext.request.contextPath }/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<s:a action="goods_input.action" cssClass="xiu">修改
									<s:param name="gm.id" value="id"></s:param>
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
