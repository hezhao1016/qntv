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
	
	// ----------------------------------��ƺ�-----------------------------------
	/**
	 * ͨ��username��ȡ�û�
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
	 * ��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//��ȡ����
		String name = request.getParameter("username");
		//��ȡ����
		String pwd = request.getParameter("password");
		if (name == null||"".equals(name.trim())) {
			out.print("<script>alert('�������û�����');history.back();</script>");
			return;
		}
		if (pwd == null||"".equals(pwd.trim())) {
			out.print("<script>alert('�������û����룡');history.back();</script>");
			return;
		}
		//��ȡ�������֤��
		String incode = (String)request.getParameter("code");   
		//��ȡ�����������֤��
		String rightcode = (String)session.getAttribute("rand");
		//���÷���
		User u = ud.login(name, pwd);
		if (incode == null || rightcode == null || incode.trim().equals("") || rightcode.trim().equals("")) {
			out.print("<script>alert('��������֤�룡');history.back();</script>");
			return;
		}
		if(u != null){
			if (incode.toLowerCase().equals(rightcode.toLowerCase())) {
				session.setAttribute("user", u);
				if(u.getIsadmin()==0){
				//��ͨ�û�
					response.sendRedirect("VideoServlet?op=getVideoFromTimeAndCount");
				}else if(u.getIsadmin()==1){
				//����Ա
					response.sendRedirect("VideoServlet?op=showVideoListForAdmin");
				}
			}else{
				out.print("<script>alert('��֤������������������룡');history.back();</script>");
			}
		}else if(u==null &&incode.toLowerCase().equals(rightcode.toLowerCase())){
			out.print("<script>alert('�����û��������������������������룡');history.back();</script>");
		}else{
			out.print("<script>alert('���������������������룡');history.back();</script>");
		}
	}
	/**
	 * �������
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
			out.print("<script>alert(' �û����Լ��ܱ��𰸲���Ϊ�գ����������룡');history.back();</script>");	
			return;
		}
		UserDao ud = new UserDaoImp();
		String n = ud.getMima(name, answer);
		if(n!=null){
			out.print("<script>alert('����!��������ǣ�" +n+ " ���μǣ�����');location='login.jsp'</script>");			
		}else {
			out.print("<script>alert('�û������ܱ�������������������룡');history.back();</script>");
		}

	}
	/**
	 * ��������ܱ�������𼯺�
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
	 * ע��
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
		String unPwd = "";//ȷ������
		boolean isNull = false;//�û������Ƿ�Ϊ��
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
    		//��������ϴ����� 5MB
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
 							out.println("<script>alert('�û������ܳ���18���ַ�����������д��');history.back();</script>");
							return;
 						}
 						user.setUsername(val);
 					}else if("password".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('���벻�ܳ���18���ַ�����������д��');history.back();</script>");
							return;
 						}
 						user.setPassword(val);
 					}else if("userpassword".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('ȷ�����벻�ܳ���18���ַ�����������д��');history.back();</script>");
							return;
 						}
 						unPwd = val;
 					}else if("nickname".equals(name)){
 						if(val.length()>25){
 							out.println("<script>alert('�ǳƲ��ܳ���25���ַ�����������д��');history.back();</script>");
							return;
 						}
 						user.setNickname(val);
 					}else if("birthday".equals(name)){
 						user.setBirthday(val);
 					}else if("adress".equals(name)){
 						user.setAdress(val);
 					}else if("phone".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('�绰���벻�ܳ���18���ַ�����������д��');history.back();</script>");
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
						//��ȡ��׺��
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//ͼƬ��׺������
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//��ȡʱ��
						String now = sdf.format(new Date());
						Random rd = new Random();
						//��ȡ1��1000000�������
						int math = rd.nextInt(1000000);
						//�����ļ�����
						String fileName = now+"_"+math+"."+exeName;
						if(!pictureExeList.contains(exeName.toLowerCase())){
						//��ʽ����ȷ
							out.println("<script>alert('���ϴ���ͼƬ��ʽ����ȷ��');history.back();</script>");
							return;
						}else{
							//����ͼƬ
							String path = application.getRealPath("upload/userPicture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							user.setPicturepath(fileName);
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('�ļ�̫�󣬳����ϴ�����,����վ����ϴ���СΪ"+sfu.getSizeMax()/1024/1024+"MB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//����û�������
    	if(ud.isExistUserName(user.getUsername())){
    		out.println("<script>alert('�û��� "+user.getUsername()+" �Ѿ����ڣ���������д��');history.back();</script>");
    		return;
    	}
    	//˵���û�û��ѡ��ͼƬ
    	if(user.getPicturepath() == null ||"".equals(user.getPicturepath().trim())){
    		//out.println("<script>alert('����д���û���Ϣ�����������ϴ�ͷ��');history.back();</script>");
			//return;
    		//ѡ��Ĭ��ͼƬ
    		user.setPicturepath("default_qntv.jpg");
    	}
    	if(isNull){
    		out.println("<script>alert('����д���û���Ϣ����������������д��');history.back();</script>");
			return;
    	}
    	if(!user.getPassword().equals(unPwd)){
    		out.println("<script>alert('�����������벻һ�£���������д��');history.back();</script>");
    		return;
    	}
   		if(ud.addUser(user)){
   			out.println("<script>alert('ע��ɹ���');location='login.jsp';</script>");
   		}else{
   			out.println("<script>alert('������˼��ע��ʧ�ܣ�');history.back();</script>");
   		}
	}
	/**
	 * �޸��û�
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
		String picpath = "";//ԭ����ͷ���ַ
		boolean isNull = false;//�û������Ƿ�Ϊ��
		FileItemFactory fif = new DiskFileItemFactory();
    	ServletFileUpload sfu = new ServletFileUpload(fif); 
    	sfu.setHeaderEncoding("utf-8");
    	boolean isMultipart = sfu.isMultipartContent(request);
    	if(isMultipart){
    		//��������ϴ����� MB
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
 							out.println("<script>alert('�ǳƲ��ܳ���25���ַ�����������д��');history.back();</script>");
							return;
 						}
 						user.setNickname(val);
 					}else if("birthday".equals(name)){
 						user.setBirthday(val);
 					}else if("adress".equals(name)){
 						user.setAdress(val);
 					}else if("phone".equals(name)){
 						if(val.length()>18){
 							out.println("<script>alert('�绰���벻�ܳ���18���ַ�����������д��');history.back();</script>");
							return;
 						}
 						user.setPhone(val);
 					}else if("pwdid".equals(name)){
 						user.setPwdid(Integer.parseInt(val));
 					}else if("answer".equals(name)){
 						user.setAnswer(val);
 					}else if("picturepath".equals(name)){
 						picpath = val;//��ȡԭ��ͼ��
 					}
				}else{
					if(fi.getName()!=null && !fi.getName().equals("")){
						//��ȡ��׺��
						String exeName = fi.getName().substring(fi.getName().lastIndexOf(".")+1);
						//ͼƬ��׺������
						List<String> pictureExeList = Arrays.asList("jpg","png","gif","bmp","jpeg","pcx","tiff","psd","tga","eps","raw");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
						//��ȡʱ��
						String now = sdf.format(new Date());
						Random rd = new Random();
						//��ȡ1��1000000�������
						int math = rd.nextInt(1000000);
						//�����ļ�����
						String fileName = now+"_"+math+"."+exeName;
						if(!pictureExeList.contains(exeName.toLowerCase())){
						//��ʽ����ȷ
							out.println("<script>alert('���ϴ���ͼƬ��ʽ����ȷ��');history.back();</script>");
							return;
						}else{
							//����ͼƬ
							String path = application.getRealPath("upload/userPicture");
							File saveFile = new File(path+"/"+fileName);
							fi.write(saveFile);
							user.setPicturepath(fileName);
						}
					}
				}
			} 
    		}catch(FileUploadBase.SizeLimitExceededException e){
    			out.println("<script>alert('�ļ�̫�󣬳����ϴ�����,����վ����ϴ���СΪ"+sfu.getSizeMax()/1024/1024+"MB.');history.back();</script>");
				return;
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}		
    	}
    	//˵���û�û��ѡ��ͼƬ,��ֵΪԭ����ֵ
    	if(user.getPicturepath() == null ||"".equals(user.getPicturepath().trim())){
    		user.setPicturepath(picpath);
    	}
    	if(isNull){
    		out.println("<script>alert('����д���û���Ϣ����������������д��');history.back();</script>");
			return;
    	}
    	if(session.getAttribute("user")!=null){	
    		String name =  ((User)session.getAttribute("user")).getUsername();
    		user.setUsername(name);
    	}
   		if(ud.updateUser(user)){
   			//���µ�ǰ��¼�û�����
			User newUser = ud.getUserByUsername(user.getUsername());
			session.setAttribute("user", newUser);
			session.setAttribute("user", user);
   			out.println("<script>alert('�޸��û���Ϣ�ɹ���');location='UserServlet?op=showUserInfo';</script>");
   		}else{
   			out.println("<script>alert('������˼���޸��û���Ϣʧ�ܣ�');history.back();</script>");
   		}
	}
	// ---------------------------------------------------------------------
	
	// ---------------------------------����---------------------------------
	/**
	 * �޸�����
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
    		out.println("<script>alert('ԭ�������������������д��');history.back();</script>");
    		return;
    	}
		if(!newpwd.equals(repassword)){
    		out.println("<script>alert('�����������벻һ�£���������д��');history.back();</script>");
    		return;
    	}
		//�޸�����
		if (ud.updatePassword(oldpwd, newpwd, username)) {
			if (nickname == null || nickname.equals("")) {
				//���µ�ǰ��¼�û�����
				User newUser = ud.getUserByUsername(username);
				session.setAttribute("user", newUser);
				out.println("<script>alert('�޸�����ɹ���');location='UserServlet?op=showUserInfo';</script>");
			}else{
				out.println("<script>alert('���ã�"+nickname+"���޸�����ɹ���');location='UserServlet?op=showUserInfo';</script>");
			}
		}else{
			out.println("<script>alert('ϵͳ�����쳣���޸�����ʧ�ܣ�');history.back();</script>");
		}
	}
	/**
	 * �˳�
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
		//����op����
		String op = request.getParameter("op");
		//��֤��½
		if("checkLogin".equals(op)){	
			checkLogin(request,response);
		//��ȡ����
		}else if("getmi".equals(op)){
			GetMi(request,response);
		//ע��ҳ��
		}else if("zhuce".equals(op)){
			GetMiBao(request,response);
		//����û�
		}else if("addUser".equals(op)){
			addUser(request,response);
		//�޸��û�
		}else if("updateUser".equals(op)){
			updateUser(request,response);
		//��ȡ�û���Ϣ_update
		}else if("getUserByupdateUser".equals(op)){
			getUserByUserName(request,response);
			//�ٻ�ȡ�ܱ����⼯��
			PasswordClassDao pcd = new PasswordClassDaoImp();
			List  list = pcd.getAllPassword();
			request.setAttribute("pwdlist", list);	
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		//��ʾ�û���Ϣ_��������
		}else if("showUserInfo".equals(op)){
			getUserByUserName(request,response);
			request.getRequestDispatcher("userInfo.jsp").forward(request, response);
		//�޸�����
		}else if("updateLoginPassWord".equals(op)){
			updateUserPassword(request,response);
		}else if("checkOut".equals(op)){
		//�˳�
			checkOut(request,response);
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
