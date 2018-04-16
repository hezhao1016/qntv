package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoNoteSearch extends VideoNote{
	private Video video;//视频对象

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	public VideoNoteSearch(){
		
	}
	public VideoNoteSearch(ResultSet rs){
		try {
			Video v = new Video(rs);
			this.setVideo(v);
			this.setVideoid(rs.getInt("videoid"));
			this.setNoteid(rs.getInt("noteid"));
			this.setUsername(rs.getString("username"));
			String notedate = rs.getString("notedate").substring(0,19);//对日期进行处理
			this.setNotedate(notedate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
