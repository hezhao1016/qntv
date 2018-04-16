<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkUser.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>收藏视频_青鸟视频</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	
	<link href="css/userCollectList.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/adminHeader.js"></script>
 	<script type="text/javascript" src="js/userCollectList.js"></script>
	
  </head>
  
 <body>
 	<div id="max">
  	<!-- 顶部导航栏 -->
  	<div id="header" style="margin-bottom: 0px;">
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
    <div class="show">
    <div id="videoShowList">
    	<form id="myform" action="UserCollectServlet?op=delCheckedCollects" method="post">
    	<table width="1100px" style="margin-bottom: 10px;margin-left: -66.5px;">
    		<!-- 菜单 -->
    		<tr height="70px" id="menu">
    			<td width="100px" id="all_td">
    				<label><input type="checkbox" name="all" id="all" title="全选" onclick="check();"/>全选</label>
    			</td>
    			<td width="100px" id="ck_td">
	    			<a id="delCKCollects" style="font-size: 16px;cursor:pointer" title="删除多个">删除多个</a>
    			</td>
    			<td id="quxiao" width="100px">
	    			<a style="font-size: 16px;cursor:pointer" title="取消删除">取消</a>
    			</td>
    			<td width="100px" id="clearAll_td">
			    	<a id="delAll" style="font-size: 16px;" href="javascript:delAllCollects();" title="清空收藏列表">清空收藏</a>
    			</td>
    			<td>${user.nickname}的收藏视频</td>
    		</tr>
    	</table>
    	<c:forEach items="${collectList}" var="c">
    		<ul class="movie_ul">
            	<li class="m_pic">
                    <input type="checkbox" class="collect" name="collect" title="选择《${c.video.videoname }》" value="${c.collectid }" onclick="checkBox();"/>
                    <a class="pic_a" target="_blank">
                        <img src="upload/picture/${c.video.picturepath }" width="175px" height="245px" title="${c.video.title }" alt="${c.video.videoname }"/>	
                    </a>
                    <input type="hidden" value="${c.video.videoid }"/>
                </li>
                <li class="m_name">
                	<a href="VideoServlet?videoid=${c.video.videoid }&op=playVideo" target="_blank">${c.video.videoname }</a>
                </li>
                <li class="m_jieshao">
                	<span class="m_gray">导演:</span>${c.video.director }
                </li>
                 <li class="m_jieshao" title="${c.video.actor }">
                	  <span class="m_gray">主演:</span>${c.video.actor }
                </li>
                 <li class="m_date">
                 	  <span class="m_time">播放量:${c.video.playcount}</span>
                	  <span class="m_del"><a title="删除《${c.video.videoname }》" href="javascript:delCollect(${c.collectid});">删除收藏</a></span>
                </li>
            </ul>
    	</c:forEach>
    	</form>
    	<div id="empty">
    		<p><a href="VideoServlet?op=getVideoFromTimeAndCount">暂时没有收藏电影，最新最全的电影尽在青鸟视频！</a></p>
    	</div>
    	</div>
	   		<c:if test="${totalPages>1 }">
	   			<div id="pageZone">
    			<ul>
	           	<c:if test="${pageIndex>1 }">
	            	<li><a href="UserCollectServlet?op=showCollectVideoList&pageIndex=1">首页</a></li>
	            	<li><a href="UserCollectServlet?op=showCollectVideoList&pageIndex=${pageIndex-1 }">上页</a></li>
	           	</c:if> 
	           	<c:if test="${pageIndex<=1 }">
	           		<li>首页</li>
	            	<li>上一页</li>
	           	</c:if> 
	           <select id="SelectIndex" onchange="changeIndex();">
					<c:forEach var="i" varStatus="status" begin="1" end="${totalPages}">
	           			<option 
	           				<c:if test="${status.index==pageIndex}">
	           					selected="selected"
	           				</c:if> 
	           			value="${status.index }">第${status.index}页</option>
					</c:forEach>
          	 	</select>
	           	<c:if test="${pageIndex!=totalPages }">
	            	<li><a href="UserCollectServlet?op=showCollectVideoList&pageIndex=${pageIndex+1 }">下页</a></li>
	            	<li><a href="UserCollectServlet?op=showCollectVideoList&pageIndex=${totalPages }">尾页</a></li>
				</c:if>
				<c:if test="${pageIndex==totalPages }">
	           		<li>下页</li>
	            	<li>尾页</li>
	           	</c:if>
	          	 <li class="pageinfo" style="width:40px;">第${pageIndex }页</li>
   			   	 <li class="pageinfo" style="width:40px;">共${totalPages }页</li>
	         </ul>
	       </div>
	    </c:if>
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
