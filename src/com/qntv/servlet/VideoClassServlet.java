package com.qntv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qntv.dao.VideoClassDao;
import com.qntv.dao.VideoDao;
import com.qntv.dao.imp.VideoClassDaoImp;
import com.qntv.dao.imp.VideoDaoImp;
import com.qntv.entity.VideoClass;

public class VideoClassServlet extends HttpServlet {

	VideoClassDao vcd = new VideoClassDaoImp();
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 获得视频类型集合
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getVideoClassList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<VideoClass> vcs = new ArrayList<VideoClass>();
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
	}
	
	/**
	 * 根据cid获得视频类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getVideoClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("cid") == null || "".equals(request.getParameter("cid").trim())){
			out.println("<script>history.back();</script>");
			return;
		}
		int cid = Integer.parseInt(request.getParameter("cid"));
		VideoClass vc = vcd.getVideoClassById(cid);
		request.setAttribute("videoClass", vc);
		request.getRequestDispatcher("updateVideoClass.jsp").forward(request, response);
	}
	
	/**
	 * 根据cid删除视频类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delVideoClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("cid") == null || "".equals(request.getParameter("cid").trim())){
			out.println("<script>history.back();</script>");
			return;
		}
		int cid = Integer.parseInt(request.getParameter("cid"));
		VideoDao vd = new VideoDaoImp();
		//如果此类型下还有视频
		if(vd.isHaveVideoClassByVideoClassId(cid)){
			out.println("<script>alert('此视频类型下还有视频，暂时不能删除！');history.back();</script>");
			return;
		}
		if(vcd.delVideoClassById(cid)){
			out.println("<script>alert('删除视频类型成功！');location='VideoClassServlet?op=showVideoClassForVideoClassList';</script>");
		}else{
			out.println("<script>alert('删除视频类型失败！');history.back();</script>");
		}
	}
	
	/**
	 * 根据classid修改视频类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateVideoClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("classid") == null || "".equals(request.getParameter("classid").trim())){
			out.println("<script>history.back();</script>");
			return;
		}
		int classid = Integer.parseInt(request.getParameter("classid"));
		String classname = request.getParameter("classname");
		if(classname == null || "".equals(classname.trim())){
			out.println("<script>alert('电影类型名称不能为空，请重新填写！');history.back();</script>");
			return;
		}
		VideoClass vc = new VideoClass();
		vc.setClassid(classid);
		vc.setClassname(classname);
		//如果这个类型名称已经存在
		if(vcd.isExistVideoClassByIdAndName(vc)){
			out.println("<script>alert('电影类型-"+classname+"片已经存在，请重新填写！');history.back();</script>");
			return;
		}
		//如果字符大于10
		if(classname.length()>10){
			out.println("<script>alert('电影类型名称字符长度不能超过10个字符，请重新填写！');history.back();</script>");
			return;
		}
		if(vcd.updateVideoClassById(vc)){
			out.println("<script>alert('修改视频类型成功！');location='VideoClassServlet?op=showVideoClassForVideoClassList';</script>");
		}else{
			out.println("<script>alert('修改视频类型失败！');history.back();</script>");
		}
	}
	
	/**
	 * 添加视频类型
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addVideoClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classname = request.getParameter("classname");
		if(classname == null || "".equals(classname.trim())){
			out.println("<script>alert('电影类型名称不能为空，请重新填写！');history.back();</script>");
			return;
		}
		VideoClass vc = new VideoClass();
		vc.setClassname(classname);
		//如果这个类型名称已经存在
		if(vcd.getVideoClassByClassName(classname)!=null){
			out.println("<script>alert('电影类型-"+classname+"片已经存在，请重新填写！');history.back();</script>");
			return;
		}
		//如果字符大于10
		if(classname.length()>10){
			out.println("<script>alert('电影类型名称字符长度不能超过10个字符，请重新填写！');history.back();</script>");
			return;
		}
		if(vcd.addVideoClass(vc)){
			out.println("<script>alert('添加视频类型成功！');location='VideoClassServlet?op=showVideoClassForVideoClassList';</script>");
		}else{
			out.println("<script>alert('添加视频类型失败！');history.back();</script>");
		}
	}
	// ---------------------------------------------------------------------
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		if("showVideoClassForUploadVideo".equals(op)){
		//获得视频类型集合，转发至uploadVideo.jsp
			getVideoClassList(request,response);
			request.getRequestDispatcher("uploadVideo.jsp").forward(request, response);
		}else if("showVideoClassForVideoClassList".equals(op)){
		//获得视频类型集合，转发至videoClassList.jsp
			getVideoClassList(request,response);
			request.getRequestDispatcher("videoClassList.jsp").forward(request, response);
		}else if("getVideoClassForUpdate".equals(op)){
		//获得视频类型，转发至updateVideoClass.jsp
			getVideoClass(request,response);
		}else if("delVideoClass".equals(op)){
		//删除视频类型
			delVideoClass(request,response);
		}else if("updateVideoClass".equals(op)){
		//修改视频类型
			updateVideoClass(request,response);
		}else if("addVideoClass".equals(op)){
		//添加视频类型
			addVideoClass(request,response);
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
