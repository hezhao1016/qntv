<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--如果已经登录，就根据用户身份跳回相应页面 --%>
<c:if test="${not empty user}">
	<c:if test="${user.isadmin==0 }">
		<script type="text/javascript">
			location = "VideoServlet?op=getVideoFromTimeAndCount";
		</script>
	</c:if>
	<c:if test="${user.isadmin==1 }">
		<script type="text/javascript">
			location = "VideoServlet?op=showVideoListForAdmin";
		</script>
	</c:if>
</c:if>
<html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0064)http://www.17sucai.com/preview/137615/2015-01-15/demo/index.html -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><META content="IE=11.0000" http-equiv="X-UA-Compatible">
 <head>
	<TITLE>用户登录_青鸟视频</TITLE> 
	<META name="GENERATOR" content="MSHTML 11.00.9600.17496"></HEAD> 
	<META http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	
	<link href="css/login.css" rel="stylesheet" type="text/css" />
	<SCRIPT src="js/jquery-1.8.3.js" type="text/javascript"></SCRIPT>
	<SCRIPT type="text/javascript" src="js/login.js"></SCRIPT>
</head>
<BODY>

<DIV class="top_div"></DIV>
	<DIV id="login_1">
	<DIV id="login_2">
		<DIV class="tou"></DIV>
		<DIV class="initial_left_hand" id="left_hand"></DIV>
		<DIV class="initial_right_hand" id="right_hand"></DIV>
	</DIV>
		<form id="myform" action="UserServlet?op=checkLogin" method="post">          
			<P id="p_1">
				<SPAN class="u_logo"></SPAN> 
				<INPUT class="ipt" id="username" name="username" type="text" placeholder="请输入用户名" value="">
			</P>
			<P id="p_2">
				<SPAN class="p_logo"></SPAN>         
				<INPUT class="ipt" id="password" name="password" type="password" placeholder="请输入密码" value=""><span></span>
			</P>
			<p id="p_3">
				  <SPAN class="p_logo" id="c_cogo"></SPAN> 
		 	 	  <input class="ipt" id="code" type="text" placeholder="请输入验证码"name="code"/>
		 	 	  <img src ='images.jsp' id="code_pic"/> 
			</p>
			<DIV id="login_3">
				<P id="p_4">
					<SPAN id="span_1">
						<A id="wjpwd" href="javascript:getMi();">忘记密码?</A>
					</SPAN> 
			        <SPAN id="span_2">
			        	<A id="zhuce" href="UserServlet?op=zhuce">注册</A>  
			            <A id="loginBtn" href="javascript:Login();">登录</A> 
	      		    </SPAN>
	              </P>
           </DIV>
     </form>
    </DIV>
 </BODY>
</HTML>