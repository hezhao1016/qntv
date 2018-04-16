<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkUser.jsp" %>
<c:if test="${user.isadmin!=1}">
	<script type="text/javascript">
		history.back();
	</script>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加视频类型_青鸟视频</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
  	
  	<link href="css/videoClass.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/videoClass.js"></script>
  	<script type="text/javascript" src="js/adminHeader.js"></script>
  
  </head>
  
  <body>
  <div id="max">
  	<!-- 顶部导航栏 -->
  	<div id="header">
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
  	</div>
  	<div id="t_title_Zone">
   			<p id="title" align="center">添加电影类型<br></p>
   	</div> 
  	<!-- 主体部分 -->
     <div class="show" style="height:260px;">
    	<form id="myform" action="VideoClassServlet?op=addVideoClass" method="post">
    		<table width="700px">
    			<tr>
    				<td width="60%">
    					类型名称：<input type="text" name="classname" id="classname" style="width:300px;"/>
    				</td>
    				<td>
    					<div id="classname_pram"></div>
    				</td>
    			</tr>
    		</table>
    		<p id="bott">
	    		<input class="btn" type="submit" value="添加"/>
	    		<input class="btn" type="reset" value="重置"/>
    		</p>
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
