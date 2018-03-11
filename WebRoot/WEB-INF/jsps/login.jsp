<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<title>进销存-系统登录页</title>
<script>


	$(function(){
	var $info = $("ul span");
	    $info.unwrap();
	    $info.unwrap();
	
	    $("form:first").submit(function(){
	      var isSubmit = true;
	      //为表单注册提交事件
	      var username = $("#username").val();
	      if(username == '')
	      {
	      	$("#nameErro").text("用户名不能为空!");
	      	isSubmit = isSubmit && false;
	      }else
	      {
	      	$("#nameErro").text("");
	      }
	      var password = $("#password").val();
	      if(password == '')
	      {
	      	$("#pwdErro").text("密码不能为空!");
	      	isSubmit = isSubmit && false;
	      }else
	      {
	        $("#pwdErro").text("");
	      }
	      var code = $("#code").val();
	      if(code == '')
	      {
	      	$("#codeErro").text("验证码不能为空!");
	      	isSubmit = isSubmit && false;
	      }else
	      {
	        $("#codeErro").text("");
	      }
	      return isSubmit;
	      
	    });
		$("#login_ok").click(function() {
		//触发表单提交方法
			$("form:first").submit();
		});
	});
	function MM_swapImage(srcObj,image_src){
		srcObj.src=image_src;
	}
		function change()
	{
		var img = document.getElementById("captchaImage");
		img.src = "${pageContext.request.contextPath }/checkImg.action?test="+ new Date().getTime();
	}
	
		
</script>
</head>
<body>
	<div class="container-login">
		<div class="login-pic">
			<div class="login-text">
				<s:form action="emp_login" method="post" >
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="15%" height="28">用户名：</td>
							<td colspan="2">
								<s:textfield id="username" name="em.username" size="15" value="admin"></s:textfield>
							</td>
					<td width="150px"><span id="nameErro" style="color:red"><s:actionerror /><s:fielderror fieldName="em.username"></s:fielderror></span></td>
						 </tr>
						 <tr>
							<td height="28">密&nbsp;&nbsp;码：</td>
							<td colspan="2">
							    <s:password id="password" name="em.password" size="15" value="admin" showPassword="true"></s:password>
							</td>
			<td width="150px"><span id="pwdErro" style="color:red"><s:fielderror fieldName="em.password"></s:fielderror></span></td>
							
						</tr>
						<tr>
						  <tr>
							 <td height="30	">验证码：</td>
							<td width="10%">
								  <input type="text" id="code"  name="checkCode" size="4" autocomplete="off">
<!-- 								<input type="text" size="6" /> -->
							</td>
							<td width="10%">

<%--  										<span class="fieldSet">  --%>
											<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath }/checkImg.action" onclick="change()" title="点击更换验证码">
<%-- 										</span>  --%>
							</td>
							<td width="150px"><span id="codeErro" style="color:red"><s:actionmessage /></span></td>
						</tr>
						<tr>
							<td height="30">&nbsp;</td>
							<td colspan="2">
								<a href="javascript:void(0)" id="login_ok">
									<img src="${pageContext.request.contextPath }/images/denglu_bg_03.gif" 
										 name="Image1" width="40"	
										 height="22" border="0"  
										 onmouseover="MM_swapImage(this,'${pageContext.request.contextPath }/images/denglu_h_03.gif')" 
										 onmouseout="MM_swapImage(this,'${pageContext.request.contextPath }/images/denglu_bg_03.gif')" /></a>
								<a href="#">
									<img src="${pageContext.request.contextPath }/images/giveup_bg_03.gif" 
										 name="Image2" width="42" 
										 height="22" border="0"  
										 onmouseover="MM_swapImage(this,'${pageContext.request.contextPath }/images/giveup_h_03.gif')" 
										 onmouseout="MM_swapImage(this,'${pageContext.request.contextPath }/images/giveup_bg_03.gif')" /></a>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>
