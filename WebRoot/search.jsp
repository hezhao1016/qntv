<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>电影搜索_青鸟视频</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">

	<LINK href="index/base.css" rel="stylesheet">
	<LINK href="index/channel.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" />
	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/adminHeader.js"></script>
	<style>
		body{width:100%;}
		#sshow{margin:20px 100px;width: 80%;}
		table{background-color:rgba(199, 199, 199, 1);margin:20px 0px;}
		td{font-size: 18px;}
		img{width:200px;height:250px;padding:20px;}
		#na{padding-top: 20px;font-size: 29px;}
		#content{width:1143px;}
		span{color:rgb(136, 136, 136);}
		.wtd{width:820px;}
		.rtd{text-align: right;}
		#gx{text-indent: 60px;}
	</style>
  </head>
  <body>
<%--如果已经登录，就显示顶栏 --%>
<c:if test="${not empty user}">
  <!-- 顶部导航栏 -->
  	<div id="header" style="margin-top: 0px;z-index: 1;margin-bottom: 0px;">
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
</c:if>
  	<div id="sshow">
	<div id="b" style="min-height: 500px;">
	<c:if test="${empty videos}">
		<p>抱歉,没有找到相关信息</p>
	</c:if>
	<c:forEach items="${videos }" var="v">
		<table id="content">
			<tr>
				<td rowspan="6">
					<a target="_blank"  href="VideoServlet?videoid=${v.videoid }&op=playVideo">
						<img src="upload/picture/${v.picturepath}"/>
					</a>
				</td>
			</tr>
			<tr>
				<td colspan="3" id="na" valign="top">
					<a target="_blank"  href="VideoServlet?videoid=${v.videoid }&op=playVideo">${v.videoname }
					</a>
				</td>
			</tr>
			<tr>
				<td class="rtd" valign="top">导演：</td>
				<td class="wtd" valign="top">${v.director }</td>
			</tr>
			<tr>
				<td class="rtd" valign="top">主演：</td>
				<td class="wtd" valign="top">${v.actor}</td>
			</tr>
			<tr>
				<td class="rtd" valign="top">简介：</td>
				<td class="wtd" valign="top">${v.describe }</td>
			</tr>
			<%--如果已经登录，就显示收藏链接 --%>
			<c:if test="${not empty user}">
			<tr>
				<td class="rtd" valign="top" style="line-height: 40px;text-align: left;" >
					<%--如果已经收藏 --%>
					<c:if test="${v.iscollect==false }">
						&nbsp;<a style="color:red" href="UserCollectServlet?op=addCollectVideo&videoid=${v.videoid}&videoname=${param.videoname}">收藏本片</a>
					</c:if>
					<%--如果没有收藏 --%>
					<c:if test="${v.iscollect==true }">
						&nbsp;已收藏
					</c:if>
				</td>
			</tr>
			</c:if>
		</table>
		</c:forEach>
	</div>
	<DIV class="mod_footer"  style="margin-left:13.5%;">
		<DIV class="footermenu">
			<A href="about.jsp" target="_blank"
				rel="nofollow">关于我们</A> | <A
				href="about.jsp" target="_blank"
				rel="nofollow">About QingNiao</A> | <A
				href="http://www.qq.com/contract.shtml" target="_blank"
				rel="nofollow">服务条款</A> | <A href="http://adver.qq.com/"
				target="_blank" rel="nofollow">广告服务</A> | <A
				href="http://hr.tencent.com/" target="_blank" rel="nofollow">青鸟招聘</A>
			| <A href="http://service.qq.com/" target="_blank"
				rel="nofollow">客服中心</A> | <A href="http://www.qq.com/map/"
				target="_blank" rel="nofollow">网站导航</A>
		</DIV>
		<DIV class="copyrighten">
			Copyright © 2015 - <SPAN> <SCRIPT>
				document
						.write(new Date().getFullYear());
			</SCRIPT> </SPAN> QingNiao. <A
				href="http://www.tencent.com/en-us/le/copyrightstatement.shtml"
				target="_blank" rel="nofollow">All Rights Reserved.</A>
		</DIV>
		<DIV class="copyrightzh">
			青鸟公司 <A
				href="http://www.bdqn.cn/"
				target="_blank" rel="nofollow">版权所有</A> <A
				href="http://www.bdqn.cn/page/gsjj.shtml"
				target="_blank" rel="nofollow">青鸟网络文化经营许可证</A>
		</DIV>
	</DIV>
	</div>
  </body>
</html>
