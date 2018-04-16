<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户注册_青鸟视频</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<link href="css/updateUser.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/zhuCe.js"></script> 
	<style type="text/css">
		.text{border:1px red solid;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$(".btn").mouseover(function(){
				$(this).css("border-color","red");
				$(this).css("color","red");
			}).mouseout(function(){
				$(this).css("border-color","green");
				$(this).css("color","black");
			});
		});
	</script>
  </head>
  
  <body>
<div id="max">
  	<!-- 主体部分 -->
     <div class="show" style="margin-bottom: 250px;margin-top: -20px;">
   		<div id="t_title_Zone" style="margin-top:0px;">
   			<p id="title" align="center">青鸟视频用户注册<br></p>
   		</div> 
    	<form name="reg" action="UserServlet?op=addUser" method="post" target="_self" onSubmit="return check();" enctype="multipart/form-data"> 
		<table id="uuTable" width="800px" style="margin-left: 33%;"> 
		<tr  height="25"> 
			<td width="13%"  align="right"><font face="Arial, Helvetica, sans-serif">用户名:</font></td> 
			<td  width="30%" align="left" > 
	 		<input  id="userid" type="text" name="username" onblur="checkUser()" onfocus="showUser()"> </td>
			<td width="57%"><P id="user_prompt"> </P></td>
		</tr> 
		<tr> 
			<td width="13%" align="right" height="25" name="password">密码:</td> 
			<td width="30%" align="left" height="25"> <input id="pwd" type="password" name="password" onblur="checkPwd()" onfocus="showPwd()"></td> 
		    <td  width="57%"><div id="pwd_prompt"></div></td>
		</tr> 
		<tr> 
			<td width="13%" align="right" height="25" >确认密码:</td> 
			<td width="30%" align="left" height="25"> <input id="repwd" type="password" name="userpassword" onblur="checkRepwd()" onfocus="showRePwd()"></td> 
			<td  width="57%"><div id="repwd_prompt"></div></td>
		</tr> 
	<tr>
		<td width="13%" align="right" height="25">昵称:</td> 
		<td width="30%" align="left" height="25"> <input type="text" id="nickname" name="nickname" onblur="checkNickName()" onfocus="showNickName()" value="${user.nickname }"></td> 
		<td  width="57%"><div id="nickname_prompt"></div></td>
	</tr>
	<tr> 
		<td width="13%" align="right" height="25">出生日期:</td> 
		<td width="30%" align="left" height="25"> <input id="birth" type="text" name="birthday" onblur="checkBirth()" onfocus="showBirth()" value="${user.birthday }"></td> 
		<td  width="57%"><div id="birth_prompt"></div></td>
	</tr> 
	<tr> 
		<td width="13%" align="right" height="25">联系方式:</td> 
		<td width="30%" align="left" height="25"> <input id="mobile" type="text" name="phone" onblur="checkMobile()"  onfocus="showMoblie()" value="${user.phone }"></td> 
		<td  width="57%"> <div id="mobile_prompt"></div></td>
	</tr> 
	<tr> 
		<td width="13%" align="right" height="25">地址:</td> 
		<td width="30%" align="left" height="25"> <input type="text" name="adress" id="adress" onblur="checkAdress()" onfocus="showAdress()" value="${user.adress }"></td> 
		<td  width="57%"> <div id="adress_prompt"></div></td>
	</tr> 
	<tr>
		<td width="13%" align="right" height="25">选择头像:</td>
		<td width="87%" align="left" height="25" colspan="2"> <input type="file" name="picture"></td>
	</tr>
	<tr> 
		<td width="13%" align="right" height="25">密保问题:</td> 
		<td width="87%" align="left" height="25" colspan="2"> 
		<select name="pwdid">
	  		<c:forEach items="${pwdlist}" var="p">
	  		<option value="${p.pwdid }">${p.pwdname }</option>
	  		</c:forEach>
	  	</select></td> 
	</tr> 
	<tr>
		<td width="13%" align="right" height="25">密保答案:</td>
		<td width="30%" align="left" height="25">
			<input type="password" name="answer" id="answer"  onblur="checkAnswer()" onfocus="showAnswer()" value="${user.answer }">
		</td>
		<td  width="57%"> <div id="answer_prompt"></div></td>
	</tr>
	<tr>
		<td id="sub" colspan="3" style="line-height: 40px;text-align: left;">
			<p id="b_user" style="margin-left: 16%;"> 
				<input class="btn" type="submit" value="注册"/>
	    		<input class="btn" type="reset" value="重置"/>
			</p> 
		</td>
	</tr>
	</table> 
	</form> 
    </div>
   <!-- 底部开始 -->
    <div id="footer">
		<div id="top">
	       <a target="_blank" href="VideoServlet?op=getVideoFromTimeAndCount">首页</a>|
	       <a target="_blank" href="http://kf.qq.com/">客户服务</a>|
	       <a target="_blank" href="http://www.youku.com/">品牌合作</a>|
	       <a target="_blank" href="http://www.bdqn.cn/">网站联盟</a>|
	       <a target="_blank" href="about.jsp">关于我们</a>
       	</div>
    	<p>Copyright&nbsp;&copy;&nbsp;2015&nbsp;www.qiaoniaotv.com&nbsp;All&nbsp;Rights&nbsp;Reserved京公网安备110105005926</p>
	</div>
	</div>
  </body>
</html>



