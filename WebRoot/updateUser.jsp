<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="checkUser.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改用户信息_青鸟视频</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	
  	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<link href="css/updateUser.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/adminHeader.js"></script>
	<script type="text/javascript" src="js/updateUser.js"></script> 
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
  	<!-- 顶部导航栏 -->
  	<div id="header">
  		<%--根据是否是管理员.显示不同的导航栏 --%>
  		<%--普通用户 --%>
  		<c:if test="${user.isadmin==0 }">
	  		<style type="text/css">
				#header li{width:155px;}
				#header #checkout{width:155px;}
			</style>
	  		<ul>
	  			<li id="headerOne" title="青鸟视频"><a href="VideoServlet?op=getVideoFromTimeAndCount">青鸟视频</a></li>
	  			<li title="个人中心"><a href="UserServlet?op=showUserInfo">个人中心</a></li>
	  			<li title="电影排行榜"><a href="VideoServlet?op=top50&time=1=1&order=playcount%20desc">电影排行榜</a></li>
	  			<li title="用户收藏"><a href="UserCollectServlet?op=showCollectVideoList" >收藏视频</a></li>
	  			<li title="观看记录"><a href="VideoNoteServlet?op=showVideoNoteList">观看记录</a></li>
	  			<li id="checkout" title="退出"><a href="UserServlet?op=checkOut">退出</a></li>
	  		</ul>
  		</c:if>
  		<%--管理员 --%>
  		<c:if test="${user.isadmin==1 }">
	  		<ul>
	  			<li id="headerOne" title="青鸟视频"><a href="VideoServlet?op=getVideoFromTimeAndCount">青鸟视频</a></li>
	  			<li title="个人中心"><a href="UserServlet?op=showUserInfo">个人中心</a></li>
	  			<li title="电影排行榜"><a href="VideoServlet?op=top50&time=1=1&order=playcount%20desc">电影排行榜</a></li>
	  			<li title="管理员首页"><a href="VideoServlet?op=showVideoListForAdmin">管理员首页</a></li>
	  			<li title="上传视频"><a href="VideoClassServlet?op=showVideoClassForUploadVideo">上传视频</a></li>
	  			<li title="电影类型管理"><a href="VideoClassServlet?op=showVideoClassForVideoClassList">类型管理</a></li>
	  			<li title="添加电影类型"><a href="addVideoClass.jsp">添加类型</a></li>
	  			<li title="用户收藏"><a href="UserCollectServlet?op=showCollectVideoList" >用户收藏</a></li>
	  			<li title="观看记录"><a href="VideoNoteServlet?op=showVideoNoteList">观看记录</a></li>
	  			<li id="checkout" title="退出"><a href="UserServlet?op=checkOut">退出</a></li>
  			</ul>
  		</c:if>
  	</div>
  	<!-- 主体部分 -->
     <div class="show">
   		<div id="t_title_Zone">
   			<p id="title" align="center">修改用户信息<br></p>
   		</div> 
    	<form name="reg" action="UserServlet?op=updateUser" method="post" target="_self" onSubmit="return check()" enctype="multipart/form-data"> 
	<table id="uuTable" width="880px"> 
	<tr>
		<td width="10%" align="right" height="25">昵称:</td> 
		<td width="30%" align="left" height="25"> <input type="text" id="nickname" name="nickname" onblur="checkNickName()" onfocus="showNickName()" value="${user.nickname }"></td> 
		<td  width="60%"><div id="nickname_prompt"></div></td>
	</tr>
	<tr> 
		<td width="10%" align="right" height="25">出生日期:</td> 
		<td width="30%" align="left" height="25"> <input id="birth" type="text" name="birthday" onblur="checkBirth()" onfocus="showBirth()" value="${user.birthday }"></td> 
		<td  width="60%"><div id="birth_prompt"></div></td>
	</tr> 
	<tr> 
		<td width="10%" align="right" height="25">联系方式:</td> 
		<td width="30%" align="left" height="25"> <input id="mobile" type="text" name="phone" onblur="checkMobile()"  onfocus="showMoblie()" value="${user.phone }"></td> 
		<td  width="60%"> <div id="mobile_prompt"></div></td>
	</tr> 
	<tr> 
		<td width="10%" align="right" height="25">地址:</td> 
		<td width="30%" align="left" height="25"> <input type="text" name="adress" id="adress" onblur="checkAdress()" onfocus="showAdress()" value="${user.adress }"></td> 
		<td  width="60%"> <div id="adress_prompt"></div></td>
	</tr> 
	<tr>
		<td width="30%" align="right" height="25">选择头像:</td>
		<td width="70%" align="left" height="25" colspan="2"> <input type="file" name="picture"></td>
	</tr>
	<tr> 
		<td width="30%" align="right" height="25">密保问题:</td> 
		<td width="60%" align="left" height="25" colspan="2"> 
		<select name="pwdid">
	  		<c:forEach items="${pwdlist}" var="p">
	  		<option value="${p.pwdid }"
	  			 <c:if test="${p.pwdid==user.pwdid }">
	  			 	selected="selected"
	  			 </c:if> 
	  			 >${p.pwdname }</option>
	  		</c:forEach>
	  	</select></td> 
	</tr> 
	<tr>
		<td width="10%" align="right" height="25">密保答案:</td>
		<td width="30%" align="left" height="25">
			<input type="password" name="answer" id="answer"  onblur="checkAnswer()" onfocus="showAnswer()" value="${user.answer }">
		</td>
		<td  width="60%"> <div id="answer_prompt"></div></td>
	</tr>
	<tr>
		<td id="sub" colspan="3" style="line-height: 40px;text-align: center;">
			<input type="hidden" name="picturepath"  value="${user.picturepath }">
			<p id="b_user"> 
				<input class="btn" type="submit" value="修改"/>
	    		<input class="btn" type="reset" value="重置"/>
			</p> 
		</td>
	</tr>
	</table> 
	</form> 
    </div>
   <!-- 底部开始 -->
    <div id="footer" style="margin-top: 350px;">
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


