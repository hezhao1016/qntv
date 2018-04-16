package com.qntv.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.qntv.dao.UserCollectDao;
import com.qntv.dao.UserDao;
import com.qntv.dao.VideoClassDao;
import com.qntv.dao.VideoDao;
import com.qntv.dao.VideoNoteDao;
import com.qntv.dao.imp.UserCollectDaoImp;
import com.qntv.dao.imp.UserDaoImp;
import com.qntv.dao.imp.VideoClassDaoImp;
import com.qntv.dao.imp.VideoDaoImp;
import com.qntv.dao.imp.VideoNoteDaoImp;
import com.qntv.entity.User;
import com.qntv.entity.Video;
import com.qntv.entity.VideoAndClass;
import com.qntv.entity.VideoClass;
import com.qntv.entity.VideoNote;

public class VideoServlet extends HttpServlet {

	VideoDao vd = new VideoDaoImp();
	UserDao ud = new UserDaoImp();
	VideoClassDao vcd = new VideoClassDaoImp();
	VideoNoteDao vnd = new VideoNoteDaoImp();
	UserCollectDao ucd = new UserCollectDaoImp();
	
	// ----------------------------------����-----------------------------------
	/**
	 * �����Ƶ���ϣ�ת����admin.jsp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getVideoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 20;//ÿҳ��ʾ20��
		int totalPage = vd.getTotalPageByPageSize(pageSize);
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
		List<Video> videos = vd.getVideosBypageSizeAndpageIndex(pageSize, pageIndex);
		request.setAttribute("videoList", videos);
		VideoClassDao vcd = new VideoClassDaoImp();
		List<VideoClass> vcs = new ArrayList<VideoClass>();
		//�����Ƶ���ͼ���
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		//��ʾ��ǰҳ�治��ͨ��������ʾ��
		request.setAttribute("isSearch", false);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	/**
	 * ���ݵ�Ӱ���͡���Ӱ���ơ����������������Ƶ��ת����admin.jsp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchVideoByVideoClassId_Years_Zone_VideoName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String videoname = request.getParameter("videoname");
		String zone = request.getParameter("zone");
		String years = request.getParameter("years");
		String videoclass = request.getParameter("videoClass");
		if(zone==null || years==null||videoclass==null||"".equals(zone.trim())||"".equals(years.trim())||"".equals(videoclass.trim())){
			//˵����������
			out.println("<script>history.back();</script>");
			return;
		}
		int videoclassid = Integer.parseInt(videoclass);
		if(videoname==null){
			videoname="";
		}
		//�ѵ�ǰ��������������request
		request.setAttribute("videoname", videoname);
		request.setAttribute("zone", zone);
		request.setAttribute("years", Integer.parseInt(years));
		request.setAttribute("videoclassid", videoclassid);
		if("��ѡ��".equals(zone)){
			zone=null;
		}
		if("-1".equals(years)){
			years=null;
		}
		if("".equals(videoname.trim()) || "�������Ӱ����".equals(videoname.trim())){
			videoname = null;
		}
		int pageSize = 20;//ÿҳ��ʾ20��
		int totalPage = vd.getTotalPageByVideoClassId_Years_Zone_VideoName(pageSize, videoclassid, years, zone, videoname);
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
		List<Video> videos = vd.getVideosByVideoClassId_Years_Zone_VideoName(pageSize, pageIndex, videoclassid, years, zone, videoname);
		request.setAttribute("videoList", videos);
		VideoClassDao vcd = new VideoClassDaoImp();
		List<VideoClass> vcs = new ArrayList<VideoClass>();
		//�����Ƶ���ͼ���
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		//��ʾ��ǰҳ����ͨ��������ʾ��
		request.setAttribute("isSearch", true);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		//��ȡ��������
		request.setAttribute("year", Integer.parseInt(year));
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	/**
	 * �ϴ���Ƶ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		Video video = new Video();
		boolean isNull = false;//��Ӱ��Ϣ�Ƿ�Ϊ��
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
    		//��������ϴ����� 10GB
		  	sfu.setSizeMax(1024*1024*1024*10);
    		try{
	    		List<FileItem> list = sfu.parseRequest(request);
    			for(FileItem fi : list){
				if(fi.isFormField()){
					String name = fi.getFieldName();
					String val = fi.getString("UTF-8");
					if(val == null || "".equals(val.trim())){
						isNull = true;
						break;
					}
 					if("videoname".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('��Ӱ���Ƴ��Ȳ��ܳ���50���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setVideoname(val);
 					}else if("director".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('�����������Ȳ��ܳ���25���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setDirector(val);
 					}else if("actor".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('�����������Ȳ��ܳ���50���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setActor(val);
 					}else if("zone".equals(name)){
 						video.setZone(val);
 					}else if("years".equals(name)){
 						video.setYears(val);
 					}else if("size".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('��Ӱ��С���Ȳ��ܳ���25���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setSize(val);
 					}else if("videoclassid".equals(name)){
 						video.setVideoclassid(Integer.parseInt(val));
 					}else if("describe".equals(name)){
 						if(val.length()<50){
 							out.println("<script>alert('��Ӱ�������Ȳ�������50���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setDescribe(val);
 					}else if("title".equals(name)){
 						if(val.length()>25 || val.length()<5){
 							out.println("<script>alert('��Ӱ���ⳤ��Ϊ5-25λ�ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setTitle(val);
 					}
				}else{
					if(fi.getName()!=null && !fi.getName().equals("")){
						//��ȡ��׺��
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//ͼƬ��׺������
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						//��Ƶ��׺������
						List<String> videoExeList = Arrays.asList("avi","mov","asf","wmv","navi","3gp","mkv","flv","rm","rmvb","dat","mpg","mpeg","divx","lxe","mp4","xv");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//��ȡʱ��
						String now = sdf.format(new Date());
						Random rd = new Random();
						//��ȡ1��1000000�������
						int math = rd.nextInt(1000000);
						//�����ļ�����
						String fileName = now+"_"+math+"."+exeName;
						if(pictureExeList.contains(exeName.toLowerCase())){
						//����ͼƬ
							String path = application.getRealPath("upload/picture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setPicturepath(fileName);
						}else if(videoExeList.contains(exeName.toLowerCase())){
						//������Ƶ
							String path = application.getRealPath("upload/video");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setVideopath(fileName);
						}else{
							out.println("<script>alert('���ϴ����ļ���ʽ����ȷ��');history.back();</script>");
							return;
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('�ļ�̫�󣬳����ϴ�����,����վ����ϴ���СΪ"+sfu.getSizeMax()/1024/1024/1024+"GB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//�����Ӱ���ƴ���
    	if(vd.getVideoByVideoName(video.getVideoname())!=null){
    		out.println("<script>alert('��Ӱ��"+video.getVideoname()+"���Ѿ����ڣ���������д��');history.back();</script>");
    		return;
    	}
    	//˵���û�û��ѡ��ͼƬ
    	if(video.getPicturepath() == null ||"".equals(video.getPicturepath().trim())){
    		out.println("<script>alert('����д�ĵ�Ӱ��Ϣ�����������ϴ���Ӱ���棡');history.back();</script>");
			return;
    	}
    	//˵���û�û��ѡ����Ƶ
    	if(video.getVideopath() == null ||"".equals(video.getVideopath().trim())){
    		out.println("<script>alert('����д�ĵ�Ӱ��Ϣ�����������ϴ���Ƶ�ļ���');history.back();</script>");
			return;
    	}
    	//���Ϊ��
    	if(isNull){
    		out.println("<script>alert('����д�ĵ�Ӱ��Ϣ����������������д��');history.back();</script>");
			return;
    	}
    	String uploadName = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		uploadName = user.getUsername();
    	}
    	//���������ϴ���Ա����
    	video.setUploadname(uploadName);
   		if(vd.uploadVedio(video)){
   			//������Ա�ӻ���
   			ud.addScoreByUserName(uploadName, 10);
   			out.println("<script>alert('��Ӱ��"+video.getVideoname()+"���ϴ��ɹ���');location='VideoServlet?op=showVideoListForAdmin';</script>");
   		}else{
   			out.println("<script>alert('��Ӱ��"+video.getVideoname()+"���ϴ�ʧ�ܣ�');history.back();</script>");
   		}
	}
	
	/**
	 * �޸���Ƶ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		Video video = new Video();
		boolean isNull = false;//��Ӱ��Ϣ�Ƿ�Ϊ��
		String picturepath = "";//ԭͼƬ��ַ
		String videopath = "";//ԭ��Ƶ��ַ 
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
		  	//��������ϴ����� 10GB
		  	sfu.setSizeMax(1024*1024*1024*10);
    		try{
	    		List<FileItem> list = sfu.parseRequest(request);
    			for(FileItem fi : list){
				if(fi.isFormField()){
					String name = fi.getFieldName();
					String val = fi.getString("UTF-8");
					if(val == null || "".equals(val.trim())){
						if("videoid".equals(name)){
						//˵����������
							out.println("<script>history.back();</script>");
							return;
						}
						isNull = true;
						continue;
					}
 					if("videoname".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('��Ӱ���Ƴ��Ȳ��ܳ���50���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setVideoname(val);
 					}else if("director".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('�����������Ȳ��ܳ���25���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setDirector(val);
 					}else if("actor".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('�����������Ȳ��ܳ���50���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setActor(val);
 					}else if("zone".equals(name)){
 						video.setZone(val);
 					}else if("years".equals(name)){
 						video.setYears(val);
 					}else if("size".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('��Ӱ��С���Ȳ��ܳ���25���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setSize(val);
 					}else if("videoclassid".equals(name)){
 						video.setVideoclassid(Integer.parseInt(val));
 					}else if("describe".equals(name)){
 						if(val.length()<50){
 							out.println("<script>alert('��Ӱ�������Ȳ�������50���ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setDescribe(val);
 					}else if("title".equals(name)){
 						if(val.length()>25 || val.length()<5){
 							out.println("<script>alert('��Ӱ���ⳤ��Ϊ5-25λ�ַ�����������д��');history.back();</script>");
							return;
 						}
 						video.setTitle(val);
 					}else if("picturepath".equals(name)){
 						picturepath = val;
 					}else if("videopath".equals(name)){
 						videopath = val;
 					}else if("videoid".equals(name)){
 						video.setVideoid(Integer.parseInt(val));
 					}
				}else{
					if(fi.getName()!=null && !fi.getName().equals("")){
						//��ȡ��׺��
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//ͼƬ��׺������
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						//��Ƶ��׺������
						List<String> videoExeList = Arrays.asList("avi","mov","asf","wmv","navi","3gp","mkv","flv","rm","rmvb","dat","mpg","mpeg","divx","lxe","mp4","xv");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//��ȡʱ��
						String now = sdf.format(new Date());
						Random rd = new Random();
						//��ȡ1��1000000�������
						int math = rd.nextInt(1000000);
						//�����ļ�����
						String fileName = now+"_"+math+"."+exeName;
						if(pictureExeList.contains(exeName.toLowerCase())){
						//����ͼƬ
							String path = application.getRealPath("upload/picture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setPicturepath(fileName);
						}else if(videoExeList.contains(exeName.toLowerCase())){
						//������Ƶ
							String path = application.getRealPath("upload/video");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setVideopath(fileName);
						}else{
							out.println("<script>alert('���ϴ����ļ���ʽ����ȷ��');history.back();</script>");
							return;
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('�ļ�̫�󣬳����ϴ�����,����վ����ϴ���СΪ"+sfu.getSizeMax()/1024/1024/1024+"GB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//�����Ӱ���ƴ���
    	if(vd.isExistVideoByIdAndName(video)){
    		out.println("<script>alert('��Ӱ��"+video.getVideoname()+"���Ѿ����ڣ���������д��');history.back();</script>");
			return;
    	}
    	//���Ϊ��
    	if(isNull){
    		out.println("<script>alert('����д�ĵ�Ӱ��Ϣ����������������д��');history.back();</script>");
			return;
    	}
    	//˵���û�û��ѡ��ͼƬ����ô�͸�ԭ��ͼƬ��ֵ
    	if(video.getPicturepath() == null || "".equals(video.getPicturepath().trim())){
    		video.setPicturepath(picturepath);
    	}
    	//˵���û�û��ѡ����Ƶ����ô�͸�ԭ����Ƶ��ֵ
    	if(video.getVideopath() == null || "".equals(video.getVideopath().trim())){
    		video.setVideopath(videopath);
    	}
    	String uploadName = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		uploadName = user.getUsername();
    	}
    	//���������ϴ���Ա����
    	video.setUploadname(uploadName);
   		if(vd.updateVedioByVedioId(video)){
   			//������Ա�ӻ���
   			ud.addScoreByUserName(uploadName, 5);
   			out.println("<script>alert('�޸ĵ�Ӱ��Ϣ�ɹ���');location='VideoServlet?op=showVideoListForAdmin';</script>");
   		}else{
   			out.println("<script>alert('�޸ĵ�Ӱ��Ϣʧ�ܣ�');history.back();</script>");
   		}
	}
	
	/**
	 * ɾ��һ����Ƶ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("vid") == null || "".equals(request.getParameter("vid").trim())){
			out.println("<script>history.back();</script>");
			return;
		}
		int vid = Integer.parseInt(request.getParameter("vid"));
		UserCollectDao ucd = new UserCollectDaoImp();
		Video video = vd.getVideoByVId(vid);
		String videoName = video.getVideoname();
		//�������Ƶ���ղ���
		if(ucd.isCollectVideoByVideoId(vid)){
			out.println("<script>alert('��Ӱ��"+videoName+"���ѱ��û��ղأ���ʱ����ɾ����');history.back();</script>");
			return;
		}
		VideoNoteDao vnd = new VideoNoteDaoImp();
		//�������Ƶ�ڼ�¼���м�¼
		if(vnd.isHaveLookNoteByVId(vid)){
			out.println("<script>alert('��Ӱ��"+videoName+"�����û��ۿ���¼���м�¼����ʱ����ɾ����');history.back();</script>");
			return;
		}
		if(vd.delVedioByVedioId(vid)){
			out.println("<script>alert('ɾ����Ӱ��"+videoName+"���ɹ���');location='VideoServlet?op=showVideoListForAdmin';</script>");
		}else{
			out.println("<script>alert('ɾ����Ӱ��"+videoName+"��ʧ�ܣ�');history.back();</script>");
		}
	}
	
	/**
	 * �����Ƶ��Ϣ�������޸���Ƶ��ת����updateVideo.jsp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showVideoForUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("vid") == null || "".equals(request.getParameter("vid").trim())){
			out.println("<script>history.back();</script>");
			return;
		}
		int vid = Integer.parseInt(request.getParameter("vid"));
		VideoClassDao vcd = new VideoClassDaoImp();
		List<VideoClass> vcs = new ArrayList<VideoClass>();
		//�����Ƶ���ͼ���
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		//�����Ƶ��Ϣ
		Video video = vd.getVideoByVId(vid);
		request.setAttribute("video", video);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		//��ȡ��������
		request.setAttribute("year", Integer.parseInt(year));
		request.getRequestDispatcher("updateVideo.jsp").forward(request, response);
	}
	
	/**
	 * ɾ��ѡ�е���Ƶ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCheckedVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] videoids = request.getParameterValues("video");//videoid����
		PrintWriter out = response.getWriter();
		if(videoids == null){
			out.println("<script>history.back();</script>");
			return;
		}
		UserCollectDao ucd = new UserCollectDaoImp();
		VideoNoteDao vnd = new VideoNoteDaoImp();
		boolean isOk = true;
		for (String videoid : videoids) {
			int vid = Integer.parseInt(videoid);
			Video video = vd.getVideoByVId(vid);
			String videoName = video.getVideoname();
			//�������Ƶ���ղ���
			if(ucd.isCollectVideoByVideoId(vid)){
				isOk = false;
				out.println("<script>alert('��Ӱ��"+videoName+"���ѱ��û��ղأ���ʱ����ɾ����');</script>");
				continue;
			}
			//�������Ƶ�ڼ�¼���м�¼
			if(vnd.isHaveLookNoteByVId(vid)){
				isOk = false;
				out.println("<script>alert('��Ӱ��"+videoName+"�����û��ۿ���¼���м�¼����ʱ����ɾ����');</script>");
				continue;
			}
			if(!vd.delVedioByVedioId(vid)){
				isOk = false;
				out.println("<script>alert('ɾ����Ӱ��"+videoName+"��ʧ�ܣ�');</script>");
				continue;
			}
		}
		if(isOk){
		//ȫ���ɹ�ɾ��
			out.println("<script>alert('����ѡ�е���Ƶ��ȫ��ɾ���ɹ���');location='VideoServlet?op=showVideoListForAdmin';</script>");
		}else{
		//δ��ɾ��ȫ����Ƶ
			out.println("<script>location='VideoServlet?op=showVideoListForAdmin';</script>");
		}
	}
	/**
	 * ��ӹۿ���¼
	 * @param username
	 * @param vid
	 */
	public boolean addVideoNoteByUserName_VideoId(String username,int videoid){
		VideoNote vn = new VideoNote();
		vn.setUsername(username);
		vn.setVideoid(videoid);
		boolean isOk =  vnd.VideoNoteInfoByPlayVideo(vn);
		return isOk;
	}
	/**
	 * ���ݵ�Ӱid���û�����ѯ�Ƿ��ղع���Ƭ
	 * @param username
	 * @param videoid
	 */
	public void isCollectByUserNameAndVideoId(HttpServletRequest request,
			HttpServletResponse response,String username,int videoid) 
					throws ServletException, IOException {
		boolean isCollect =  ucd.isExistCollectByUserNameAndVideoId(username, videoid);
		request.setAttribute("isCollect", isCollect);
	}
	// ----------------------------------����Ϊ����_code-----------------------------------
	
	//------------------------------------������-------------------------------------------
	public void getVideoFromTimeAndCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<VideoClass> vcs = new ArrayList<VideoClass>();
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		int pageIndex = 1;
		if (request.getParameter("pageindex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageindex"));
		}
		request.setAttribute("pageindex", pageIndex);
		int classid = -1;
		if (request.getParameter("classid") != null) {
			classid = Integer.parseInt(request.getParameter("classid"));
			session.setAttribute("last_classid", classid);
		}
		int last_classid = 0;
		if (session.getAttribute("last_classid") != null) {
			last_classid = Integer.parseInt(session.getAttribute("last_classid")
					.toString());
		}
		if (classid == -1 && last_classid != 0) {
			classid = last_classid;
		}
		List videos = vd.getVideoFromTimeAndCount(classid);
		request.setAttribute("getVideoFromTimeAndCount", videos);
		List list = vd.getVideoByClassIdAndPageindex(pageIndex, 15, classid);
		request.setAttribute("ListByPageSizeAndPageIndex", list);
		int totalPage = vd.getTotalPageByPageSize(15, classid);
		request.setAttribute("totalPages", totalPage);
		List actors = vd.getActor();
		request.setAttribute("actors", actors);
	}

	public void getVideoByClass(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageIndex = 1;
		if (request.getParameter("pageindex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageindex"));
		}
		request.setAttribute("pageindex", pageIndex);
		int classid = Integer.parseInt(request.getParameter("classid"));
		List list = vd.getVideoByClassIdAndPageindex(pageIndex, 15, classid);
		request.setAttribute("ListByPageSizeAndPageIndex", list);
		int totalPage = vd.getTotalPageByPageSizeAndClassid(15, classid);
		request.setAttribute("totalPages", totalPage);
		List vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		List videos = vd.getVideoByClassId(classid);
		request.setAttribute("getVideoFromTimeAndCount", videos);

	}

	public boolean PlayVideo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = "";
		//���û�е�¼�����ص�½ҳ��
		if(session.getAttribute("user")==null){
			return false;
		}
		User user = (User)session.getAttribute("user");
		username = user.getUsername();
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		Video video = vd.getVideoByVId(videoid);
		request.setAttribute("video", video);
		vd.addPlayCount(videoid);
    	//��ӹۿ���¼
    	addVideoNoteByUserName_VideoId(username, videoid);
    	//�鿴�Ƿ��ղ�,����request��
    	isCollectByUserNameAndVideoId(request, response, username, videoid);
    	//���û��ӻ���
		ud.addScoreByUserName(username, 3);
		return true;
	}

	public void getTop50BySql(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int classid = -1;
		String actor = "";
		String zone = "";
		String time = "";
		String order = "";
		if (request.getParameter("classid") != null) {
			classid = Integer.parseInt(request.getParameter("classid"));
		}
		if (request.getParameter("actor") != null) {
			actor = request.getParameter("actor");
		}
		if(request.getParameter("zone") != null){
			zone = request.getParameter("zone");
		}
		if(request.getParameter("time") != null){
			time = request.getParameter("time");
		}
		if(request.getParameter("order") != null){
			order = request.getParameter("order");	
		}
		List top50 = vd.getTop50VideoBySql(classid, actor, zone, time, order);
		List vcs = vcd.getAllVideoClass();
		List actors = vd.getActor();
		request.setAttribute("actors", actors);
		request.setAttribute("top50", top50);
		request.setAttribute("videoClassLists", vcs);
	}

	public void Videosearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("videoname");
		List<VideoAndClass> videos = vd.search(name);
		String username = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		username = user.getUsername();
    	}
    	//������Ƶ���ϣ����β�ѯ�û���û���ղع���Ƭ
    	for (VideoAndClass v : videos) {
    		boolean isCollect =  ucd.isExistCollectByUserNameAndVideoId(username, v.getVideoid());
    		v.setIscollect(isCollect);
    	}
    	request.setAttribute("videos", videos);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String op = request.getParameter("op");
		if("showVideoListForAdmin".equals(op)){
		//�����Ƶ���ϣ�ת����admin.jsp
			getVideoList(request,response);
		}else if("uploadVideo".equals(op)){
		//�ϴ���Ƶ
			uploadVideo(request, response);
		}else if("updateVideo".equals(op)){
		//�޸���Ƶ
			updateVideo(request, response);
		}else if("delVideo".equals(op)){
		//ɾ��һ����Ƶ
			delVideo(request, response);
		}else if("delCheckedVideos".equals(op)){
		//ɾ��ѡ�е���Ƶ
			delCheckedVideos(request,response);
		}else if("showVideoForUpdate".equals(op)){
		//�����Ƶ��Ϣ�������޸���Ƶ��ת����updateVideo.jsp
			showVideoForUpdate(request, response);
		}else if("searchVideoByVideoClassId_Years_Zone_VideoName".equals(op)){
		//���ݵ�Ӱ���͡���Ӱ���ơ����������������Ƶ��ת����admin.jsp
			searchVideoByVideoClassId_Years_Zone_VideoName(request, response);
		}else if ("getVideoFromTimeAndCount".equals(op)) {
			// ��ȡ���е���Ƶ����
			getVideoFromTimeAndCount(request, response);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else if ("getVideoByClassid".equals(op)) {
			// �����ȡ��Ƶ����
			getVideoByClass(request, response);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else if ("playVideo".equals(op)) {
			// ������Ƶ
			if(PlayVideo(request, response)){
				request.getRequestDispatcher("player.jsp").forward(request,response);
			}else{
				//û�е�¼
				out.println("<script>alert('����û�е�¼�����ȵ�¼��');location = 'login.jsp';</script>");
			}
		} else if ("top50".equals(op)) {
			// ����SQL����ȡTOP 50��Ƶ
			getTop50BySql(request, response);
			request.getRequestDispatcher("top.jsp").forward(request, response);
		} else if ("search".equals(op)) {
			// ����SQL����ȡTOP 50��Ƶ
			Videosearch(request, response);
			request.getRequestDispatcher("search.jsp").forward(request,
					response);
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
