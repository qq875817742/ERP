<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath }/css/index.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		$(".ajaxMsg").live("click", function() {
			$(".ajaxMsg").empty();
		});
		$(".info").click(function() {
			var jsonParam = {
				"bqm.goodsId" : $(this).attr("gm")
			};
			jsonParam["bqm.type"] = $("[name='bqm.type']").val();
			jsonParam["bqm.supplierId"] = $("[name='bqm.supplierId']").val();
			$tt = $(this).parent().parent();
			$.post("bill_ajaxGetBillDetail.action", jsonParam, function(data) {
				//将制定标记tr删除
				$('.ajaxMsg').empty();

				//每个tr对象都带有一个class="ajaxMsg",用于后期操作删除标记

				//动态添加一个tr行,用于做标题栏
				//创建tr组件

				var $trHead = $("<tr align='center' class='ajaxMsg' style='background:url(images/table_bg.gif) repeat-x;'></tr>");
				var $td1 = $("<td height='30'>订单号</td>");
				$trHead.append($td1);
				var $td2 = $("<td>订单时间</td>");
				$trHead.append($td2);
				var $td3 = $("<td>数量</td>");
				$trHead.append($td3);
				var $td4 = $("<td>单价</td>");
				$trHead.append($td4);
				var $td5 = $("<td>合计</td>");
				$trHead.append($td5);
				$tt.after($trHead);
				$tt = $trHead;

				//--------------------------------------------------------------------------
				var odmList = data;
				var sum = 0;
				for (i = 0; i < odmList.length; i++) {
					var odm = odmList[i];
					var om = odm.om; //订单数据
					sum += odm.totalView * 1;
					var $tr = $("<tr align='center' class='ajaxMsg'></tr>");
					//共计5列
					var $td1 = $("<td height='30'>" + om.orderNum + "</td>");
					$tr.append($td1);
					var $td2 = $("<td>" + om.createTimeView + "</td>");
					$tr.append($td2);
					var $td3 = $("<td>" + odm.num + "</td>");
					$tr.append($td3);
					var $td4 = $("<td align='right'>" + odm.priceView + "&nbsp;元</td>");
					$tr.append($td4);
					var $td5 = $("<td align='right'>" + odm.totalView + "&nbsp;元</td>");
					$tr.append($td5);
					$tt.after($tr);
					$tt = $tr;

				}
				//--------------------------------------------------------------------------


				var $trFoot = $("<tr align='center' class='ajaxMsg'></tr>");
				var $td1 = $("<td align='right' colspan='4' height='30'>总计：</td>");
				$trFoot.append($td1);
				var $td2 = $("<td align='right'>" + intToFloat(sum) + "&nbsp;元</td>");
				$trFoot.append($td2);
				$tt.after($trFoot);
				$tt = $trHead;

			});
		});
		function intToFloat(val) {
			return new Number(val).toFixed(2);
		}

	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货报表</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="bill_buyBillList" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="70" height="30">报表类别:</td>
						<td width="140"><input type="radio" name="all"
							checked="checked">商品名称</td>
						<td width="70">订单类别:</td>
						<td width="190"><s:select name="bqm.type"
								list="@com.xinboiedu.erp.invoice.order.vo.OrderModel@ORDERTYPE_MAP"></s:select>
						</td>
						<td width="70">开始日期:</td>
						<td width="190"><input type="text" size="18"
							onfocus="c.showMoreDay=false;c.show(this);" />
						<td><a id="query"> <img
								src="${pageContext.request.contextPath }/images/can_b_01.gif"
								border="0" />
						</a></td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td><input type="radio" name="all">销售人员</td>
						<td>厂商名称:</td>
						<td><s:select name="bqm.supplierId" list="supplierList"
								listKey="id" listValue="name" headerKey="-1"
								headerValue="===请选择==="></s:select></td>
						<td>结束日期:</td>
						<td width="190"><input type="text" size="18"
							onfocus="c.showMoreDay=false;c.show(this);" />
						<td><a href="bill_downloadExcel.action?bqm.type=${param['bqm.type']}&bqm.supplierId=${param['bqm.supplierId']}"> <img
								src="${pageContext.request.contextPath }/images/can_b_03.gif"
								border="0" />
						</a>
					</tr>
				</table>

			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="70%" border="1" cellpadding="0" cellspacing="0"
					style="float:left;">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td colspan="2" width="49%" height="30">商品名称</td>
						<td colspan="2" width="28%">总数量</td>
						<td width="23%">详情</td>
					</tr>
					<s:iterator value="buyBillList" var="bill">
						<tr align="center" bgcolor="#FFFFFF">
							<td colspan="2" width="30%" height="30">${bill[1].name }</td>
							<td colspan="2">${bill[0]}</td>
							<td><a href="javascript:void(0)" class="xiu info" value="1"
								gm="${bill[1].id }"> 详情 </a></td>
						</tr>
					</s:iterator>
				</table>
				<div style="float:right;">
					<a href="bill_pieBill.action?bqm.type=${param['bqm.type']}&bqm.supplierId=${param['bqm.supplierId']}"> <img id="pei"
						src="bill_pieBill.action?bqm.type=${param['bqm.type']}&bqm.supplierId=${param['bqm.supplierId']}"
						width="228px" height="180px">
					</a>
				</div>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
