<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkUser.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>${user.nickname }_个人中心_青鸟视频</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">

  	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/adminHeader.js"></script>
  	<link href="css/userInfo.css" rel="stylesheet" type="text/css" />
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
    <div id="userZone">
    	<!-- 头像 -->
    	<div id="pic">
	 		<img width="150px" height="156px"
	 			align="middle" title="${user.nickname }" alt="${user.nickname }"
	 			src="upload/userPicture/${user.picturepath }"/>
	 	</div>
	 	<!-- 信息展示 -->
	 	<div id="MessageInfo">
	 		<table width="800px" id="messTable">
	 			<tr class="mess_tr">
	 				<td id="title" colspan="3">个人中心</td>
	 			</tr>
	 			<tr class="mess_tr">
	 				<td width="25%"></td>
	 				<td class="leftMessage" width="12%">用户名:</td>
	 				<td>${user.username }</td>
	 			</tr>
	 			<tr class="mess_tr">
	 				<td width="25%"></td>
	 				<td class="leftMessage" width="12%">昵称:</td>
	 				<td>${user.nickname }</td>
	 			</tr>
	 			<tr class="mess_tr">
	 				<td width="25%"></td>
	 				<td class="leftMessage" width="12%">生日:</td>
	 				<td>${user.birthday }</td>
	 			</tr>
	 			<tr class="mess_tr">
	 				<td width="25%"></td>
	 				<td class="leftMessage" width="12%">电话:</td>
	 				<td>${user.phone }</td>
	 			</tr>
	 			<tr class="mess_tr">
	 				<td width="25%"></td>
	 				<td class="leftMessage" width="12%">地址:</td>
	 				<td>${user.adress }</td>
	 			</tr>
	 			<tr class="mess_tr" id="last_tr">
	 				<td width="25%"></td>
	 				<td class="leftMessage" width="16%">用户积分:</td>
	 				<td>${user.score }</td>
	 			</tr>
	 		</table>
	 	</div>
	 	<!-- 用户操作区域 -->
	 	<div id="userCaoZuo">
	 		<ul id="czList">
	 			<li id="firstList">
			 		<p>用户操作</p>
			 		<hr/>
	 			</li>
	 			<li>
	 				<a title="修改密码" href="updatePassword.jsp">修改密码</a>
	 				<hr/>
	 			</li>
	 			<li>
			 		<a title="修改个人信息" href="UserServlet?op=getUserByupdateUser">修改个人信息</a>
	 			</li>
	 		</ul>
	 	</div>
	</div>
   <!-- 底部开始 -->
    <div id="footer" style="margin-top: 480px;">
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
