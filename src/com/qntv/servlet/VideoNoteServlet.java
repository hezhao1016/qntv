package com.qntv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qntv.dao.VideoNoteDao;
import com.qntv.dao.imp.VideoNoteDaoImp;
import com.qntv.entity.User;
import com.qntv.entity.VideoNoteSearch;

public class VideoNoteServlet extends HttpServlet {

	VideoNoteDao vnd = new VideoNoteDaoImp();
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 删除观看记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delVideoNote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("noteid") == null || request.getParameter("noteid") == null ){
			out.println("<script>history.back();</script>");
			return;
		}
		int noteid = Integer.parseInt(request.getParameter("noteid"));
		VideoNoteSearch vns = vnd.getVideoNoteSerachBynoteid(noteid);
		//获取电影名称
		String videoName = "";
		if(vns!=null){
			if(vns.getVideo()!=null){
				videoName = vns.getVideo().getVideoname();
			}
		}
		if(vnd.delVideoNoteById(noteid)){
			out.println("<script>alert('删除观看记录《"+videoName+"》成功！');location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}else{
			out.println("<script>alert('删除观看记录《"+videoName+"》失败！');history.back();</script>");
		}
	}
	
	/**
	 * 删除选中的记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCheckedVideoNote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] notes = request.getParameterValues("note");//collectid数组
		PrintWriter out = response.getWriter();
		if(notes == null){
			out.println("<script>history.back();</script>");
			return;
		}
		boolean isOk = true;
		for (String noteid : notes) {
			int vnid = Integer.parseInt(noteid);
			if(!vnd.delVideoNoteById(vnid)){
				isOk = false;
				VideoNoteSearch vns = vnd.getVideoNoteSerachBynoteid(vnid);
				//获取电影名称
				String videoName = "";
				if(vns!=null){
					if(vns.getVideo()!=null){
						videoName = vns.getVideo().getVideoname();
					}
				}
				out.println("<script>alert('删除观看记录《"+videoName+"》失败！');</script>");
				continue;
			}
		}
		if(isOk){
		//全部成功删除
			out.println("<script>alert('您所选中的观看记录已全部删除成功！');location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}else{
		//未能删除全部观看记录
			out.println("<script>location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}
	}
	
	/**
	 * 清空所有观看记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delAllVideoNote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		username = user.getUsername();
    	}
		if(vnd.delAllVideoNote(username)){
			out.println("<script>alert('清空观看记录成功！');location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}else{
			out.println("<script>alert('清空观看记录失败！');history.back();</script>");
		}
	}
	
	/**
	 * 分页显示观看记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showVideoNoteList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		username = user.getUsername();
    	}
		int pageSize = 20;//每页显示20条
		int totalPage = vnd.getTotalPageByNote(pageSize, username);
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
		List<VideoNoteSearch> videoNotes = vnd.getAllVideoNoteByUserName(pageSize, pageIndex, username);
		request.setAttribute("videoNoteList", videoNotes);
		request.getRequestDispatcher("videoNoteList.jsp").forward(request, response);
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
		if("delVideoNote".equals(op)){
		//删除观看记录
			delVideoNote(request,response);
		}else if("delAllVideoNote".equals(op)){
		//清空所有观看记录
			delAllVideoNote(request,response);
		}else if("delCheckedVideoNote".equals(op)){
		//删除所选观看记录
			delCheckedVideoNote(request,response);
		}else if("showVideoNoteList".equals(op)){
		//分页显示观看记录列表
			showVideoNoteList(request,response);
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
