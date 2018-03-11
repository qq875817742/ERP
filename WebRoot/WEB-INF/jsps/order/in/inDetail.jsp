<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath }/css/index.css"
	rel="stylesheet" type="text/css"
/>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"
></script>
<script type="text/javascript">
	

	$(function() {
		//初始化仓库数据
		var storeUuidArr = new Array();
		var storeNameArr = new Array();
		
		var i = 0;
		<s:iterator value="storeList">
		  storeUuidArr[i] = ${id};
		  storeNameArr[i] = "${name}";
		  i++;
		</s:iterator>
			
		
		$(".oper").click(function() {
			var $myTr = $(this).parent().parent();
			var $nextTr = $myTr.next();
			if($nextTr.attr("class") == "in"){
				return;
			}
			if($(".in").length>0){
				$(".in").remove();
			}
		
			var odmId = $(this).attr("odm");
			$.post("orderDetail_ajaxGetSurplus.action",{"om.id":odmId},function(data){
			
			var $newTr = $("<tr class='in'></tr>");
			var $td1 = $("<td align='right'>仓库：</td>");
			    $newTr.append($td1);	
				var storeSelectStr = "<select id='store' style='width:200px'>";
				for(var i = 0;i<storeUuidArr.length;i++){
					storeSelectStr+="<option value='";
					storeSelectStr+=storeUuidArr[i];
					storeSelectStr+="'>";
					storeSelectStr+=storeNameArr[i];
					storeSelectStr+="</option>";
				}
				storeSelectStr += "</select>";
			var $td2 = $("<td height='30'>"+storeSelectStr+"</td>");
			$newTr.append($td2);	
			//2.3入库多少
			var $td3 = $("<td align='right'>入库量：</td>");
			$newTr.append($td3);	
			//获取当前入库数据总量
			var $td4 = $("<td><input id='inNum' type='text' value='"+data.surplus+"'/></td>");
			$newTr.append($td4);	
			var $td5 = $("<td align='center'><a href='javascript:void(0)' class='ajaxIn xiu'><img src='${pageContext.request.contextPath }/images/icon_3.gif' />确定</a></td>");
			$newTr.append($td5);
			//3.将新的行对象添加到当前按钮所在的行对象后面
			$myTr.after($newTr);
			
			});
			
		});
		//确定入库按钮
		$(".ajaxIn").live("click",function(){
		  // 什么是入库?
		  // 将什么东西数量多少放入哪个仓库?
		  // 订单明细id  , 数量 , 仓库
		  
		  var $nowTr = $(this).parent().parent();
		  var $preTr = $nowTr.prev();
		  
		  var jsonParam = {};//json对象
		  jsonParam["odmId"] = $preTr.attr("odm");
		  jsonParam["num"] = $("#inNum").val();
		  jsonParam["storeId"] = $("#store").val();
		  $.post("order_ajaxInStore.action",jsonParam,function(data){
		  		//  data.num data.surplus
		  		var num = data.num;
		  		var surplus = data.surplus;
		  		
		  		//全部商品入库完成
		  		if($(".odms").length == 1 && surplus == 0)
		  		{
		  		  //两个显示
		  		  $("#allInTitle").show();
		  		  $("#return").show();
		  		  //两个隐藏
		  		  $("#inOrderTitle").hide();
		  		  $("#inOrder").hide();
		  		}
		  		
		  		if(surplus == 0)
		  		{
		  		//一项商品入库完成
		  		  $nowTr.remove();
		  		  $preTr.remove()
		  		}
		  		
		  		// 1. 修改已经入库的数量
		  		$preTr.children("td:eq(2)").html(num-surplus);
		  		// 2. 修改剩余数量
		  		$preTr.children("td:eq(3)").html(surplus);
		  		// 3. 修改本次要入库的数量
		  		$nowTr.children("td:eq(3)").children("input").val(surplus);
		  });
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
		<div class="square-o-top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				style="font-size:14px; font-weight:bold; font-family:"
			黑体";">
				<tr>
					<td>订 单 号:</td>
					<td class="order_show_msg">${om.orderNum }</td>
					<td>商品总量:</td>
					<td class="order_show_msg">${om.totalNum }</td>
				</tr>
			</table>
		</div>
		<!--"square-o-top"end-->
		<div class="square-order">
			<center id="inOrderTitle"
				style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"
			黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
			<br />
			<table id="inOrder" width="100%" border="1" cellpadding="0"
				cellspacing="0"
			>
				<tr align="center"
					style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;"
				>
					<td width="20%" height="30">商品名称</td>
					<td width="30%">总数量</td>
					<td width="10%">已入库数量</td>
					<td width="30%">剩余数量</td>
					<td width="10%">入库</td>
				</tr>
				<s:iterator value="om.odms">
					<s:if test="surplus > 0">
						<tr odm="${id}" class="odms"  align="center" bgcolor="#FFFFFF">
							<td height="30">${gm.name }</td>
							<td>${num }</td>
							<td>${num-surplus }</td>
							<td>${surplus }</td>
							<td><a odm="${id}" href="javascript:void(0)" class="oper xiu"><img
									src="${pageContext.request.contextPath }/images/icon_3.gif"
								/>入库</a></td>
						</tr>
					</s:if>
				</s:iterator>
			</table>

			<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
			<table id="return" style="display:none">
				<tr>
					<td>&nbsp;</td>
					<td width="100%" align="center">
					<a href="javascipt:history.back()"
						style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(${pageContext.request.contextPath }/images/btn_bg.jpg)"
					> 返回 </a></td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="content-bbg"></div>
</div>
