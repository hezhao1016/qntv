package com.qntv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qntv.dao.PasswordClassDao;
import com.qntv.dao.UserDao;
import com.qntv.dao.imp.PasswordClassDaoImp;
import com.qntv.dao.imp.UserDaoImp;
import com.qntv.entity.User;

public class PasswordClassServlet extends HttpServlet {

	PasswordClassDao pcd = new PasswordClassDaoImp();
	
	// ----------------------------------¿Ó∫∆∫∆-----------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//’“ªÿ√‹¬Î
		List  list = pcd.getAllPassword();
		request.setAttribute("list", list);
		User user = null;
		String name = request.getParameter("username");
		if(name!=null){
			UserDao ud = new UserDaoImp();
			user = ud.getUserByUsername(name);
			request.setAttribute("u", user);
		}
		request.getRequestDispatcher("getMiMa.jsp").forward(request, response);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
