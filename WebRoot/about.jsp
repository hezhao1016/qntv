<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <TITLE>关于我们_青鸟视频</TITLE>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="青鸟视频,电影,电视剧,综艺,新闻,财经,音乐,MV,高清,视频,在线观看">
	<meta http-equiv="description" content="create by 北大青鸟徐州中博0821 S2 2015">
	<style>
        a:link
        {
            color: red;
            text-decoration: none;
        }
        a:visited
        {
            color: purple;
            text-decoration: none;
        }
        a:HOVER{
        	color:red;
        }
        .div a
        {
            color: #40403f;
            text-decoration: none;
        }
        .div a:hover
        {
            color: red;
            text-decoration: underline;
        }
        body
        {
            font-family: 微软雅黑;
        }
    </style>
</head>
<body style="margin: 0">
    <div align="center">
        <div style="height: 382px; width: 1178px; background-image: url(images/new_bgbanner_2011.jpg);
            text-align: center;">
            <div valign="top" align="center">
                <div style="width: 960px; border:solid 1px green;  height: 86px; background-image: url(images/comiis_bodybg.jpg);
                    text-align: left">
                    &nbsp;&nbsp;&nbsp;
                    <a target="_blank"  href="VideoServlet?op=getVideoFromTimeAndCount">
                    	<img alt="青鸟视频" title="青鸟视频网站" width="90px" height="90px" src="images/qingniao_ICO4.png" />
                    </a>
                    <a target="_blank" title="青鸟视频网站"  href="VideoServlet?op=getVideoFromTimeAndCount">
	               		<h2 style="position: relative;bottom: 70px;left:116px;COLOR:BLACK;">青鸟视频网站</h2>
                    </a>
                </div>
                <table style="width: 962px; height: 38px; margin-top: 2px;" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left">
                            <div>
                                <img src="images/new_head_lefbg.gif" />
                            </div>
                        </td>
                        <td>
                        </td>
                        <td align="right">
                            <div>
                                <img src="images/new_head_lefbg.gif" />
                            </div>
                        </td>
                    </tr>
                </table>
                <div style="height: 30px; background-image: url(images/nav_bg.gif); width: 962px; font-size: 10pt;
                    font-family: 微软雅黑; line-height: 30px; overflow: hidden; text-align: left;">
                    &nbsp;&nbsp;&nbsp;&nbsp; 当前位置 : <a  title="青鸟视频网站" target="_blank"  href="VideoServlet?op=getVideoFromTimeAndCount">青鸟视频首页</a> > 关于我们
                </div>
                <div style="width: 962px; background-color: White; height: 500px;">
                    <div style="height: 14px;">
                    </div>
                    <table>
                        <tr>
                            <td>
                                <table cellpadding="0" cellspacing="0" class="div" style="width: 200px; height: 351px;
                                    background-image: url(images/leftnav.gif); font-size: 10pt; font-family: 微软雅黑;">
                                    <tr>
                                        <td align="left">
                                            <div style="width: 200px; height: 23px; background: url(images/5656.gif) repeat; color: White;
                                                font-weight: bolder;">
                                                &nbsp;&nbsp;&nbsp;&nbsp; 导航</div>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="网站首页" href="VideoServlet?op=getVideoFromTimeAndCount">网站首页</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="管理员首页" href="VideoServlet?op=showVideoListForAdmin">管理员首页</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="个人中心" href="UserServlet?op=showUserInfo">个人中心</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="电影排行榜" href="VideoServlet?op=top50&time=1=1&order=playcount%20desc">电影排行榜</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="用户登录" href="login.jsp">用户登录</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="用户注册" href="UserServlet?op=zhuce">用户注册</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="观看记录" href="VideoNoteServlet?op=showVideoNoteList">观看记录</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="用户收藏" href="UserCollectServlet?op=showCollectVideoList">用户收藏</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="网站联盟" href="http://www.bdqn.cn/">网站联盟</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="客服中心" href="http://service.qq.com/">客服中心</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            <a target="_blank" title="网站导航" href="http://www.qq.com/map/">网站导航</a>
                                        </td>
                                    </tr>
                                    <tr style="text-align:center;">
                                        <td>
                                            &nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td valign="top" align="left">
                                <div style="width: 720px; margin-left: 10px;">
                                    <div style="height: 30px; width: 720px; background-image: url(images/navZ.gif); line-height: 35px;
                                        overflow: hidden; font-size: 11pt; font-family: 微软雅黑; font-weight: bolder;">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 关于我们
                                    </div>
                                    <div style="font-size: 9pt; font-family: 宋体; line-height: 25px; margin-top: 20px;">
                                        &nbsp;&nbsp;&nbsp;&nbsp; 青鸟视频网站是由北大青鸟徐州中博2014级0821&nbsp;S2班级李浩浩小组于2015年3月25号建立的一个集电影、
                                        	电视剧、 综艺、新闻、财经、音乐、MV、高清视频在线观看的网络平台 ，
                                        	网站建立的初衷是让网民能有一个安全、视频丰富、免费的上网娱乐方式， 希望在这里你能感觉到快乐！
                                        <br />
                                        &nbsp;&nbsp;&nbsp;&nbsp; 青鸟视频网站提供了丰富的视频，包括电影、娱乐、电视剧、综艺、新闻、财经、
                                        	音乐、MV，并且很多板块都是符合大家的需求，可以使新手很快入门，同时也针对有一定经验的朋友， 
                                        	网站里也有一些很有经验的朋友发了很多进阶的资料提供下载，一般资讯查看与互动解决。
                   						如果您遇到什么问题可以到这里发表出来， 大家一起讨论， 很多热心的朋友会帮您解决问题，同时您也可以在这里帮助别人，
                   						分享您的生活经验、情感问题，情感交友，娱乐八卦，笑话、文学、汽车、
                                       	青春话题，应有尽有。 达到在上网时达到互相帮助促进学习、生活乐趣的目的！ 目前，网站正在不断进步，力争成为一个有态度的网站！
										<div style="text-align:right;line-height:20px;margin-right:1%;font-family:楷体;font-size:18px;font-weight:bold;color:#06C;">
                                        	<p>开发人员</p>
                                        	<p>何钊、孙永杰、李浩浩</p></div>
                                    	</div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <div style="border: solid 1px #ccd4e4; height: 380px;" class="adiv">
                                    <div align="left" style="background-image: url(images/titbg.jpg); height: 26px; width: 200px;
                                        border-bottom: solid 1px #ccd4e4; font-size: 11pt; font-weight: bolder;">
                                        &nbsp;&nbsp; 与我通讯</div>
                                    <div>
                                        <table>
                                            <tr>
                                                <td>
                                                    <img src="images/QQ2.png" style="height: 44px; width: 44px" />
                                                </td>
                                                <td align="left" style="line-height: 23px;">
                                                    <a href="tencent://message/?uin=1439293823&Site=myqq&Menu=yes" style="font-size: 10pt;
                                                        color: #6a6c72">联系QQ(夜雨)</a><br />
                                                    <a href="tencent://message/?uin=834085584&Site=myqq&Menu=yes" style="font-size: 10pt;
                                                        color: #6a6c72">联系QQ(心若向阳)</a><br />
                                                    <a href="tencent://message/?uin=1014646373&Site=myqq&Menu=yes" style="font-size: 10pt;
                                                        color: #6a6c72">联系QQ(Saint~李少)</a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <img src="images/e-mail.png" />
                                                </td>
                                                <td align="left">
                                                    <a style="color: #6a6c72;font-size: 10pt" href="mailto:hezhaoemail@foxmail.com">站长邮箱</a>
                                                    <br />
                                                    <a style="color: #6a6c72;font-size: 10pt" href="mailto:834085584@qq.com">副站长邮箱</a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <img src="images/dianhua.png" />
                                                </td>
                                                <td align="left">
                                                    <span style="color: #6a6c72;font-size: 10pt">Mr Try:13764379535</span><br />
                                                    <span style="color: #6a6c72;font-size: 10pt">Mr 徐 :18942336589</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><img src="images/loves.gif" /></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </td>
                            <td valign="top" align="left">
                                <div style="width: 720px; margin-left: 10px;">
                                    <div style="width: 720px; height: 35px; background-image: url(images/0001.gif); line-height: 35px;
                                        overflow: hidden; font-size: 11pt; font-family: 微软雅黑; font-weight: bolder;">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 联系我们
                                    </div>
                                    <div style="width: 716px; height: 100%; border-left: solid 1px #d4d4d4; border-right: solid 1px #d4d4d4;
                                        margin-left: 1px;">
                                        <div style="height: 20px;">
                                        </div>
                                        <div style="background-image: url(images/map.gif); height: 300px; font-family: 微软雅黑;
                                            color: #0e8da8; line-height: 28px;">
                                            &nbsp; 徐州 : Mr 何 : Tel:15250945991<br />
                                            &nbsp; 上海 : Mr 李 : Tel:13167138712
                                        </div>
                                        <div style="height: 20px;">
                                        </div>
                                    </div>
                                    <div style="width: 720px; height: 13px; background-image: url(images/0002.gif)">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="div" style="line-height: 22px; font-size: 9pt; margin-top: 20px; color: #919295">
                                    <div>
                                        <a target="_blank" title="青鸟视频网站" href="VideoServlet?op=getVideoFromTimeAndCount">青鸟视频,一个集电影、电视剧、综艺、新闻、财经、音乐、MV、高清视频在线观看的网络平台</a>(http://LocalHost:8080/qntv/VideoServlet?op=getVideoFromTimeAndCount)</div>
                                    <div>
                                     	   合作联系:15250945991 QQ:1439293823 834085584 1014646373</div>
                                    <div>
                                        Powered by 夜雨  技术支持   HeZhao 设计</div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
