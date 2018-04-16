package com.qntv.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.qntv.dao.VideoDao;
import com.qntv.dao.imp.VideoDaoImp;
import com.qntv.entity.Video;
import com.qntv.servlet.VideoServlet;

public class TestJsonServlet extends HttpServlet {

	private VideoDao vd = new VideoDaoImp();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject obj;
		try {
			Integer vid = Integer.parseInt(request.getParameter("vid"));
			Video video = vd.getVideoByVId(vid);
			
			obj = JSONObject.fromObject(video);
			out.print(obj);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String str = "²ÎÊý´íÎó";
			out.print(JSONObject.fromObject(str));
		}
		
		out.flush();
		out.close();
	}


}
