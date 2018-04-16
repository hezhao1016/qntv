package com.qntv.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.qntv.dao.PasswordClassDao;
import com.qntv.dao.UserDao;
import com.qntv.dao.imp.PasswordClassDaoImp;
import com.qntv.dao.imp.UserDaoImp;
import com.qntv.entity.User;

public class UserServlet extends HttpServlet {

	UserDao ud = new UserDaoImp();
	
	// ----------------------------------李浩浩-----------------------------------
	/**
	 * 通过username获取用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUserByUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("user")!=null){
			username = ((User)session.getAttribute("user")).getUsername();
		}
		User user = ud.getUserByUsername(username);
		request.setAttribute("user", user);
	}
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//获取姓名
		String name = request.getParameter("username");
		//获取密码
		String pwd = request.getParameter("password");
		if (name == null||"".equals(name.trim())) {
			out.print("<script>alert('请输入用户名！');history.back();</script>");
			return;
		}
		if (pwd == null||"".equals(pwd.trim())) {
			out.print("<script>alert('请输入用户密码！');history.back();</script>");
			return;
		}
		//获取输入的验证码
		String incode = (String)request.getParameter("code");   
		//获取随机产生的验证码
		String rightcode = (String)session.getAttribute("rand");
		//调用方法
		User u = ud.login(name, pwd);
		if (incode == null || rightcode == null || incode.trim().equals("") || rightcode.trim().equals("")) {
			out.print("<script>alert('请输入验证码！');history.back();</script>");
			return;
		}
		if(u != null){
			if (incode.toLowerCase().equals(rightcode.toLowerCase())) {
				session.setAttribute("user", u);
				if(u.getIsadmin()==0){
				//普通用户
					response.sendRedirect("VideoServlet?op=getVideoFromTimeAndCount");
				}else if(u.getIsadmin()==1){
				//管理员
					response.sendRedirect("VideoServlet?op=showVideoListForAdmin");
				}
			}else{
				out.print("<script>alert('验证码输入错误，请重新输入！');history.back();</script>");
			}
		}else if(u==null &&incode.toLowerCase().equals(rightcode.toLowerCase())){
			out.print("<script>alert('您的用户名或密码输入有误，请重新输入！');history.back();</script>");
		}else{
			out.print("<script>alert('您的输入有误，请重新输入！');history.back();</script>");
		}
	}
	/**
	 * 获得密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void GetMi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		String answer = request.getParameter("answer");
		if(name == null || answer == null||name.trim().equals("") ||answer.trim().equals("")){
			out.print("<script>alert(' 用户名以及密保答案不能为空！请重新输入！');history.back();</script>");	
			return;
		}
		UserDao ud = new UserDaoImp();
		String n = ud.getMima(name, answer);
		if(n!=null){
			out.print("<script>alert('您好!你的密码是：" +n+ " 请牢记！！！');location='login.jsp'</script>");			
		}else {
			out.print("<script>alert('用户名或密保答案输入错误，请重新输入！');history.back();</script>");
		}

	}
	/**
	 * 获得所有密保问题类别集合
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void GetMiBao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PasswordClassDao pcd = new PasswordClassDaoImp();
		List  list = pcd.getAllPassword();
		request.setAttribute("pwdlist", list);	
		request.getRequestDispatcher("zhuCe.jsp").forward(request, response);
	}
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		User user = new User();
		String unPwd = "";//确认密码
		boolean isNull = false;//用户输入是否为空
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
    		//设置最大上传限制 5MB
		  	sfu.setSizeMax(1024*1024*5);
    		try{
	    		List<FileItem> list = sfu.parseRequest(request);
    			for(FileItem fi : list){
				if(fi.isFormField()){
					String name = fi.getFieldName();
					String val = fi.getString("UTF-8");
					if(val == null || "".equals(val.trim())){
						if("username".equals(name) ||"password".equals(name) || "nickname".equals(name) || "pwdid".equals(name) || "answer".equals(name) || "userpassword".equals(name)){
							isNull = true;
							break;
						}
					}
					
 					if("username".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('用户名不能超过18个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						user.setUsername(val);
 					}else if("password".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('密码不能超过18个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						user.setPassword(val);
 					}else if("userpassword".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('确认密码不能超过18个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						unPwd = val;
 					}else if("nickname".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('昵称不能超过25个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						user.setNickname(val);
 					}else if("birthday".equals(name)){
 						user.setBirthday(val);
 					}else if("adress".equals(name)){
 						user.setAdress(val);
 					}else if("phone".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('电话号码不能超过18个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						user.setPhone(val);
 					}else if("pwdid".equals(name)){
 						user.setPwdid(Integer.parseInt(val));
 					}else if("answer".equals(name)){
 						user.setAnswer(val);
 					}
				}else{
					if(fi.getName()!=null && !fi.getName().equals("")){
						//获取后缀名
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//图片后缀名数组
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//获取时间
						String now = sdf.format(new Date());
						Random rd = new Random();
						//获取1到1000000的随机数
						int math = rd.nextInt(1000000);
						//完整文件名称
						String fileName = now+"_"+math+"."+exeName;
						if(!pictureExeList.contains(exeName.toLowerCase())){
						//格式不正确
							out.println("<script>alert('您上传的图片格式不正确！');history.back();</script>");
							return;
						}else{
							//这是图片
							String path = application.getRealPath("upload/userPicture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							user.setPicturepath(fileName);
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('文件太大，超出上传限制,本网站最大上传大小为"+sfu.getSizeMax()/1024/1024+"MB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//如果用户名存在
    	if(ud.isExistUserName(user.getUsername())){
    		out.println("<script>alert('用户名 "+user.getUsername()+" 已经存在，请重新填写！');history.back();</script>");
    		return;
    	}
    	//说明用户没有选择图片
    	if(user.getPicturepath() == null ||"".equals(user.getPicturepath().trim())){
    		//out.println("<script>alert('您填写的用户信息不完整，请上传头像！');history.back();</script>");
			//return;
    		//选择默认图片
    		user.setPicturepath("default_qntv.jpg");
    	}
    	if(isNull){
    		out.println("<script>alert('您填写的用户信息不完整，请重新填写！');history.back();</script>");
			return;
    	}
    	if(!user.getPassword().equals(unPwd)){
    		out.println("<script>alert('两次密码输入不一致，请重新填写！');history.back();</script>");
    		return;
    	}
   		if(ud.addUser(user)){
   			out.println("<script>alert('注册成功！');location='login.jsp';</script>");
   		}else{
   			out.println("<script>alert('不好意思，注册失败！');history.back();</script>");
   		}
	}
	/**
	 * 修改用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		User user = new User();
		String picpath = "";//原来的头像地址
		boolean isNull = false;//用户输入是否为空
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
    		//设置最大上传限制 MB
		  	sfu.setSizeMax(1024*1024*5);
    		try{
	    		List<FileItem> list = sfu.parseRequest(request);
    			for(FileItem fi : list){
				if(fi.isFormField()){
					String name = fi.getFieldName();
					String val = fi.getString("UTF-8");
					if(val == null || "".equals(val.trim())){
						if("password".equals(name) || "nickname".equals(name) || "pwdid".equals(name) || "answer".equals(name) || "userpassword".equals(name)){
							isNull = true;
							continue;
						}
					}
 					if("nickname".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('昵称不能超过25个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						user.setNickname(val);
 					}else if("birthday".equals(name)){
 						user.setBirthday(val);
 					}else if("adress".equals(name)){
 						user.setAdress(val);
 					}else if("phone".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('电话号码不能超过18个字符，请重新填写！');history.back();</script>");
							return;
 						}
 						user.setPhone(val);
 					}else if("pwdid".equals(name)){
 						user.setPwdid(Integer.parseInt(val));
 					}else if("answer".equals(name)){
 						user.setAnswer(val);
 					}else if("picturepath".equals(name)){
 						picpath = val;//获取原来图像
 					}
				}else{
					if(fi.getName()!=null && !fi.getName().equals("")){
						//获取后缀名
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//图片后缀名数组
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//获取时间
						String now = sdf.format(new Date());
						Random rd = new Random();
						//获取1到1000000的随机数
						int math = rd.nextInt(1000000);
						//完整文件名称
						String fileName = now+"_"+math+"."+exeName;
						if(!pictureExeList.contains(exeName.toLowerCase())){
						//格式不正确
							out.println("<script>alert('您上传的图片格式不正确！');history.back();</script>");
							return;
						}else{
							//这是图片
							String path = application.getRealPath("upload/userPicture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							user.setPicturepath(fileName);
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('文件太大，超出上传限制,本网站最大上传大小为"+sfu.getSizeMax()/1024/1024+"MB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//说明用户没有选择图片,则赋值为原来的值
    	if(user.getPicturepath() == null ||"".equals(user.getPicturepath().trim())){
    		user.setPicturepath(picpath);
    	}
    	if(isNull){
    		out.println("<script>alert('您填写的用户信息不完整，请重新填写！');history.back();</script>");
			return;
    	}
    	if(session.getAttribute("user")!=null){	
    		String name =  ((User)session.getAttribute("user")).getUsername();
    		user.setUsername(name);
    	}
   		if(ud.updateUser(user)){
   			//更新当前登录用户对象
			User newUser = ud.getUserByUsername(user.getUsername());
			session.setAttribute("user", newUser);
			session.setAttribute("user", user);
   			out.println("<script>alert('修改用户信息成功！');location='UserServlet?op=showUserInfo';</script>");
   		}else{
   			out.println("<script>alert('不好意思，修改用户信息失败！');history.back();</script>");
   		}
	}
	// ---------------------------------------------------------------------
	
	// ---------------------------------何钊---------------------------------
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUserPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String oldpwd = request.getParameter("oldpwd");
		String repassword = request.getParameter("repassword");
		String newpwd = request.getParameter("newpwd");
		if (oldpwd==null||repassword==null||newpwd==null
				||oldpwd.trim().equals("") ||repassword.trim().equals("") ||newpwd.trim().equals("")) {
			out.println("<script>history.back();</script>");
			return;
		}
		String okPwd = "";
		String username = "";
		String nickname = "";
		if(session.getAttribute("user")!=null){	
			username = ((User)session.getAttribute("user")).getUsername();
			okPwd= ((User)session.getAttribute("user")).getPassword();
			nickname= ((User)session.getAttribute("user")).getNickname();
    	}
		if(!oldpwd.equals(okPwd)){
    		out.println("<script>alert('原密码输入错误，请重新填写！');history.back();</script>");
    		return;
    	}
		if(!newpwd.equals(repassword)){
    		out.println("<script>alert('两次密码输入不一致，请重新填写！');history.back();</script>");
    		return;
    	}
		//修改密码
		if (ud.updatePassword(oldpwd, newpwd, username)) {
			if (nickname == null || nickname.equals("")) {
				//更新当前登录用户对象
				User newUser = ud.getUserByUsername(username);
				session.setAttribute("user", newUser);
				out.println("<script>alert('修改密码成功！');location='UserServlet?op=showUserInfo';</script>");
			}else{
				out.println("<script>alert('您好，"+nickname+"，修改密码成功！');location='UserServlet?op=showUserInfo';</script>");
			}
		}else{
			out.println("<script>alert('系统出现异常，修改密码失败！');history.back();</script>");
		}
	}
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//根据op区分
		String op = request.getParameter("op");
		//验证登陆
		if("checkLogin".equals(op)){	
			checkLogin(request,response);
		//获取密码
		}else if("getmi".equals(op)){
			GetMi(request,response);
		//注册页面
		}else if("zhuce".equals(op)){
			GetMiBao(request,response);
		//添加用户
		}else if("addUser".equals(op)){
			addUser(request,response);
		//修改用户
		}else if("updateUser".equals(op)){
			updateUser(request,response);
		//获取用户信息_update
		}else if("getUserByupdateUser".equals(op)){
			getUserByUserName(request,response);
			//再获取密保问题集合
			PasswordClassDao pcd = new PasswordClassDaoImp();
			List  list = pcd.getAllPassword();
			request.setAttribute("pwdlist", list);	
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		//显示用户信息_个人中心
		}else if("showUserInfo".equals(op)){
			getUserByUserName(request,response);
			request.getRequestDispatcher("userInfo.jsp").forward(request, response);
		//修改密码
		}else if("updateLoginPassWord".equals(op)){
			updateUserPassword(request,response);
		}else if("checkOut".equals(op)){
		//退出
			checkOut(request,response);
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
