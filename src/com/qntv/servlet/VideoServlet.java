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
	
	// ----------------------------------何钊-----------------------------------
	/**
	 * 获得视频集合，转发至admin.jsp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getVideoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 20;//每页显示20条
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
		//获得视频类型集合
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		//表示当前页面不是通过搜索显示的
		request.setAttribute("isSearch", false);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	/**
	 * 根据电影类型、电影名称、地区、年代搜索视频，转发至admin.jsp
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
			//说明操作错误
			out.println("<script>history.back();</script>");
			return;
		}
		int videoclassid = Integer.parseInt(videoclass);
		if(videoname==null){
			videoname="";
		}
		//把当前的搜索条件存入request
		request.setAttribute("videoname", videoname);
		request.setAttribute("zone", zone);
		request.setAttribute("years", Integer.parseInt(years));
		request.setAttribute("videoclassid", videoclassid);
		if("请选择".equals(zone)){
			zone=null;
		}
		if("-1".equals(years)){
			years=null;
		}
		if("".equals(videoname.trim()) || "请输入电影名称".equals(videoname.trim())){
			videoname = null;
		}
		int pageSize = 20;//每页显示20条
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
		//获得视频类型集合
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		//表示当前页面是通过搜索显示的
		request.setAttribute("isSearch", true);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		//获取今年的年份
		request.setAttribute("year", Integer.parseInt(year));
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	/**
	 * 上传视频
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
		boolean isNull = false;//电影信息是否为空
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
    		//设置最大上传限制 10GB
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
 							out.println("<script>alert('电影名称长度不能超过50个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setVideoname(val);
 					}else if("director".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('导演姓名长度不能超过25个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setDirector(val);
 					}else if("actor".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('主演姓名长度不能超过50个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setActor(val);
 					}else if("zone".equals(name)){
 						video.setZone(val);
 					}else if("years".equals(name)){
 						video.setYears(val);
 					}else if("size".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('电影大小长度不能超过25个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setSize(val);
 					}else if("videoclassid".equals(name)){
 						video.setVideoclassid(Integer.parseInt(val));
 					}else if("describe".equals(name)){
 						if(val.length()<50){
 							out.println("<script>alert('电影描述长度不能少于50个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setDescribe(val);
 					}else if("title".equals(name)){
 						if(val.length()>25 || val.length()<5){
 							out.println("<script>alert('电影标题长度为5-25位字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setTitle(val);
 					}
				}else{
					if(fi.getName()!=null && !fi.getName().equals("")){
						//获取后缀名
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//图片后缀名数组
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						//视频后缀名数组
						List<String> videoExeList = Arrays.asList("avi","mov","asf","wmv","navi","3gp","mkv","flv","rm","rmvb","dat","mpg","mpeg","divx","lxe","mp4","xv");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//获取时间
						String now = sdf.format(new Date());
						Random rd = new Random();
						//获取1到1000000的随机数
						int math = rd.nextInt(1000000);
						//完整文件名称
						String fileName = now+"_"+math+"."+exeName;
						if(pictureExeList.contains(exeName.toLowerCase())){
						//这是图片
							String path = application.getRealPath("upload/picture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setPicturepath(fileName);
						}else if(videoExeList.contains(exeName.toLowerCase())){
						//这是视频
							String path = application.getRealPath("upload/video");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setVideopath(fileName);
						}else{
							out.println("<script>alert('您上传的文件格式不正确！');history.back();</script>");
							return;
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('文件太大，超出上传限制,本网站最大上传大小为"+sfu.getSizeMax()/1024/1024/1024+"GB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//如果电影名称存在
    	if(vd.getVideoByVideoName(video.getVideoname())!=null){
    		out.println("<script>alert('电影《"+video.getVideoname()+"》已经存在，请重新填写！');history.back();</script>");
    		return;
    	}
    	//说明用户没有选择图片
    	if(video.getPicturepath() == null ||"".equals(video.getPicturepath().trim())){
    		out.println("<script>alert('您填写的电影信息不完整，请上传电影封面！');history.back();</script>");
			return;
    	}
    	//说明用户没有选择视频
    	if(video.getVideopath() == null ||"".equals(video.getVideopath().trim())){
    		out.println("<script>alert('您填写的电影信息不完整，请上传视频文件！');history.back();</script>");
			return;
    	}
    	//如果为空
    	if(isNull){
    		out.println("<script>alert('您填写的电影信息不完整，请重新填写！');history.back();</script>");
			return;
    	}
    	String uploadName = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		uploadName = user.getUsername();
    	}
    	//这里设置上传人员名称
    	video.setUploadname(uploadName);
   		if(vd.uploadVedio(video)){
   			//给管理员加积分
   			ud.addScoreByUserName(uploadName, 10);
   			out.println("<script>alert('电影《"+video.getVideoname()+"》上传成功！');location='VideoServlet?op=showVideoListForAdmin';</script>");
   		}else{
   			out.println("<script>alert('电影《"+video.getVideoname()+"》上传失败！');history.back();</script>");
   		}
	}
	
	/**
	 * 修改视频
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
		boolean isNull = false;//电影信息是否为空
		String picturepath = "";//原图片地址
		String videopath = "";//原视频地址 
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
		  	//设置最大上传限制 10GB
		  	sfu.setSizeMax(1024*1024*1024*10);
    		try{
	    		List<FileItem> list = sfu.parseRequest(request);
    			for(FileItem fi : list){
				if(fi.isFormField()){
					String name = fi.getFieldName();
					String val = fi.getString("UTF-8");
					if(val == null || "".equals(val.trim())){
						if("videoid".equals(name)){
						//说明操作错误
							out.println("<script>history.back();</script>");
							return;
						}
						isNull = true;
						continue;
					}
 					if("videoname".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('电影名称长度不能超过50个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setVideoname(val);
 					}else if("director".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('导演姓名长度不能超过25个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setDirector(val);
 					}else if("actor".equals(name)){
 						if(val.length()>50){
 							out.println("<script>alert('主演姓名长度不能超过50个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setActor(val);
 					}else if("zone".equals(name)){
 						video.setZone(val);
 					}else if("years".equals(name)){
 						video.setYears(val);
 					}else if("size".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('电影大小长度不能超过25个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setSize(val);
 					}else if("videoclassid".equals(name)){
 						video.setVideoclassid(Integer.parseInt(val));
 					}else if("describe".equals(name)){
 						if(val.length()<50){
 							out.println("<script>alert('电影描述长度不能少于50个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						video.setDescribe(val);
 					}else if("title".equals(name)){
 						if(val.length()>25 || val.length()<5){
 							out.println("<script>alert('电影标题长度为5-25位字符，请重新填写！');history.back();</script>");
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
						//获取后缀名
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//图片后缀名数组
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						//视频后缀名数组
						List<String> videoExeList = Arrays.asList("avi","mov","asf","wmv","navi","3gp","mkv","flv","rm","rmvb","dat","mpg","mpeg","divx","lxe","mp4","xv");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//获取时间
						String now = sdf.format(new Date());
						Random rd = new Random();
						//获取1到1000000的随机数
						int math = rd.nextInt(1000000);
						//完整文件名称
						String fileName = now+"_"+math+"."+exeName;
						if(pictureExeList.contains(exeName.toLowerCase())){
						//这是图片
							String path = application.getRealPath("upload/picture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setPicturepath(fileName);
						}else if(videoExeList.contains(exeName.toLowerCase())){
						//这是视频
							String path = application.getRealPath("upload/video");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							video.setVideopath(fileName);
						}else{
							out.println("<script>alert('您上传的文件格式不正确！');history.back();</script>");
							return;
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('文件太大，超出上传限制,本网站最大上传大小为"+sfu.getSizeMax()/1024/1024/1024+"GB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//如果电影名称存在
    	if(vd.isExistVideoByIdAndName(video)){
    		out.println("<script>alert('电影《"+video.getVideoname()+"》已经存在，请重新填写！');history.back();</script>");
			return;
    	}
    	//如果为空
    	if(isNull){
    		out.println("<script>alert('您填写的电影信息不完整，请重新填写！');history.back();</script>");
			return;
    	}
    	//说明用户没有选择图片，那么就赋原来图片的值
    	if(video.getPicturepath() == null || "".equals(video.getPicturepath().trim())){
    		video.setPicturepath(picturepath);
    	}
    	//说明用户没有选择视频，那么就赋原来视频的值
    	if(video.getVideopath() == null || "".equals(video.getVideopath().trim())){
    		video.setVideopath(videopath);
    	}
    	String uploadName = "";
    	if(session.getAttribute("user")!=null){
    		User user = (User)session.getAttribute("user");
    		uploadName = user.getUsername();
    	}
    	//这里设置上传人员名称
    	video.setUploadname(uploadName);
   		if(vd.updateVedioByVedioId(video)){
   			//给管理员加积分
   			ud.addScoreByUserName(uploadName, 5);
   			out.println("<script>alert('修改电影信息成功！');location='VideoServlet?op=showVideoListForAdmin';</script>");
   		}else{
   			out.println("<script>alert('修改电影信息失败！');history.back();</script>");
   		}
	}
	
	/**
	 * 删除一个视频
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
		//如果此视频被收藏了
		if(ucd.isCollectVideoByVideoId(vid)){
			out.println("<script>alert('电影《"+videoName+"》已被用户收藏，暂时不能删除！');history.back();</script>");
			return;
		}
		VideoNoteDao vnd = new VideoNoteDaoImp();
		//如果此视频在记录表有记录
		if(vnd.isHaveLookNoteByVId(vid)){
			out.println("<script>alert('电影《"+videoName+"》在用户观看记录表有记录，暂时不能删除！');history.back();</script>");
			return;
		}
		if(vd.delVedioByVedioId(vid)){
			out.println("<script>alert('删除电影《"+videoName+"》成功！');location='VideoServlet?op=showVideoListForAdmin';</script>");
		}else{
			out.println("<script>alert('删除电影《"+videoName+"》失败！');history.back();</script>");
		}
	}
	
	/**
	 * 获得视频信息，用于修改视频，转发至updateVideo.jsp
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
		//获得视频类型集合
		vcs = vcd.getAllVideoClass();
		request.setAttribute("videoClassList", vcs);
		//获得视频信息
		Video video = vd.getVideoByVId(vid);
		request.setAttribute("video", video);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		//获取今年的年份
		request.setAttribute("year", Integer.parseInt(year));
		request.getRequestDispatcher("updateVideo.jsp").forward(request, response);
	}
	
	/**
	 * 删除选中的视频
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCheckedVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] videoids = request.getParameterValues("video");//videoid数组
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
			//如果此视频被收藏了
			if(ucd.isCollectVideoByVideoId(vid)){
				isOk = false;
				out.println("<script>alert('电影《"+videoName+"》已被用户收藏，暂时不能删除！');</script>");
				continue;
			}
			//如果此视频在记录表有记录
			if(vnd.isHaveLookNoteByVId(vid)){
				isOk = false;
				out.println("<script>alert('电影《"+videoName+"》在用户观看记录表有记录，暂时不能删除！');</script>");
				continue;
			}
			if(!vd.delVedioByVedioId(vid)){
				isOk = false;
				out.println("<script>alert('删除电影《"+videoName+"》失败！');</script>");
				continue;
			}
		}
		if(isOk){
		//全部成功删除
			out.println("<script>alert('您所选中的视频已全部删除成功！');location='VideoServlet?op=showVideoListForAdmin';</script>");
		}else{
		//未能删除全部视频
			out.println("<script>location='VideoServlet?op=showVideoListForAdmin';</script>");
		}
	}
	/**
	 * 添加观看记录
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
	 * 根据电影id与用户名查询是否收藏过本片
	 * @param username
	 * @param videoid
	 */
	public void isCollectByUserNameAndVideoId(HttpServletRequest request,
			HttpServletResponse response,String username,int videoid) 
					throws ServletException, IOException {
		boolean isCollect =  ucd.isExistCollectByUserNameAndVideoId(username, videoid);
		request.setAttribute("isCollect", isCollect);
	}
	// ----------------------------------以上为何钊_code-----------------------------------
	
	//------------------------------------孙永杰-------------------------------------------
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
		//如果没有登录，返回登陆页面
		if(session.getAttribute("user")==null){
			return false;
		}
		User user = (User)session.getAttribute("user");
		username = user.getUsername();
		int videoid = Integer.parseInt(request.getParameter("videoid"));
		Video video = vd.getVideoByVId(videoid);
		request.setAttribute("video", video);
		vd.addPlayCount(videoid);
    	//添加观看记录
    	addVideoNoteByUserName_VideoId(username, videoid);
    	//查看是否收藏,存入request中
    	isCollectByUserNameAndVideoId(request, response, username, videoid);
    	//给用户加积分
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
    	//遍历视频集合，依次查询用户有没有收藏过本片
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
		//获得视频集合，转发至admin.jsp
			getVideoList(request,response);
		}else if("uploadVideo".equals(op)){
		//上传视频
			uploadVideo(request, response);
		}else if("updateVideo".equals(op)){
		//修改视频
			updateVideo(request, response);
		}else if("delVideo".equals(op)){
		//删除一个视频
			delVideo(request, response);
		}else if("delCheckedVideos".equals(op)){
		//删除选中的视频
			delCheckedVideos(request,response);
		}else if("showVideoForUpdate".equals(op)){
		//获得视频信息，用于修改视频，转发至updateVideo.jsp
			showVideoForUpdate(request, response);
		}else if("searchVideoByVideoClassId_Years_Zone_VideoName".equals(op)){
		//根据电影类型、电影名称、地区、年代搜索视频，转发至admin.jsp
			searchVideoByVideoClassId_Years_Zone_VideoName(request, response);
		}else if ("getVideoFromTimeAndCount".equals(op)) {
			// 获取所有的视频集合
			getVideoFromTimeAndCount(request, response);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else if ("getVideoByClassid".equals(op)) {
			// 分类获取视频集合
			getVideoByClass(request, response);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else if ("playVideo".equals(op)) {
			// 播放视频
			if(PlayVideo(request, response)){
				request.getRequestDispatcher("player.jsp").forward(request,response);
			}else{
				//没有登录
				out.println("<script>alert('您还没有登录，请先登录！');location = 'login.jsp';</script>");
			}
		} else if ("top50".equals(op)) {
			// 根据SQL语句获取TOP 50视频
			getTop50BySql(request, response);
			request.getRequestDispatcher("top.jsp").forward(request, response);
		} else if ("search".equals(op)) {
			// 根据SQL语句获取TOP 50视频
			Videosearch(request, response);
			request.getRequestDispatcher("search.jsp").forward(request,
					response);
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
