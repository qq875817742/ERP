<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
</script>
<script type="text/javascript">

 
	$(function(){
	//$("#supplier").change(function(){
	$("#supplier").live("change",function(){
		//获取供应商id，ajax请求对应数据
		var supplierId=$(this).val();
		$.post("order_ajaxGetGtmAndGm.action",{"supplierId":supplierId},function(data){
		//返回数据响应
		//清空商品类别下拉框
		//清空商品下拉框
		$(".goodsType").empty();
		$(".goods").empty();
		//获取数据
		var gtmList=data.gtmList;
		for(var i=0;i<gtmList.length;i++)
		{
			var gtm=gtmList[i];
			$op=$("<option value='"+gtm.id+"'>"+gtm.name+"</option>");
			$(".goodsType").append($op);
		}
		var gmList=data.gmList;
		for(var i=0;i<gmList.length;i++)
		{
			var gm=gmList[i];
			$op=$("<option value='"+gm.id+"'>"+gm.name+"</option>");
			$(".goods").append($op);
		}
		//修改数量
		$(".nums").val(1);
		var price=data.gm.inPriceView;
		//修改单价
		$(".prices").val(price);
		//修改合计
		$(".total").html(price+"元");
		//修改总计
		$(".all").html(price+"元");
			total();	
		});
	});
	
	
	//修改商品类别
	$(".goodsType").live("change",function(){
		var $nowTr=$(this).parent().parent();
		var $gmSelect = $nowTr.children("td:eq(1)").children("select");
		var $nums=$nowTr.children("td:eq(2)").children("input");
		var $price=$nowTr.children("td:eq(3)").children("input");
		var $total=$nowTr.children("td:eq(4)");
		
		//获取当前页面已经使用商品id，传递到后台。
			var used = "";
			var goodsArray = $(".goods");
			for(var i = 0;i < goodsArray.length;i++)
			{
				used = used +"'"+goodsArray[i].value + "',"
			}
			
		
		var goodsTypeId=$(this).val();
		//ajax请求
		$.post("order_ajaxGetGm.action",{"goodsTypeId":goodsTypeId,"used":used},function(data){
// 		$(".goods").empty();
		$gmSelect.empty();
		var gmList=data.gmList;
		for(var i=0;i<gmList.length;i++)
		{
			var gm=gmList[i];
			$op=$("<option value='"+gm.id+"'>"+gm.name+"</option>");
			$(".goods").append($op);
		}
		//修改数量
		$nums.val(1);
		var price=data.gm.inPriceView;
		//修改单价
		$price.val(price);
		//修改合计
		$total.html(price+"元");
		//修改总计
		$(".all").html(price+"元");
			total();	
		});
	});
	//修改商品	
	$(".goods").live("change",function(){
		var goodsId=$(this).val();
		
			var $nowTr = $(this).parent().parent();
			var $nums = $nowTr.children("td:eq(2)").children("input");
			var $price = $nowTr.children("td:eq(3)").children("input");
			var $total = $nowTr.children("td:eq(4)");
		
		$.post("order_ajaxGetPrice.action",{"goodsId":goodsId},function(data){
		//修改数量
		$nums.val(1);
		var price=data.gm.inPriceView;
		//修改单价
		$price.val(price);
		//修改合计
		$total.html(price+"元");
		//修改总计
		$(".all").html(price+"元");
			total();	
		});
	});
	
	//添加按钮事件
	
	var clickFlag = true;
	$("#add").click(function(){
	 //锁定
		  $("#supplier").attr("disabled",true);
		  $(".goodsType").attr("disabled",true);
		  $(".goods").attr("disabled",true);
	
	 if(!clickFlag)
		 {
		   return;
		 }
		 clickFlag = false;
	
	
		var supplierId=$("#supplier").val();
		var used="";
		var goodsArray=$(".goods");
		for(var i=0;i<goodsArray.length;i++){
			used=used+"'"+goodsArray[i].value+"',"
		}
		
		$.post("order_ajaxGetGtmAndGm2.action",{"supplierId":supplierId,"used":used},function(data){	
		//需求:动态生成行，添加指定位置
		//分析 ：1.动态生成行，行内使用的数据时固定的还是动态（AJAX）
		//指定位置：总计的上方finalTr
		//生成一行
		$tr=$('<tr align="center" bgcolor="#FFFFFF"></tr>');
		
		$td1=$('<td height="30"></td>');
		$gtmSelect=$('<select class="goodsType" style="width:200px"></select>');
		var gtmList=data.gtmList;		
		for (var i = 0; i < gtmList.length; i++){
			var gtm=gtmList[i];		
			$op=$('<option value="'+gtm.id+'">'+gtm.name+'</option>');
			$gtmSelect.append($op);
		}				
		$td1.append($gtmSelect);
		$tr.append($td1);
		
		$td2=$('<td></td>');
		$gmSelect=$('<select name="goodsIds" class="goods" style="width:200px" ></select>');
		var gmList=data.gmList;	
		for(var i=0;i<gmList.length;i++){
			var gm=gmList[i];
			$op=$('<option value="'+gm.id+'">'+gm.name+'</option>');
			$gmSelect.append($op);
		}			
		$td2.append($gmSelect);
		$tr.append($td2);
		
		$td3=$('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>');
		$tr.append($td3);
		
		var price = data.gm.inPriceView;
		$td4=$('<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="'+price+'" /> 元</td>');
		$tr.append($td4);
		
		$td5=$('<td class="total" align="right">'+price+'元</td>');	
		$tr.append($td5);
		
		$td6=$('<td><a class="deleteBtn delete xiu" value="4"><img src="${pageContext.request.contextPath }/images/icon_04.gif" /> 删除</a></td>');
		$tr.append($td6);
		//添加
		$("#finalTr").before($tr);
			total();	
		//如果供应商下面所有商品都已经显示了那么添加按钮消失
		if(gtmList.length==1&&gmList.length==1){
			$("#add").css("display","none");
		}
		clickFlag = true;
		});
	});
	$(".deleteBtn").live("click",function(){
		//如果只剩下一行就不能删除
		if($(".deleteBtn").length==1){
			return;
		}
		//删除当前行
		$nowTr=$(this).parent().parent();
		$nowTr.remove();
		$("#add").css("display","inline");
			total();	
	});
	//修改数量
	  $(".num").live("keyup",function(){
	  		$(this).val($(this).val().replace(/[^\d.]/g, ""));
	        //获取当前行
	       totalPrice(this);	
	       	total();	
	  });
	  //修改单价
	  $(".prices").live("keyup",function(){
	  //先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); 
	  
	        //获取当前行
		totalPrice(this);
		total();	
	  });
	  
	  function totalPrice(obj){
	  	    var $nowTr = $(obj).parent().parent();
			var $num = $nowTr.children("td:eq(2)").children("input");
			var $price = $nowTr.children("td:eq(3)").children("input");
			var $total = $nowTr.children("td:eq(4)");
			
			var total = $num.val()*$price.val();
	        $total.html(intToFloat(total) + "元");
	  }
	function intToFloat(val){
 return new Number(val).toFixed(2);
 }
	function total(){
			var nums = $(".num");
			var prices = $(".prices");
			var total = 0;
			for(var i = 0;i<nums.length;i++){
				var num = $(nums[i]).val();
				var price=  $(prices[i]).val();
				total = total + num * price ;
			}
			$(".all").text(intToFloat(total)+" 元");
		}
		$("#submitOrder").click(function(){
			$("#supplier").attr("disabled",false);
			$(".goods").attr("disabled",false);
			$(".goodsType").attr("disabled",false);
			//提交页面中的第一个form对象
			$("form:first").submit();
		});
		
	});












/* 
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	//修改供应商
	$(document).ready(function() {
		$("#supplier").change(function(){
				//修改产品类别select
				$(".goodsType").empty();
				for(var i = 0;i<4;i++){
					var $option = $("<option value='1'>商品类别"+i+"</option>");	//创建option对象(jQuery格式)
					$(".goodsType").append($option);				//将option对象添加到select组件中
				}
				//修改商品select
				$(".goods").empty();
				for(var i = 0;i<5;i++){
					var $option = $("<option value='1'>商品名"+i+"</option>");	//创建option对象(jQuery格式)
					$(".goods").append($option);				//将option对象添加到select组件中
				}
				//修改商品数量
				$(".order_num").val("1");
				//修改商品单价
				$("[name='prices']").val(intToFloat(300));
				//修改商品合计
				$(".total").text(intToFloat(300)+" 元");
				//修改商品总计
				total();
		});
		//修改商品类别
		$(".goodsType").live("change",function(){
			var $goodsSelectObj = $($(this).parent().next().children()[0]);
			var $num = $($(this).parent().next().next().children()[0]);
			var $price = $($(this).parent().next().next().next().children()[0]);
			var $total = $(this).parent().next().next().next().next();
			//发送的请求要将当前已经使用的过滤掉，否则每次出现的集合会重复
			var jsonParam = {"gm.goodsType.uuid":$(this).val()};
			var goodsUuids = "";
			//取出所有select
			var goodsObjs = $(".goods");
			for(i = 0;i<goodsObjs.length;i++){
				goodsUuids = goodsUuids + $(goodsObjs[i]).val();
				if(i != goodsObjs.length -1){
					goodsUuids = goodsUuids + ",";
				}
			}
			jsonParam["goodsUuids"]= goodsUuids;
				
			$goodsSelectObj.empty();
			for(var i = 0;i<5;i++){
				var $option = $("<option value='222'>新商品名"+1+"</option>");	//创建option对象
				$goodsSelectObj.append($option);				//将option对象添加到select组件中
			}
			//修改商品数量
			$num.val("1");
			//修改商品单价
			$price.val(intToFloat(400));
			//修改商品合计
			$total.text(intToFloat(400)+" 元");
			//修改商品总计
			total();
		});
		//修改商品
		$(".goods").live("change",function(){
			var $num = $($(this).parent().next().children()[0]);
			var $price = $($(this).parent().next().next().children()[0]);
			var $total = $(this).parent().next().next().next();
			
			//修改商品数量
			$num.val("1");
			//修改商品单价
			$price.val(intToFloat(111));
			//修改商品合计
			$total.text(intToFloat(111)+" 元");
			//修改商品总计
			total();
		});
		//添加新商品
		$("#add").click(function(){
			//设置不能修改供应商
			$("#supplier").attr("disabled","disabled");
			//设置已存在的所有select全部不可点击
			$(".goodsType").attr("disabled","disabled");
			$(".goods").attr("disabled","disabled");
			
			//发送ajax提交，将供应商id与当前已经使用的类别对应商品2个id传递到后台，并将其过滤，过滤完毕的数据传递回页面
			var goodsTypeObjs = $(".goodsType");
			var goodsObjs = $(".goods");
			var jsonParam = {"gm.goodsType.supplier.uuid":$("#supplier").val()};
			var hasUuids = "";
			for(i = 0;i<goodsTypeObjs.length;i++){
				hasUuids = hasUuids + $(goodsTypeObjs[i]).val();
				hasUuids = hasUuids + ":";
				hasUuids = hasUuids + $(goodsObjs[i]).val();
				if(i != goodsTypeObjs.length -1){
					hasUuids = hasUuids + ",";
				}
			}
			jsonParam["hasUuids"]= hasUuids;
				
				//动态添加一个tr行
				//创建tr组件
				var $tr = $("<tr></tr>");
				
				//创建td组件，生成商品类别select
				var typeSelectStr = "<select class='goodsType' style='width:200px'>";
				for(i = 0;i<4;i++){
					typeSelectStr+="<option value='";
					typeSelectStr+=111;
					typeSelectStr+="'>";
					typeSelectStr+="类别名称"+i;
					typeSelectStr+="</option>";
				}
				typeSelectStr += "</select>";
				var $td1 = $("<td height='30'>"+typeSelectStr+"</td>");
				$tr.append($td1);
				
				//创建td组件，生成商品select
				var goodsSelectStr = "<select name='gmUuids' class='goods' style='width:200px'>";
				for(i = 0;i<4;i++){
					goodsSelectStr+="<option value='";
					goodsSelectStr+=123;
					goodsSelectStr+="'>";
					goodsSelectStr+="新商品名"+i;
					goodsSelectStr+="</option>";
				}
				goodsSelectStr += "</select>";
				var $td2 = $("<td>"+goodsSelectStr+"</td>");
				$tr.append($td2);
				
				//创建td组件，生成商品数量input，默认值1
				var $td3 = $("<td>&nbsp;<input name='nums' type='text' value='1' class='num order_num' style='width:67px;border:1px solid black;text-align:right;padding:2px' /></td>");
				$tr.append($td3);
				
				var $td4 = $("<td style='padding-left:4px'><input name='prices' type='text' value='"+222+"' class='prices order_num' style='width:93px;border:1px solid black;text-align:right;padding:2px' /> 元</td>");
				$tr.append($td4);
				
				var $td5 = $("<td class='total' align='right'>"+222+" 元</td>");
				$tr.append($td5);
				
				var $td6 = $("<td>&nbsp;&nbsp;<a class='deleteBtn delete xiu' value='"+112+"'><img src='${pageContext.request.contextPath }/images/icon_04.gif'/> 删除</a></td>");
				$tr.append($td6);
				
				//在最后一个tr对象前添加该tr组件
				$("#finalTr").before($tr);
				//注意：新添加的元素是否具有动态事件激活能力
				
				//获取后台数据是否还有下一个可用的商品，判断添加按钮是否显示
				if("Y" == "N"){
					//将添加按钮设置为不可显示
					$("#add").css("display","none");
				}
				total();
		});
		//修改商品数量，事件绑定为点击任意键盘数字按钮
		$(".num").live("keyup",function(event){
			//事件激活方式为任意键盘数字按钮，避免用户输入非法数字
			if(event.keyCode>=48 && event.keyCode<=57 || event.keyCode>=96 && event.keyCode<=105 || event.keyCode == 8){
				//获取当前输入数量值
				var num = $(this).val();
				//获取当前价格
				var price = $($(this).parent().next().children()[0]).val();
				$(this).parent().next().next().text(intToFloat(num*price)+" 元");
				total();
				return true;
			}
			return false;
		});
		//修改商品价格，事件绑定为点击任意键盘数字按钮
		$(".prices").live("keyup",function(event){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); 

	        //获取当前输入价格
			var price = $(this).val();
			//获取当前数量
			var num = $($(this).parent().prev().children()[0]).val();
			//求合计
			$(this).parent().next().text(intToFloat(num*price)+" 元");
			//求总计
			total();
		});
		
		$(".deleteBtn").live("click",function(){
			if($(".deleteBtn").size()>1){
				//获取当前所在行，并将其删除
				$(this).parent().parent().remove();
				//将添加按钮设置为可显示状态
				$("#add").css("display","inline");
			}
			//将所有项设置为不可修改
			$(".goodsType").attr("disabled","disabled");
			$(".goods").attr("disabled","disabled");
			total();
		});
		
		//求总和的方法
		function total(){
			var nums = $(".num");
			var prices = $(".prices");
			var total = 0;
			for(var i = 0;i<nums.length;i++){
				var num = $(nums[i]).val();
				var price=  $(prices[i]).val();
				total = total + num * price ;
			}
			$(".all").text(intToFloat(total)+" 元");
		}
		
		//提交按钮（设置不可修改的
		$("#submitOrder").click(function(){
			$("#supplier").removeAttr("disabled");
			$(".goods").removeAttr("disabled");
			//提交页面中的第一个form对象
			$("form:first").submit();
		});
	}); */
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_buySave.action" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="24">&nbsp;</td>
					</tr>
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">							 
							<s:select name="om.sm.id" id="supplier" cssClass="kuan" cssStyle="width:190px" list="supplierList" listKey="id" listValue="name"></s:select>
						</td>
						<td height="30">
							<a id="add"><img src="${pageContext.request.contextPath }/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<s:select cssClass="goodsType" cssStyle="width:200px" list="goodsTypeList" listKey="id" listValue="name"></s:select>
						</td>
						<td>							
							<s:select name="goodsIds" cssClass="goods" cssStyle="width:200px" list="goodsList" listKey="id" listValue="name"></s:select>
						</td>
						<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${goodsList[0].inPriceView}"/> 元</td>
						<td class="total" align="right">${goodsList[0].inPriceView}&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="${pageContext.request.contextPath }/images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(${pageContext.request.contextPath }/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">${goodsList[0].inPriceView }&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="${pageContext.request.contextPath }/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath }/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:history.back()"><img src="${pageContext.request.contextPath }/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</s:form>
	</div>
	
	<div class="content-bbg"></div>
</div>
