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
	
	// ----------------------------------����-----------------------------------
	/**
	 * ����ղ�
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
		//��ȡ��Ӱ����
		String videoName = "";
		if(video!=null){
			videoName = video.getVideoname();
		}
		if(ucd.isExistCollectByUserNameAndVideoId(username, videoid)){
			out.println("<script>alert('��Ӱ��"+videoName+"�����Ѿ��ղع�,��ȥ�ղ������ÿ��ĵ�Ӱ�ɣ�');history.back();</script>");
			return;
		}
		UserCollect uc = new UserCollect();
		uc.setUsername(username);
		uc.setVideoid(videoid);
		if(ucd.addCollectVideo(uc)){
			//����ǴӲ���ҳ����ת����
			if(request.getParameter("videoname")==null){
				out.println("<script>alert('�ղص�Ӱ��"+videoName+"���ɹ���');location='VideoServlet?op=playVideo&videoid="+videoid+"'</script>");
			}else{
			//����Ǵ�����ҳ����ת����
				out.println("<script>alert('�ղص�Ӱ��"+videoName+"���ɹ���');location='VideoServlet?op=search&videoname="+request.getParameter("videoname")+"'</script>");
			}
		}else{
			out.println("<script>alert('�ղص�Ӱ��"+videoName+"��ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * ɾ��һ���ղ�
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
		//��ȡ��Ӱ����
		String videoName = "";
		if(ucs!=null){
			if(ucs.getVideo()!=null){
				videoName = ucs.getVideo().getVideoname();
			}
		}
		if(ucd.delCollectVideoById(collectid)){
			out.println("<script>alert('ɾ���ղ���Ƶ��"+videoName+"���ɹ���');location='UserCollectServlet?op=showCollectVideoList';</script>");
		}else{
			out.println("<script>alert('ɾ���ղ���Ƶ��"+videoName+"��ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * ɾ��ѡ�е��ղ�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCheckedCollects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] collects = request.getParameterValues("collect");//collectid����
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
				//��ȡ��Ӱ����
				String videoName = "";
				if(ucs!=null){
					if(ucs.getVideo()!=null){
						videoName = ucs.getVideo().getVideoname();
					}
				}
				out.println("<script>alert('ɾ���ղ���Ƶ��"+videoName+"��ʧ�ܣ�');</script>");
				continue;
			}
		}
		if(isOk){
		//ȫ���ɹ�ɾ��
			out.println("<script>alert('����ѡ�е��ղ���Ƶ��ȫ��ɾ���ɹ���');location='UserCollectServlet?op=showCollectVideoList';</script>");
		}else{
		//δ��ɾ��ȫ���ղ���Ƶ
			out.println("<script>location='UserCollectServlet?op=showCollectVideoList';</script>");
		}
	}
	
	/**
	 * ��������ղ�
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
			out.println("<script>alert('����ղ���Ƶ�б�ɹ���');location='UserCollectServlet?op=showCollectVideoList';</script>");
		}else{
			out.println("<script>alert('����ղ���Ƶ�б�ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * ��ҳ��ʾ�ղ���Ƶ
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
		int pageSize = 20;//ÿҳ��ʾ20��
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
		//����op����
		if("addCollectVideo".equals(op)){
		//����ղ���Ƶ
			addCollectVideo(request,response);
		}else if("delCollectVideo".equals(op)){
		//ɾ��һ���ղ���Ƶ
			delCollectVideo(request,response);
		}else if("delCheckedCollects".equals(op)){
		//ɾ��ѡ�е��ղ���Ƶ
			delCheckedCollects(request,response);
		}else if("delAllCollectVideo".equals(op)){
		//��������ղ���Ƶ
			delAllCollectVideo(request,response);
		}else if("showCollectVideoList".equals(op)){
		//��ҳ��ʾ�ղ���Ƶ
			showCollectVideoList(request,response);
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
