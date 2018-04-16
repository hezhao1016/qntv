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
	
	// ----------------------------------����-----------------------------------
	/**
	 * ɾ���ۿ���¼
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
		//��ȡ��Ӱ����
		String videoName = "";
		if(vns!=null){
			if(vns.getVideo()!=null){
				videoName = vns.getVideo().getVideoname();
			}
		}
		if(vnd.delVideoNoteById(noteid)){
			out.println("<script>alert('ɾ���ۿ���¼��"+videoName+"���ɹ���');location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}else{
			out.println("<script>alert('ɾ���ۿ���¼��"+videoName+"��ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * ɾ��ѡ�еļ�¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCheckedVideoNote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] notes = request.getParameterValues("note");//collectid����
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
				//��ȡ��Ӱ����
				String videoName = "";
				if(vns!=null){
					if(vns.getVideo()!=null){
						videoName = vns.getVideo().getVideoname();
					}
				}
				out.println("<script>alert('ɾ���ۿ���¼��"+videoName+"��ʧ�ܣ�');</script>");
				continue;
			}
		}
		if(isOk){
		//ȫ���ɹ�ɾ��
			out.println("<script>alert('����ѡ�еĹۿ���¼��ȫ��ɾ���ɹ���');location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}else{
		//δ��ɾ��ȫ���ۿ���¼
			out.println("<script>location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}
	}
	
	/**
	 * ������йۿ���¼
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
			out.println("<script>alert('��չۿ���¼�ɹ���');location='VideoNoteServlet?op=showVideoNoteList';</script>");
		}else{
			out.println("<script>alert('��չۿ���¼ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * ��ҳ��ʾ�ۿ���¼
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
		int pageSize = 20;//ÿҳ��ʾ20��
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
		//����op����
		if("delVideoNote".equals(op)){
		//ɾ���ۿ���¼
			delVideoNote(request,response);
		}else if("delAllVideoNote".equals(op)){
		//������йۿ���¼
			delAllVideoNote(request,response);
		}else if("delCheckedVideoNote".equals(op)){
		//ɾ����ѡ�ۿ���¼
			delCheckedVideoNote(request,response);
		}else if("showVideoNoteList".equals(op)){
		//��ҳ��ʾ�ۿ���¼�б�
			showVideoNoteList(request,response);
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
