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
    <title>${user.nickname }_管理员页面_青鸟视频</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
  
  	<link href="css/admin.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/adminHeader.js"></script>
 	<script type="text/javascript" src="js/admin.js"></script>
 	
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
  	<!-- 主体部分 -->
    <div class="show">
    	<!-- 搜索栏开始 -->
    	<div class="searchzone">
    	<form id="searchform" action="VideoServlet?op=searchVideoByVideoClassId_Years_Zone_VideoName" method="post">
    		<p>
    			<label>电影类型：<select style="width:100px;" name="videoClass">
    						<option <c:if test="${videoclassid==-1}">selected="selected"</c:if> value="-1">请选择</option>
    						<c:forEach items="${videoClassList}" var="vc">
    							<option <c:if test="${videoclassid==vc.classid}">selected="selected"</c:if> value="${vc.classid }">${vc.classname}</option>
    						</c:forEach>
    					</select>
    			</label> 
    			<c:if test="${isSearch==false}">
    				&nbsp;&nbsp;<label>年代：<select name="years" style="width:96px;">
    						<option value="-1">请选择</option>
    						<script type="text/javascript">
								var year =parseInt(new Date().getFullYear());
			    				for(var i=year;i>=1900;i--){
			    					document.write("<option value="+i+">"+i+"</option>");
								} 			
    						</script>
    					</select>
    				</label>
    			</c:if>
    			<c:if test="${isSearch==true}">
    				&nbsp;&nbsp;<label>年代：<select name="years" style="width:96px;">
    						<option <c:if test="${years==-1}">selected="selected"</c:if> value="-1">请选择</option>
    						<%
    							int year = (Integer)request.getAttribute("year");
    							int years = (Integer)request.getAttribute("years");
    							for(int i=year;i>=1900;i--){
    						%>
    							<option 
				    				<%if(i==years){ %>
				    					selected="selected"
				    				<%} %>
				    				value="<%=i%>"><%=i%></option>
    						<%} %>
    					</select>
    				</label>
    			</c:if>
    			
    			&nbsp;&nbsp;<label>地区：<select name="zone" id="zone" style="width:96px;">
    					<option <c:if test="${zone=='请选择' }">selected="selected"</c:if> value="请选择">请选择</option>
		    			<option <c:if test="${zone=='内地' }">selected="selected"</c:if> value="内地">内地</option>
		    			<option <c:if test="${zone=='香港' }">selected="selected"</c:if> value="香港">香港</option>
		    			<option <c:if test="${zone=='台湾'}">selected="selected"</c:if> value="台湾">台湾</option>
		    			<option <c:if test="${zone=='日本'}">selected="selected"</c:if> value="日本">日本</option>
		    			<option <c:if test="${zone=='韩国'}">selected="selected"</c:if> value="韩国">韩国</option>
		    			<option <c:if test="${zone=='美国'}">selected="selected"</c:if> value="美国">美国</option>
		    			<option <c:if test="${zone=='欧洲' }">selected="selected"</c:if> value="欧洲">欧洲</option>
    				</select>
    				</label>
    			&nbsp;&nbsp;<label>电影名称：<input type="text" id="videoname" name="videoname" width="250px" 
    											<c:if test="${videoname=='' || videoname=='请输入电影名称' || videoname==null}">value="请输入电影名称" style="color:gray;border-color:green;"</c:if>  
    											<c:if test="${videoname!='' && videoname!='请输入电影名称' && videoname!=null }">value="${videoname }"  style="border-color:green;"</c:if>
    											 /></label>
    			<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex }"/>
    			&nbsp;<input id="searchBtn" type="button" value="搜索"/>
    		</p>
    	</form>
    	</div>
    	<!-- 搜索栏结束 -->
    	
    	<form id="myform" action="VideoServlet?op=delCheckedVideos" method="post">
    	<table width="900px">
    		<!-- 菜单 -->
    		<tr height="70px" id="meau">
    			<td width="10%">
    				<label><input type="checkbox" name="all" id="all" title="全选" onclick="check();"/>全选</label>
    			</td>
    			<td width="56%">青鸟视频网站视频列表</td>
    			<td width="24%">上传日期</td>
    			<td width="10%">操作</td>
    		</tr>
    		<tr height="1px"><td colspan="4"><hr/></td></tr>
    		<c:forEach items="${videoList}" var="v">
	    		<tr height="30px" style="text-align:center;" title="${v.videoname }">
	    			<td width="10%">
	    				<input title="选择《${v.videoname }》" type="checkbox" name="video" value="${v.videoid }" onclick="checkBox();"/>
	    			</td>
	    			<td width="56%"><a target="_blank" href="VideoServlet?videoid=${v.videoid }&op=playVideo">${v.videoname }</a></td>
	    			<td title="上传日期" width="24%" style="text-align:center;">${v.createdate}</td>
	    			<td width="30%">
	    				<a title="修改《${v.videoname }》" href="VideoServlet?op=showVideoForUpdate&vid=${v.videoid}">修改</a>
						<a title="删除《${v.videoname }》" href="javascript:delVideo(${v.videoid});">删除</a>   					
	    			</td>
	    		</tr>
	    		<tr height="1px"><td colspan="4"><hr/></td></tr>
    		</c:forEach>
    	</table>
    	<a id="delCKVideos" href="javascript:delCheckedVideos();" title="删除所选">删除所选</a>
    	</form>
    	<div id="empty">
    		<p>暂时没有电影，快去上传电影吧！>>>
    			<a href="VideoClassServlet?op=showVideoClassForUploadVideo" title="上传视频">上传视频</a>
    		</p>
    	</div>
    	<%--表示当前页面不是通过搜索显示的--%>
    	<c:if test="${isSearch==false}">
    		<div id="pageZone">
    			<ul>
	           	<c:if test="${pageIndex>1 }">
	            	<li><a href="VideoServlet?op=showVideoListForAdmin&pageIndex=1">首页</a></li>
	            	<li><a href="VideoServlet?op=showVideoListForAdmin&pageIndex=${pageIndex-1 }">上页</a></li>
	           	</c:if> 
	           	<c:if test="${pageIndex<=1 }">
	           		<li>首页</li>
	            	<li>上一页</li>
	           	</c:if> 
	           <select id="SelectIndex" onchange="changeIndex('all');">
					<c:forEach var="i" varStatus="status" begin="1" end="${totalPages}">
	           			<option 
	           				<c:if test="${status.index==pageIndex}">
	           					selected="selected"
	           				</c:if> 
	           			value="${status.index }">第${status.index}页</option>
					</c:forEach>
          	 	</select>
	           	<c:if test="${pageIndex!=totalPages }">
	            	<li><a href="VideoServlet?op=showVideoListForAdmin&pageIndex=${pageIndex+1}">下页</a></li>
	            	<li><a href="VideoServlet?op=showVideoListForAdmin&pageIndex=${totalPages }">尾页</a></li>
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
    	<%-- 表示当前页面是通过搜索显示的--%>
    	<c:if test="${isSearch==true}">
    		<div id="pageZone">
				<ul>
	           	<c:if test="${pageIndex>1 }">
	            	<li><a href="javascript:changePageIndexBySearch(1);">首页</a></li>
	            	<li><a href="javascript:changePageIndexBySearch(${pageIndex-1 });">上页</a></li>
	           	</c:if> 
	           	<c:if test="${pageIndex<=1 }">
	           		<li>首页</li>
	            	<li>上一页</li>
	           	</c:if> 
	           <select id="SelectIndex" onchange="changeIndex('search');">
					<c:forEach var="i" varStatus="status" begin="1" end="${totalPages}">
	           			<option 
	           				<c:if test="${status.index==pageIndex}">
	           					selected="selected"
	           				</c:if> 
	           			value="${status.index }">第${status.index}页</option>
					</c:forEach>
          	 	</select>
	           	<c:if test="${pageIndex!=totalPages }">
	            	<li><a href="javascript:changePageIndexBySearch(${pageIndex+1});">下页</a></li>
	            	<li><a href="javascript:changePageIndexBySearch(${totalPages});">尾页</a></li>
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
