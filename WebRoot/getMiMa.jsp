<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>找回密码_青鸟视频</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">

 	<link href="css/videoClass.css" rel="stylesheet" type="text/css" />
  	<link href="css/adminFooter.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
  	<script type="text/javascript" src="js/getMiMa.js"></script>
  	<style type="text/css">
  		#t_title_Zone{
			background-color: menu;text-align: center;	width:100%;height:60px;
			font-size: 22px;font-weight: bold;color:green;margin-bottom: 20px;
		}
		#title{
			line-height:60px;
		}
  	</style>
  </head>
 
  <body>
  <div id="max">
     	<div id="t_title_Zone" style="margin-top:0px;">
   			<p id="title" align="center">找回密码<br></p>
   		</div> 
  	<!-- 主体部分 -->
     <div class="show" style="height:280px;">
	    <form action="UserServlet" method="post" onsubmit="return checkGetMiMa();">
		    <input type="hidden" name="op" value="getmi"/>
		    <table width="700px">
    			<tr style="line-height: 40px;">
    				<td width="13%" style="text-align: right;">用户名:</td>
    				<td>
    					<input name="username" id="username" type=text value="${u.username }" style="width:300px;height: 25px;"/>
    				</td>
    				<td id="username_prams"></td>
    			</tr>
    			<tr style="line-height: 40px;">
    				<td width="13%" style="text-align: right;">密保问题:</td>
    				<td colspan="2">
    					<select style="width:300px;height: 25px;">
					  		<c:forEach items="${list}" var="p">
					  		<option
					  			<c:if test="${u.pwdid==p.pwdid }">
					  				selected="selected"
					  			</c:if>
					  		 value="${p.pwdid }">${p.pwdname }</option>
					  		</c:forEach>
		  				</select>
    				</td>
    			</tr>
    			<tr style="line-height: 40px;">
    				<td width="13%" style="text-align: right;">密保答案:</td>
    				<td>
    					<input name="answer" id="answer" type="text" style="width:300px;height: 25px;"/>
    				</td>
    				<td id="answer_prams"></td>
    			</tr>
    		</table>
    		<p id="bott" style="margin-left: 21%;">
	    		<input class="btn" type="submit" value="提交"/>
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

