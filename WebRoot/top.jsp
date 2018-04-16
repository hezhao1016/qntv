<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0048)http://v.qq.com/rank/detail/1_-1_-1_-1_2_-1.html -->
<HTML lang=zh-cn><HEAD>
	<TITLE>电影排行榜_青鸟视频</TITLE>
	<META content="IE=7.0000" http-equiv="X-UA-Compatible">
	<META name=GENERATOR content="MSHTML 11.00.9600.17631"></HEAD>
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	<LINK rel=stylesheet type=text/css href="top/head_simple.css" media=screen>
	<LINK rel=stylesheet type=text/css href="top/rank_detail.css" media=screen>
	<LINK href="index/base.css" rel="stylesheet">
	<LINK href="index/channel.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" />
	<link href="css/adminHeader.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/top.js"></script>
<BODY>

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
</c:if>
<DIV class="site_head site_head_logo"  id="head_v3" data-fixed="1" style="z-index: 0">
		<DIV class="head_inner" style="width:100%;height:5px;">
			<DIV class="mod_search">
			<DIV class="search_inner">
			</DIV>
			<DIV class="mod_quick cf" >
				<DIV class="quick_item quick_attention" id="mod_head_favorites">								
					<DIV class="mod_quick_pop mod_pop_attention"
						id="mod_head_favorites_pop">						
						<DIV class="quick_pop_inner">						
							<I class="triangle_up triangle_up_wrap"><I
								class="triangle_up"></I> </I>							
							<DIV class="pop_info_content" id="mod_head_favorites_content"></DIV>					
						</DIV>
					</DIV>
				 	</DIV>
				<DIV class="quick_item quick_upload">
				</DIV>
				<DIV class="quick_item quick_user" id="mod_head_user">
					<DIV class="mod_quick_pop mod_pop_user" id="mod_head_notice_pop">
						<DIV class="quick_pop_inner">
							<I class="triangle_up triangle_up_wrap"><I
								class="triangle_up"></I></I>
								<DIV class="info_content_inner" id="mod_head_notice_content"></DIV>
							</DIV>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
<DIV class=rank_filter>
<DIV class=rank_logo><IMG alt=排行榜 src="top/rank_logo.jpg">
<H2>排行榜</H2></DIV>
<DIV class=rank_ad>
<DIV id=QQlive_SP_PHB_logo class=l_qq_com 
style="HEIGHT: 50px; WIDTH: 130px"></DIV></DIV>
<DIV class=rank_area><!-- 个性搜索框 开始 --><!-- 暂时停用 
        <fieldset class="mod_filter">
  <legend>个性化筛选</legend>
  <p class="rank_filter_tips"><i class="tips_tri"></i>个性化筛选</p>
  <label>性别：</label> <select class="s_gender" id="mod_gender"><option value="-1" selected>全部</option><option value="1">男</option><option value="2">女</option></select>
  <label>年龄：</label> <select class="s_age" id="mod_age"><option value="-1" selected>全部</option><option value="1">11岁以下</option><option value="2">12-14岁</option><option value="3">15-17岁</option><option value="4">18-22岁</option><option value="5">23-25岁</option><option value="6">26-30岁</option><option value="7">31-45岁</option><option value="8">46-50岁</option><option value="9">51-55岁</option><option value="10">56-60岁</option><option value="11">60岁以上</option></select>
  <label>地域：</label> <select class="s_region" id="mod_area"><option value="-1" selected>全部</option><option value="1">北京</option><option value="2">天津</option><option value="3">河北</option><option value="4">山西</option><option value="5">内蒙古</option><option value="6">江苏</option><option value="7">安徽</option><option value="8">山东</option><option value="9">辽宁</option><option value="10">吉林</option><option value="11">黑龙江</option><option value="12">上海</option><option value="13">浙江</option><option value="14">江西</option><option value="15">福建</option><option value="16">湖北</option><option value="17">湖南</option><option value="18">河南</option><option value="19">广东</option><option value="20">广西</option><option value="21">海南</option><option value="22">重庆</option><option value="23">四川</option><option value="24">贵州</option><option value="25">云南</option><option value="26">西藏</option><option value="27">陕西</option><option value="28">甘肃</option><option value="29">宁夏</option><option value="30">青海</option><option value="31">新疆</option></select>
  <a href="javascript:;" class="mod_filter_btn btn_sure" id="go_search_rank">确认</a>
  </fieldset>
	--><!-- 个性搜索框 结束 --></DIV></DIV>
<DIV class="rank_main rank_detail"><!-- 侧栏 开始 -->
<DIV class=rank_side>
<UL class=mod_rank_side_list>
 <LI class="current"><A href="VideoServlet?op=top50&classid=0&order=playcount desc"><I></I>全部</A>
 <c:forEach items="${videoClassLists}" var="vc">
 <LI><A title="${vc.classname }片排行榜" href="VideoServlet?op=top50&classid=${vc.classid }&order=playcount desc"><I class=rank_side_tri></I>${vc.classname }</A> 
 </c:forEach> 
</LI> </UL></DIV><!-- 侧栏 结束 -->
<DIV class=rank_main_content>
<DIV class=rank_main_head><!-- 排行榜分类列表 开始 -->
<UL class=rank_main_title_tab>
  <LI class=current><A href="javascript:;">播放排行榜</A> 
</LI></UL><!-- 排行榜分类列表 结束 --></DIV>
<DIV class=rank_content>
<DIV class=mod_rankbox>
<DIV class=mod_rankbox_area><STRONG class=mod_rankbox_area_title>地区：</STRONG> 
<UL class=mod_rankbox_area_list _group="iarea">
  <LI><A style="cursor:pointer" onclick="javascript:onzone('全部');" id="全部">全部</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('内地');" id="内地">内地</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('香港');" id="香港">香港</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('台湾');" id="台湾">台湾</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('日本');" id="日本">日本</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('韩国');" id="韩国">韩国</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('美国');" id="美国">美国</A></LI>
  <LI><A style="cursor:pointer" onclick="javascript:onzone('欧洲');" id="欧洲">欧洲</A></LI>
</LI></UL></DIV><!-- 时间选择模块 开始 -->
<UL class=mod_rankbox_tab_list _group="sort" _type="1">
  <LI><A  href="VideoServlet?op=top50&time=DATEDIFF(NOW(),createdate) < 7 &order=playcount desc">周榜</A> </LI>
  <LI><A  href="VideoServlet?op=top50&time=DATEDIFF(NOW(),createdate) < 31 &order=playcount desc">月榜</A> </LI>
  <LI><A  href="VideoServlet?op=top50&time=DATEDIFF(NOW(),createdate) < 365 &order=playcount desc">年榜</A> </LI>
  <LI><A  href="VideoServlet?op=top50&time=1=1&order=playcount desc">总榜</A> </LI></UL><!-- 时间选择模块 结束 --><!-- 排行列表模块 开始 -->
<UL class=mod_rankbox_cate>
  <LI style="cursor:pointer" class=mod_rankbox_con_item_title><STRONG>名称</STRONG> </LI>
  <LI style="cursor:pointer" class=mod_rankbox_con_item_actor><STRONG>看点</STRONG> </LI>
  <LI style="cursor:pointer" class=mod_rankbox_con_item_impor><STRONG>主演</STRONG> </LI>
  <LI style="cursor:pointer" class=mod_rankbox_con_list_click><STRONG>播放次数</STRONG> </LI></UL>
<UL id=mod_list class=mod_rankbox_con_list>
  <c:forEach items="${top50 }" var="v" varStatus="i">
  <c:if test="${i.index<3}">
  	<LI><SPAN class="mod_rankbox_con_list_index top_3">
  	0${i.index +1}</SPAN> <SPAN 
  	class=mod_rankbox_con_item_title><A title=${v.videoname }
  	href="VideoServlet?videoid=${v.videoid }&op=playVideo" 
  	target=_blank>${v.videoname }</A></SPAN> <SPAN title="${v.describe }" style="cursor:pointer"
  	class=mod_rankbox_con_item_actor>${v.describe }</SPAN> <SPAN 
  	class=mod_rankbox_con_item_impor><A title="${v.actor}"  style="cursor:pointer" target=_blank>${v.actor}</A></SPAN> 
  	<SPAN class=mod_rankbox_con_list_click><STRONG class=num>${v.playcount}</STRONG></SPAN> </LI>
  </c:if>
  <c:if test="${i.index>=3}">
  	<LI><SPAN class=mod_rankbox_con_list_index><c:if test="${i.index<9}">
  	0${i.index+1}</c:if>
  	<c:if test="${i.index>=9}">
  	${i.index+1}</c:if></SPAN> <SPAN 
  	class=mod_rankbox_con_item_title><A title=${v.videoname } 
 	 href="VideoServlet?videoid=${v.videoid }&op=playVideo" 
  	target=_blank>${v.videoname }</A></SPAN> <SPAN title="${v.describe }" style="cursor:pointer"
  	class=mod_rankbox_con_item_actor>${v.describe }</SPAN> <SPAN 
  	class=mod_rankbox_con_item_impor><A title="${v.actor}"  style="cursor:pointer"
 	target=_blank>${v.actor}</A></SPAN> <SPAN class=mod_rankbox_con_list_click><STRONG 
  	class=num>${v.playcount}</STRONG></SPAN> </LI>
  </c:if>
  </c:forEach>
 </UL><!-- 排行列表模块 结束 --></DIV></DIV></DIV></DIV>
<DIV class="mod_footer">
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
										href=""
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
						</DIV>
					</DIV>
<SCRIPT type=text/javascript src="top/txv.rank.min.js"></SCRIPT>

<SCRIPT language=javascript>
txv.rank.detailCgi.initPage({
typeId : "1",
igender : "-1",
iage : "-1",
iarea : "-1",
sort : "2",
iprovince : "-1"
});
</SCRIPT>

<SCRIPT id=l_qq_com src="top/crystal-min.js" 
arguments="{'mo_page_ratio':0.02,'mo_ping_ratio':0.01,'mo_ping_script':'http://ra.gtimg.com/sc/mo_ping-min.js'}"></SCRIPT>
<!-- 0.052:0 --></BODY></HTML>
