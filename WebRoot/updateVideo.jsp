<%@page import="com.qntv.entity.Video"%>
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
    <title>修改视频信息_青鸟视频</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
  
 	<link href="css/editVideo.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/updateVideo.js"></script>
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
   			<p id="title1" align="center">修改电影信息<br></p>
   	</div> 
  	<!-- 主体部分 -->
    <div id="editzone">
    <form id="myform" action="VideoServlet?op=updateVideo" method="post" enctype="multipart/form-data">
    	<table width="700px">
    			<tr>
    				<td width="12%">电影名称:</td>
    				<td width="60%">
    					<input type="text" name="videoname" id="videoname" style="width:350px;" value="${video.videoname}"/>
    				</td>
    				<td>
    					<div id="videoname_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">电影标题:</td>
    				<td width="60%">
    					<input type="text" name="title" id="title" style="width:350px;" value="${video.title}"/>
    				</td>
    				<td>
    					<div id="title_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">导演:</td>
    				<td width="60%">
    					<input type="text" name="director" id="director" style="width:350px;" value="${video.director}"/>
    				</td>
    				<td>
    					<div id="director_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">主演:</td>
    				<td width="60%">
    					<input type="text" name="actor" id="actor" style="width:350px;" value="${video.actor}"/>
    				</td>
    				<td>
    					<div id="actor_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">文件大小:</td>
    				<td width="60%">
    					<input type="text" name="size" id="size" style="width:350px;" value="${video.size}"/>
    				</td>
    				<td>
    					<div id="size_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">电影封面:</td>
    				<td width="60%">
    					<input type="file" name="picturefile" id="picturefile" style="width:350px;"/>
    				</td>
    				<td>
    					<div id="picturefile_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">视频文件:</td>
    				<td width="60%">
    					<input type="file" name="videofile" id="videofile" style="width:350px;"/>
    				</td>
    				<td>
    					<div id="videofile_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">类型:</td>
    				<td width="60%">
    					<select name="videoclassid" id="videoclassid" style="width:150px;">
			    			<c:forEach items="${videoClassList}" var="vc">
				    			<option value="${vc.classid }" 
				    				<c:if test="${vc.classid==video.videoclassid }">
				    					selected="selected"
				    				</c:if>
				    			>${vc.classname}</option>
				    		</c:forEach>
			    		</select>
    				</td>
    				<td>
    					<div id="videoclassid_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">地区:</td>
    				<td width="60%">
    					<select name="zone" id="zone" style="width:150px;">
			    			<option <c:if test="${video.zone=='内地' }">selected="selected"</c:if> value="内地">内地</option>
			    			<option <c:if test="${video.zone=='香港' }">selected="selected"</c:if> value="香港">香港</option>
			    			<option <c:if test="${video.zone=='台湾'}">selected="selected"</c:if> value="台湾">台湾</option>
			    			<option <c:if test="${video.zone=='日本'}">selected="selected"</c:if> value="日本">日本</option>
			    			<option <c:if test="${video.zone=='韩国'}">selected="selected"</c:if> value="韩国">韩国</option>
			    			<option <c:if test="${video.zone=='美国'}">selected="selected"</c:if> value="美国">美国</option>
			    			<option <c:if test="${video.zone=='欧洲' }">selected="selected"</c:if> value="欧洲">欧洲</option>
			    		</select>
    				</td>
    				<td>
    					<div id="zone_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td width="12%">年代:</td>
    				<td width="60%">
    					<select name="years" id="years" style="width:150px;">
			    			<%
								int year = (Integer)request.getAttribute("year");
			    				int years =Integer.parseInt(((Video)request.getAttribute("video")).getYears());
								for(int i=year;i>=1900;i--){
							%>
								<option 
					  				<%if(i==years){ %>
					  					selected="selected"
					  				<%} %>
				  					value="<%=i%>"><%=i%></option>
				    		<%} %>
			    		</select>
    				</td>
    				<td>
    					<div id="years_pram"></div>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="3">电影描述:</td>
    			</tr>
    			<tr>
    				<td colspan="2" width="60%">
    					<textarea rows="10%" cols="60%" name="describe" id="describe"> ${video.describe}</textarea>
    				</td>
    				<td>
    					<div id="describe_pram"></div>
    				</td>
    			</tr>
    	</table>
    	<input type="hidden" name="videoid" id="videoid" value="${video.videoid}"/>
    	<input type="hidden" name="picturepath" id="picturepath" value="${video.picturepath}"/>
    	<input type="hidden" name="videopath" id="videopath" value="${video.videopath}"/>
    	<p id="editBottom">
    		<input class="btn" type="submit" value="修改"/>
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
