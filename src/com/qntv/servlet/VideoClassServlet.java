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
	
	// ----------------------------------����-----------------------------------
	/**
	 * �����Ƶ���ͼ���
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
	 * ����cid�����Ƶ����
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
	 * ����cidɾ����Ƶ����
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
		//����������»�����Ƶ
		if(vd.isHaveVideoClassByVideoClassId(cid)){
			out.println("<script>alert('����Ƶ�����»�����Ƶ����ʱ����ɾ����');history.back();</script>");
			return;
		}
		if(vcd.delVideoClassById(cid)){
			out.println("<script>alert('ɾ����Ƶ���ͳɹ���');location='VideoClassServlet?op=showVideoClassForVideoClassList';</script>");
		}else{
			out.println("<script>alert('ɾ����Ƶ����ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * ����classid�޸���Ƶ����
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
			out.println("<script>alert('��Ӱ�������Ʋ���Ϊ�գ���������д��');history.back();</script>");
			return;
		}
		VideoClass vc = new VideoClass();
		vc.setClassid(classid);
		vc.setClassname(classname);
		//���������������Ѿ�����
		if(vcd.isExistVideoClassByIdAndName(vc)){
			out.println("<script>alert('��Ӱ����-"+classname+"Ƭ�Ѿ����ڣ���������д��');history.back();</script>");
			return;
		}
		//����ַ�����10
		if(classname.length()>10){
			out.println("<script>alert('��Ӱ���������ַ����Ȳ��ܳ���10���ַ�����������д��');history.back();</script>");
			return;
		}
		if(vcd.updateVideoClassById(vc)){
			out.println("<script>alert('�޸���Ƶ���ͳɹ���');location='VideoClassServlet?op=showVideoClassForVideoClassList';</script>");
		}else{
			out.println("<script>alert('�޸���Ƶ����ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * �����Ƶ����
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
			out.println("<script>alert('��Ӱ�������Ʋ���Ϊ�գ���������д��');history.back();</script>");
			return;
		}
		VideoClass vc = new VideoClass();
		vc.setClassname(classname);
		//���������������Ѿ�����
		if(vcd.getVideoClassByClassName(classname)!=null){
			out.println("<script>alert('��Ӱ����-"+classname+"Ƭ�Ѿ����ڣ���������д��');history.back();</script>");
			return;
		}
		//����ַ�����10
		if(classname.length()>10){
			out.println("<script>alert('��Ӱ���������ַ����Ȳ��ܳ���10���ַ�����������д��');history.back();</script>");
			return;
		}
		if(vcd.addVideoClass(vc)){
			out.println("<script>alert('�����Ƶ���ͳɹ���');location='VideoClassServlet?op=showVideoClassForVideoClassList';</script>");
		}else{
			out.println("<script>alert('�����Ƶ����ʧ�ܣ�');history.back();</script>");
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
		//�����Ƶ���ͼ��ϣ�ת����uploadVideo.jsp
			getVideoClassList(request,response);
			request.getRequestDispatcher("uploadVideo.jsp").forward(request, response);
		}else if("showVideoClassForVideoClassList".equals(op)){
		//�����Ƶ���ͼ��ϣ�ת����videoClassList.jsp
			getVideoClassList(request,response);
			request.getRequestDispatcher("videoClassList.jsp").forward(request, response);
		}else if("getVideoClassForUpdate".equals(op)){
		//�����Ƶ���ͣ�ת����updateVideoClass.jsp
			getVideoClass(request,response);
		}else if("delVideoClass".equals(op)){
		//ɾ����Ƶ����
			delVideoClass(request,response);
		}else if("updateVideoClass".equals(op)){
		//�޸���Ƶ����
			updateVideoClass(request,response);
		}else if("addVideoClass".equals(op)){
		//�����Ƶ����
			addVideoClass(request,response);
		}else{
		//�������
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
