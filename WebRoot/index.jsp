<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<HTML lang="zh-CN">
<!--<![endif]-->
<HEAD>
	<TITLE>高清视频在线观看_青鸟视频</TITLE>
	<META content="IE=11.0000" http-equiv="X-UA-Compatible">
	<META http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<META http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	<META name="GENERATOR" content="MSHTML 11.00.9600.17631">
	<LINK href="index/base.css" rel="stylesheet">
	<LINK href="index/channel.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/startMove.js"></script>
	<script src="js/jquery-1.8.3.js"> </script>
</HEAD>

<BODY class="page_channel page_channel_movie">
	<DIV class="site_head site_head_logo"  id="head_v3" data-fixed="1">
		<DIV class="head_inner">
			<a><img style="margin:15px 0 0 0px" width="110px" height="50px" src="images/qingniao_ICO4.png" /></a>
			<%--如果已经登录，就显示操作按钮 --%>
			<c:if test="${not empty user}">
				<%--如果是管理员，显示管理员按钮 --%>
				<c:if test="${user.isadmin==1 }">
					<input type="button" value="管理员页面" id="index_btn1" onmouseover="showIndex_btn(1);" onmouseout="outIndex_btn(1);" onclick="clickIndex_btn1();"/>
				</c:if>
				<input type="button" value="个人中心" id="index_btn2" onmouseover="showIndex_btn(2);" onmouseout="outIndex_btn(2);" onclick="clickIndex_btn2();"/>
				<input type="button" value="退出" id="index_btn3" onmouseover="showIndex_btn(3);" onmouseout="outIndex_btn(3);" onclick="clickIndex_btn3();"/>
			</c:if>
			<%--如果没有登录，就显示登陆注册按钮 --%>
			<c:if test="${empty user}">
				<input type="button" value="登录" id="index_btn4" onmouseover="showIndex_btn(4);" onmouseout="outIndex_btn(4);" onclick="clickIndex_btn4();"/>
				<input type="button" value="注册" id="index_btn5" onmouseover="showIndex_btn(5);" onmouseout="outIndex_btn(5);" onclick="clickIndex_btn5();"/>
			</c:if>
			<DIV class="mod_search" >
			<DIV class="search_inner" >
		<div>
		<form onsubmit="return CheckSearchPost();" action="VideoServlet?op=search" method="post">
			<input id="videoname" name="videoname" type="text"  value="请输入搜索内容" onfocus="searchTextBoxFocus();" onblur="searchTextBoxBlur();"/>
			<input id="searehVideoNameBtn" type="submit" value="搜索" />
		</form>
		</div>
		</DIV>
			<I class="triangle_up"></I>							
			</DIV>
		</DIV>
	</DIV>
	<!--导航条 开始 -->
	<DIV class="site_navigation navigation_open" id="nav_v3" data-fixed="1" style="height:44px;background: url('images/bg_light.png') repeat-x top;background-color: white;margin-left:11.7%;"
		data-notresponse="1">
		<DIV class="navigation_main" id="txvSitemap">
			<DIV class="navigation_inner cf">
				<UL
					class="navigation_area navigation_area_first navigation_area_left">
					<LI class="list_item" style="width:40px;margin-left: 15px;">
						<A style="cursor:pointer;" onclick="javascript:onClick(-1)" class="link_nav"
						target="_blank" _hot="{pagetype}.hotnav.shouye">
						<SPAN class="link_inner" >首页</SPAN> </A>
					</LI>
					<!-- 循环展示分类 开始 -->
					<c:forEach items="${videoClassList }" var="c">
						<LI class="list_item" style="width:43px;" ><A style="cursor:pointer;" class="link_nav" id="${c.classid }" onclick="onClick(${c.classid})"
						target="_blank" _hot="{pagetype}.hotnav.shouye"><SPAN
							class="link_inner">${c.classname}</SPAN> </A>
						</LI>
					</c:forEach>
					
					<!-- 循环展示分类 开始 -->
				</UL>
			</DIV>
		</DIV>
	</DIV>
	<!--导航条 结束 -->
	<!--图文推荐 开始 -->
	<div id="box" id="mod_txv_focus_showpic">
		<ul id="pic_list">
		<c:forEach items="${getVideoFromTimeAndCount }" var="v" varStatus="x">
				<c:if test="${x.index == 0 }">
					<li style="z-index:3;opacity:1;fliter:alpha(opacity=100);">
					<img width="965" height="404" src="upload/picture/${v.picturepath }" alt="${v.videoname }" /></li>
				</c:if>
				<c:if test="${x.index > 0 }">
					<li class="link_thumbnails"><a href="VideoServlet?videoid=${v.videoid }&op=playVideo"><img width="965" height="404" src="upload/picture/${v.picturepath }" alt="" /></a></li>
				</c:if>
		</c:forEach>
		</ul>
	<div class="mark1" style="z-index:2"></div>
	<ul id="text_list" >
		<c:forEach items="${getVideoFromTimeAndCount }" var="v" varStatus="x">
				<c:if test="${x.index == 0 }">
					<li><h2 class="show" ><a href="VideoServlet?videoid=${v.videoid }&op=playVideo">${v.title }</a></h2></li>
				</c:if>
				<c:if test="${x.index > 0 }">
					<li class="focus_title"><h2 ><a href="VideoServlet?videoid=${v.videoid }&op=playVideo">${v.title }</a></h2></li>
				</c:if>
		</c:forEach>
	</ul>
	<div id="ico_list">
		<ul>
			<c:forEach items="${getVideoFromTimeAndCount }" var="v" varStatus="x">
				<c:if test="${x.index == 0 }">
					<li style="margin-left:5px;" class="active"><a href="javascript:void(0)"><img  src="upload/picture/${v.picturepath }" alt="" /></a></li>
				</c:if>
				<c:if test="${x.index > 0 }">
					<li><a href="javascript:void(0)"><img  src="upload/picture/${v.picturepath }" alt="" /></a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>	
	<a href="javascript:void(0)" id="btn_prev" class="btn"></a>
	<a href="javascript:void(0)" id="btn_next" class="btn showBtn"></a>
</div>
	<!--图文推荐 结束 -->
	<DIV class="v_qq_com_index" id="QQlive_SP_HP_DKB_wrapper"
		style="width: 1px; height: 1px;">
		<DIV class="l_qq_com" id="QQlive_SP_HP_DKB"
			style="margin: auto; display: none;" data-height="504px"
			data-width="1240px"></DIV>
	</DIV>
	<DIV class="site_container">
		<DIV class="container_inner">
			<DIV class="wrapper">
				<!-- 类型模块 开始 -->
				<DIV class="mod_filter">
					<DIV class="filter_item filter_item_top">
						<H3 class="filter_title">
							<SPAN class="title_inner">按排行</SPAN>
						</H3>
						<UL class="filter_list">
							<LI><A title="一周最热"
								href="VideoServlet?op=top50&time=DATEDIFF(NOW(),createdate) < 7 &order=playcount desc"
								target="_blank" _hot="movie.index.block1.list1">一周最热</A>
							</LI>
							<LI><A title="月度最热"
								href="VideoServlet?op=top50&time= DATEDIFF(NOW(),createdate) < 31 &order=playcount desc"
								target="_blank" _hot="movie.index.block1.list2">月度最热</A>
							</LI>
							<LI><A title="年度最热"
								href="VideoServlet?op=top50&time=DATEDIFF(NOW(),createdate) < 365 &order=playcount desc"
								target="_blank" _hot="movie.index.block1.list3">年度最热</A>
							</LI>
							<LI><A title="最新上映"
								href="VideoServlet?op=top50&order=createdate desc"
								target="_blank" _hot="movie.index.block1.list4">最新上映</A>
							</LI>
							<LI><A title="最近热播"
								href="VideoServlet?op=top50&order=playcount desc"
								target="_blank" _hot="movie.index.block1.list5">最近热播</A>
							</LI>
						</UL>
					</DIV>
					<!--[if !IE]>|xGv00|dc9d15557098c44f61dcafcb226c990d<![endif]-->

					<DIV class="filter_item filter_item_area">
						<H3 class="filter_title">
							<SPAN class="title_inner">按地区</SPAN>
						</H3>
						<UL class="filter_list">
							<LI><A title="内地"
								href="VideoServlet?op=top50&zone = '内地'"
								target="_blank" _hot="movie.index.block2.list1">内地</A>
							</LI>
							<LI><A title="香港"
								href="VideoServlet?op=top50&zone = '香港'"
								target="_blank" _hot="movie.index.block2.list2">香港</A>
							</LI>
							<LI><A title="台湾"
								href="VideoServlet?op=top50&zone = '台湾'"
								target="_blank" _hot="movie.index.block2.list3">台湾</A>
							</LI>
							<LI><A title="日本"
								href="VideoServlet?op=top50&zone = '日本'sc"
								target="_blank" _hot="movie.index.block2.list4">日本</A>
							</LI>
							<LI><A title="韩国"
								href="VideoServlet?op=top50&zone = '韩国'"
								target="_blank" _hot="movie.index.block2.list5">韩国</A>
							</LI>
							<LI><A title="美国"
								href="VideoServlet?op=top50&zone = '美国'"
								target="_blank" _hot="movie.index.block2.list6">美国</A>
							</LI>
							<LI><A title="欧洲"
								href="VideoServlet?op=top50&zone = '欧洲'"
								target="_blank" _hot="movie.index.block2.list9">欧洲</A>
							</LI>
						</UL>
					</DIV>
					<!--[if !IE]>|xGv00|e11e6baa176584f7aaad37bf0b8236f6<![endif]-->

					<DIV class="filter_item filter_item_type">
						<H3 class="filter_title">
							<SPAN class="title_inner">按类型</SPAN>
						</H3>
						<UL class="filter_list">
						<c:forEach items="${videoClassList}" var="f">
							<LI><A title="${f.classname }"
								href="VideoServlet?op=top50&classid = ${f.classid}"
								target="_blank" _hot="movie.index.block3.list1">${f.classname }</A>
							</LI>
						</c:forEach>
						</UL>
					</DIV>
					<!--[if !IE]>|xGv00|bb354c4061d8ec54d4abdf4d38dc6e82<![endif]-->
					<!--[if !IE]>|xGv00|72c30ec2e854d173c46e0ef1623b10a0<![endif]-->
				</DIV>
				<!-- 类型模块 结束 -->
			</DIV>

			<DIV class="wrapper">
				<DIV class="wrapper_main">
					<!-- 首播影院 开始 -->
					<DIV class="mod_row mod_row_film mod_row_s5">
						<DIV class="mod_hd">
							<DIV class="mod_title">
								<H2 class="title">
									<A href="" target="_blank"
										_hot="movie.first.title"><SPAN class="text_medium">首播影院</SPAN>
									</A>
								</H2>
								<DIV class="title_sub">
								</DIV>
							</DIV>
						</DIV>
					
						<DIV class="mod_bd">
							<DIV class="mod_figures mod_figure_v mod_figure_v_175">
								<UL class="figures_list">
								<c:forEach items="${ListByPageSizeAndPageIndex}" var="v">
									<LI class="list_item"><A tabindex="-1" title="${v.title }"
										class="figure"
										href="VideoServlet?videoid=${v.videoid }&op=playVideo"
										target="_blank" _hot="{pagetype}.first.big_img1"><IMG
											onerror="picerr(this,16)" alt="${v.videoname }"
											src="upload/picture/${v.picturepath }">
											<DIV class="figure_caption figure_caption_info">
												<DIV class="figure_info">
													<P class="figure_info_desc">${v.title }</P>
													<SPAN class="figure_info_play" href="##"><I
														class="ico_play_12"></I> <SPAN class="info_inner">播放量：${v.playcount }</SPAN>
													</SPAN>
												</DIV>
											</DIV> </A><STRONG class="figure_title"><A title="${v.videoname }"
											href="VideoServlet?videoid=${v.videoid }&op=playVideo"
											target="_blank" _hot="{pagetype}.first.big_img1">${v.videoname }</A>
									</STRONG>
									</LI>
									
									</c:forEach>
							</DIV>
<div id="pagelist">
  <ul>
    <li><a href="VideoServlet?pageindex=1&op=getVideoFromTimeAndCount">首页</a></li>
    <c:if test="${pageindex == 1 }">
    	<li><a href="VideoServlet?pageindex=1&op=getVideoFromTimeAndCount">上页</a></li>
    </c:if>
    <c:if test="${pageindex > 1}">
    	<li><a href="VideoServlet?pageindex=${pageindex-1}&op=getVideoFromTimeAndCount">上页</a></li>
    </c:if>
    <c:if test="${pageindex == totalPages }">
    	<li><a href="VideoServlet?pageindex=${totalPages }&op=getVideoFromTimeAndCount">下页</a></li>
    </c:if>
    <c:if test="${pageindex < totalPages }">
    	<li><a href="VideoServlet?pageindex=${pageindex+1}&op=getVideoFromTimeAndCount">下页</a></li>
    </c:if>
    <li><a href="VideoServlet?pageindex=${totalPages}&op=getVideoFromTimeAndCount">尾页</a></li>
    <li class="pageinfo">第${pageindex }页</li>
    <li class="pageinfo">共${totalPages }页</li>
  </ul>
</div>

							
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
					<!--[if !IE]>|xGv00|e29527e17cdfb2a260f3f43f95cdf893<![endif]-->
					<SCRIPT src="index/lab.min.js" type="text/javascript"></SCRIPT>
	
					<SCRIPT type="text/javascript">
						var d0 = new Date();
    					var timePoints=new Array();
						timePoints[0] = new Date();
						function index_page_init() {
							timePoints[1] = new Date();
							txv.index.init({
								pageType : "movie",
								aid : "QQlive_SP_DYPD_ZZSP"
							});
							timePoints[2] = new Date();
							_sreport();
						}
						$LAB
								.script("http://qzs.qq.com/tencentvideo_v1/js/txv.core.min.js?v=20141125&max_age=2592000")
								.wait()
								.script("http://qzs.qq.com/tencentvideo_v1/js/txv.focus_v2.min.js?v=20140903&max_age=604800")
								.wait()
								.script("http://qzs.qq.com/tencentvideo_v1/js/txv.index_v4.min.js?v=20140903&max_age=604800")
								.script("http://qzs.qq.com/tencentvideo_v1/js/txv.mov_v3.min.js?v=20140903&max_age=604800")
					</script>
					<SCRIPT id="l_qq_com" src="index/crystal-min.js"
						arguments="{'mo_page_ratio':0.02,'mo_ping_ratio':0.01,'mo_ping_script':'http://ra.gtimg.com/sc/mo_ping-min.js'}">
					</SCRIPT>

					<SCRIPT>
						if (typeof crystal == "object"
							&& typeof (crystal.setRegisterCallback) == "function") {
							crystal.setRegisterCallback(
							"QQlive_SP_HP_DKB",
							{
								"show" : function() {
									var mod = document.getElementById("QQlive_SP_HP_DKB_wrapper");
									if (mod) {
										mod.style.width = "100%";
										mod.style.height = "504px";
									}
								},
								"hide" : function() {
									var mod = document.getElementById("QQlive_SP_HP_DKB_wrapper");
									if (mod) {
										mod.style.display = "none";
									}
								}
							})
						}
					</SCRIPT>
		<!--back to source site at 2015-03-02 17:23:14-->
</BODY>
<!--[if !IE]>|xGv00|9735bae82a1d4695fbd39c735218b4f6<![endif]-->
</HTML>
