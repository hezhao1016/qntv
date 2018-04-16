<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}">
	<script type="text/javascript">
		alert("您还没有登录，请先登录！");
		location = "login.jsp";
	</script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${video.videoname }_高清在线观看_青鸟视频</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8;">
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
  	<script type="text/javascript">
  	function headListOver(i){
		var lis = document.getElementsByClassName("hli");
		lis[i].style.backgroundColor = "gray";
	}
	function checkOutOver(){
		var ck = document.getElementById("checkout");
		ck.style.backgroundColor = "red";
	}
	function headListOut(i){
		var lis = document.getElementsByClassName("hli");
		lis[i].style.backgroundColor = "black";
	}
	function headListClick(i){
		var lis = document.getElementsByClassName("hli");
		location.href = lis[i].getElementsByTagName("a")[0].href;
	}
  	</script>
</head>
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
<body>
  <!-- 顶部导航栏 -->
  	<div id="header" style="margin-top: 0px;z-index: 1;margin-bottom: 0px;">
  		<%--根据是否是管理员.显示不同的导航栏 --%>
  		<%--普通用户 --%>
  		<c:if test="${user.isadmin==0 }">
	  		<style type="text/css">
				#header li{width:155px;}
				#header #checkout{width:155px;}
			</style>
	  		<ul id="headList">
	  			<li class="hli" id="headerOne" title="青鸟视频" onmouseover="headListOver(0);" onmouseout="headListOut(0);" onclick="headListClick(0);">
	  				<a href="VideoServlet?op=getVideoFromTimeAndCount">青鸟视频</a>
	  			</li>
	  			<li class="hli" title="个人中心" onmouseover="headListOver(1);" onmouseout="headListOut(1);" onclick="headListClick(1);">
	  				<a href="UserServlet?op=showUserInfo">个人中心</a>
	  			</li>
	  			<li class="hli" title="电影排行榜" onmouseover="headListOver(2);" onmouseout="headListOut(2);" onclick="headListClick(2);">
	  				<a href="VideoServlet?op=top50&time=1=1&order=playcount%20desc">电影排行榜</a>
	  			</li>
	  			<li class="hli" title="用户收藏" onmouseover="headListOver(3);" onmouseout="headListOut(3);" onclick="headListClick(3);">
	  				<a href="UserCollectServlet?op=showCollectVideoList" >收藏视频</a>
	  			</li>
	  			<li class="hli" title="观看记录" onmouseover="headListOver(4);" onmouseout="headListOut(4);" onclick="headListClick(4);">
	  				<a href="VideoNoteServlet?op=showVideoNoteList">观看记录</a>
	  			</li>
	  			<li class="hli" id="checkout" title="退出" onmouseover="checkOutOver();" onmouseout="headListOut(5);" onclick="headListClick(5);">
	  				<a href="UserServlet?op=checkOut">退出</a>
	  			</li>
	  		</ul>
  		</c:if>
  		<%--管理员 --%>
  		<c:if test="${user.isadmin==1 }">
  			<ul id="headList">
	  			<li class="hli" id="headerOne" title="青鸟视频" onmouseover="headListOver(0);" onmouseout="headListOut(0);" onclick="headListClick(0);">
	  				<a href="VideoServlet?op=getVideoFromTimeAndCount">青鸟视频</a>
	  			</li>
	  			<li class="hli" title="个人中心" onmouseover="headListOver(1);" onmouseout="headListOut(1);" onclick="headListClick(1);">
	  				<a href="UserServlet?op=showUserInfo">个人中心</a>
	  			</li>
	  			<li class="hli" title="电影排行榜" onmouseover="headListOver(2);" onmouseout="headListOut(2);" onclick="headListClick(2);">
	  				<a href="VideoServlet?op=top50&time=1=1&order=playcount%20desc">电影排行榜</a>
	  			</li>
	  			<li class="hli"  title="管理员首页" onmouseover="headListOver(3);" onmouseout="headListOut(3);" onclick="headListClick(3);">
	  				<a href="VideoServlet?op=showVideoListForAdmin">管理员首页</a>
	  			</li>
	  			<li class="hli"  title="上传视频" onmouseover="headListOver(4);" onmouseout="headListOut(4);" onclick="headListClick(4);">
	  				<a href="VideoClassServlet?op=showVideoClassForUploadVideo">上传视频</a>
	  			</li>
	  			<li class="hli"  title="电影类型管理" onmouseover="headListOver(5);" onmouseout="headListOut(5);" onclick="headListClick(5);">
	  				<a href="VideoClassServlet?op=showVideoClassForVideoClassList">类型管理</a>
	  			</li>
	  			<li class="hli"  title="添加电影类型" onmouseover="headListOver(6);" onmouseout="headListOut(6);" onclick="headListClick(6);">
	  				<a href="addVideoClass.jsp">添加类型</a>
	  			</li>
	  			<li class="hli" title="用户收藏" onmouseover="headListOver(7);" onmouseout="headListOut(7);" onclick="headListClick(7);">
	  				<a href="UserCollectServlet?op=showCollectVideoList" >收藏视频</a>
	  			</li>
	  			<li class="hli" title="观看记录" onmouseover="headListOver(8);" onmouseout="headListOut(8);" onclick="headListClick(8);">
	  				<a href="VideoNoteServlet?op=showVideoNoteList">观看记录</a>
	  			</li>
	  			<li class="hli" id="checkout" title="退出" onmouseover="checkOutOver();" onmouseout="headListOut(9);" onclick="headListClick(9);">
	  				<a href="UserServlet?op=checkOut">退出</a>
	  			</li>
	  		</ul>
  		</c:if>
  	</div>

<div id="sshow">
	<div id="b">
		<h1 style="font-size: 30px;">${video.videoname}</h1>
		<h2 style="font-size: 20px;text-indent: 35px;margin-top: 15px;margin-bottom: 10px;">${video.title}</h2>
		<embed src="upload/video/${video.videopath }" width="1143px" height="700px" autostart="true"></embed>
		<table id="content">
			<tr>
				<td rowspan="6"><img src="upload/picture/${video.picturepath}"/></td>
			</tr>
			<tr style="line-height: 40px;">
				<td colspan="3" id="na" valign="top">${video.videoname }</td>
			</tr>
			<tr>
				<td class="rtd" valign="top">导演：</td>
				<td class="wtd" valign="top">${video.director }</td>
			</tr>
			<tr>
				<td class="rtd" valign="top">主演：</td>
				<td class="wtd" valign="top">${video.actor}</td>
			</tr>
			<tr>
				<td class="rtd" valign="top">简介：</td>
				<td class="wtd" valign="top">${video.describe }</td>
			</tr>
			<tr>
				<td class="rtd" valign="top" style="line-height: 40px;text-align: left;" >
					<%--如果已经收藏 --%>
					<c:if test="${isCollect==false }">
						&nbsp;<a style="color:red" href="UserCollectServlet?op=addCollectVideo&videoid=${video.videoid}">收藏本片</a>
					</c:if>
					<%--如果没有收藏 --%>
					<c:if test="${isCollect==true }">
						&nbsp;已收藏
					</c:if>
				</td>
				<td style="text-align: right;line-height: 40px;" valign="top" id="gx">
					更新于 ${video.createdate },来源于 ${video.uploadname }&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<DIV class="mod_footer" style="margin-left:13.5%;">
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