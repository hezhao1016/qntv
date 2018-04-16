package com.qntv.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserCollect��չʵ���࣬������ʾ�ղ���Ƶ�б�
 * @author Administrator
 *
 */
public class UserCollectSearch extends UserCollect{
	private Video video;//��Ƶ����

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	public UserCollectSearch(){
		
	}
	public UserCollectSearch(ResultSet rs){
		try {
			Video v = new Video(rs);
			this.setVideo(v);
			String collectdate = rs.getString("collectdate").substring(0,19);//�����ڽ��д���
			this.setCollectdate(collectdate);
			this.setCollectid(rs.getInt("collectid"));
			this.setUsername(rs.getString("username"));
			this.setVideoid(rs.getInt("videoid"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
