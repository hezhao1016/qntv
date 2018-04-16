package com.qntv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qntv.dao.UserCollectDao;
import com.qntv.dao.VideoDao;
import com.qntv.dao.VideoNoteDao;
import com.qntv.dao.imp.UserCollectDaoImp;
import com.qntv.dao.imp.VideoDaoImp;
import com.qntv.dao.imp.VideoNoteDaoImp;
import com.qntv.entity.User;
import com.qntv.entity.UserCollect;
import com.qntv.entity.UserCollectSearch;
import com.qntv.entity.Video;

public class UserCollectServlet extends HttpServlet {

	UserCollectDao ucd = new UserCollectDaoImp();
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 添加收藏
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addCollectVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if( request.getParameter("videoid") == null || "".equals(request.getParameter("videoid").trim())){
			out.println("<script>history.back();</script>");
			return;
		}
		String username = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		username = user.getUsername();
    	}
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		VideoDao vd = new VideoDaoImp();
		Video video = vd.getVideoByVId(videoid);
		//获取电影名称
		String videoName = "";
		if(video!=null){
			videoName = video.getVideoname();
		}
		if(ucd.isExistCollectByUserNameAndVideoId(username, videoid)){
			out.println("<script>alert('电影《"+videoName+"》您已经收藏过,快去收藏其他好看的电影吧！');history.back();</script>");
			return;
		}
		UserCollect uc = new UserCollect();
		uc.setUsername(username);
		uc.setVideoid(videoid);
		if(ucd.addCollectVideo(uc)){
			//如果是从播放页面跳转来的
			if(request.getParameter("videoname")==null){
				out.println("<script>alert('收藏电影《"+videoName+"》成功！');location='VideoServlet?op=playVideo&videoid="+videoid+"'</script>");
			}else{
			//如果是从搜索页面跳转来的
				out.println("<script>alert('收藏电影《"+videoName+"》成功！');location='VideoServlet?op=search&videoname="+request.getParameter("videoname")+"'</script>");
			}
		}else{
			out.println("<script>alert('收藏电影《"+videoName+"》失败！');history.back();</script>");
		}
	}
	
	/**
	 * 删除一个收藏
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCollectVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("collectid") == null || request.getParameter("collectid") == null ){
			out.println("<script>history.back();</script>");
			return;
		}
		int collectid = Integer.parseInt(request.getParameter("collectid"));
		UserCollectSearch ucs = ucd.getUserCollectSerachBycollectid(collectid);
		//获取电影名称
		String videoName = "";
		if(ucs!=null){
			if(ucs.getVideo()!=null){
				videoName = ucs.getVideo().getVideoname();
			}
		}
		if(ucd.delCollectVideoById(collectid)){
			out.println("<script>alert('删除收藏视频《"+videoName+"》成功！');location='UserCollectServlet?op=showCollectVideoList';</script>");
		}else{
			out.println("<script>alert('删除收藏视频《"+videoName+"》失败！');history.back();</script>");
		}
	}
	
	/**
	 * 删除选中的收藏
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCheckedCollects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] collects = request.getParameterValues("collect");//collectid数组
		PrintWriter out = response.getWriter();
		if(collects == null){
			out.println("<script>history.back();</script>");
			return;
		}
		boolean isOk = true;
		for (String collectid : collects) {
			int cid = Integer.parseInt(collectid);
			if(!ucd.delCollectVideoById(cid)){
				isOk = false;
				UserCollectSearch ucs = ucd.getUserCollectSerachBycollectid(cid);
				//获取电影名称
				String videoName = "";
				if(ucs!=null){
					if(ucs.getVideo()!=null){
						videoName = ucs.getVideo().getVideoname();
					}
				}
				out.println("<script>alert('删除收藏视频《"+videoName+"》失败！');</script>");
				continue;
			}
		}
		if(isOk){
		//全部成功删除
			out.println("<script>alert('您所选中的收藏视频已全部删除成功！');location='UserCollectServlet?op=showCollectVideoList';</script>");
		}else{
		//未能删除全部收藏视频
			out.println("<script>location='UserCollectServlet?op=showCollectVideoList';</script>");
		}
	}
	
	/**
	 * 清空所有收藏
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delAllCollectVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		username = user.getUsername();
    	}
		if(ucd.delAllCollectVideo(username)){
			out.println("<script>alert('清空收藏视频列表成功！');location='UserCollectServlet?op=showCollectVideoList';</script>");
		}else{
			out.println("<script>alert('清空收藏视频列表失败！');history.back();</script>");
		}
	}
	
	/**
	 * 分页显示收藏视频
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showCollectVideoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		username = user.getUsername();
    	}
		int pageSize = 20;//每页显示20条
		int totalPage = ucd.getTotalPageByCollect(pageSize, username);
		int pageIndex = 1;
		if(request.getParameter("pageIndex")!=null){
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		if(pageIndex<1){
			pageIndex = 1;
		}
		if(pageIndex>totalPage){
			pageIndex = totalPage;
		}
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("totalPages", totalPage);
		List<UserCollectSearch> collects = ucd.getAllVideosByCollect(pageSize, pageIndex, username);
		request.setAttribute("collectList", collects);
		request.getRequestDispatcher("userCollectList.jsp").forward(request, response);
	}
	// ---------------------------------------------------------------------
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		//根据op区分
		if("addCollectVideo".equals(op)){
		//添加收藏视频
			addCollectVideo(request,response);
		}else if("delCollectVideo".equals(op)){
		//删除一个收藏视频
			delCollectVideo(request,response);
		}else if("delCheckedCollects".equals(op)){
		//删除选中的收藏视频
			delCheckedCollects(request,response);
		}else if("delAllCollectVideo".equals(op)){
		//清空所有收藏视频
			delAllCollectVideo(request,response);
		}else if("showCollectVideoList".equals(op)){
		//分页显示收藏视频
			showCollectVideoList(request,response);
		}else{
		//输入错误
			out.println("<script>history.back();</script>");
			return;
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
