<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		//全选
		$("#all").click(function() {
			var flag=$(this).attr("checked");
			$("[name=roleIds]").attr("checked",flag=="checked");
		});
		//反选
		$("#reverse").click(function(){
			$("[name=roleIds]").each(function(){
				var flag = $(this).attr("checked");
				$(this).attr("checked",!(flag == "checked"));
			});
			checkedSelect();
		});
		//组件绑定事件
		$("[name=roleIds]").click(function(){
		checkedSelect();
		});
		
		function checkedSelect(){
			var allFlag=true;
			$("[name=roleIds]").each(function(){
				var flag=$(this).attr("checked")=="checked";
				allFlag=allFlag&&flag;
			});
			$("#all").attr("checked",allFlag);
		}
		
		
 		/* $("#all").click(function() {
 			$("[name=roleIds]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roleIds]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
             });
 		});  */
		
		//对比密码
		$("#password2").blur(function(){
		var pwd1=$("#password1").val();
		var pwd2=$("#password2").val();
			if(pwd2!=pwd1){
				alert("输入密码不一致");
			}
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="emp_save.action" method="post"> 
			<s:hidden name="em.id"></s:hidden>
			<s:hidden name="pageNum"></s:hidden>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">用&nbsp;户&nbsp;名</td>
				      <td width="32%">
				      	<s:textfield name="em.username" size="25"></s:textfield>
				      </td>
				      <td width="18%"align="center">真实姓名</td>
				      <td width="32%">
				      	<s:textfield name="em.name" size="25"></s:textfield>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
					<s:if test="em.id==null">
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
				      	<s:textfield id="password1" name="em.password" size="25"></s:textfield>
				      </td>
				      <td  align="center">确认密码</td>
				      <td >
				      <s:textfield id="password2" size="25"></s:textfield>
				      </td>
				    </tr>
				    </s:if>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
				      	<s:textfield name="em.email" size="25"></s:textfield>
				      <td align="center">电话号码</td>
				      <td>
				      	<s:textfield name="em.tele" size="25"></s:textfield>
					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
				      	<s:select name="em.gender" list="@com.xinboiedu.erp.auth.emp.vo.EmpModel@GENDER_MAP"></s:select>
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
				      	<s:textfield name="em.address" size="25"></s:textfield>
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				      	<input type="text" value="${em.birthdayView }" size="25" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
				      	<s:hidden name="em.birthday"></s:hidden>
					  </td>
				      <td align="center">所属部门</td>
				      <td>
						<s:select name="em.dep.id" list="#request.depList" listKey="id" listValue="name"></s:select>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				     	<s:checkboxlist name="roleIds" list="roleList" listKey="id" listValue="name"></s:checkboxlist>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="${pageContext.request.contextPath }/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath }/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="javascript:history.back()"><img src="${pageContext.request.contextPath }/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</s:form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${pageContext.request.contextPath }/images/content_bbg.jpg" /></div>
</div>
