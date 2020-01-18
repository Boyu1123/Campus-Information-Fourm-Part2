package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Notice;

public class NoticeDao extends BaseDao {

	public int addNotice(E_Notice notice) {
		int row = 0;
		openConnection();
		try {
			ps = con.prepareStatement("insert into s_notice(nid,ntitle,nmessinfo,nauthor,nurl,ndate) values(sq_notice.nextval,?,?,?,?,?)");
			ps.setString(1, notice.getNtitle());
			ps.setString(2, notice.getNmess());
			ps.setString(3, notice.getNauthor());
			ps.setString(4, notice.getNurl());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//			System.out.println(df.format(new java.util.Date()));// new Date()为获取当前系统时间
//			ps.setDate(5, new Date(System.currentTimeMillis()));
			ps.setString(5, df.format(new Date()));
			row = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_Notice> getNotice() {
		ArrayList<E_Notice> list = null;
		openConnection();
		try {
			list = new ArrayList<>();
			ps = con.prepareStatement("select nid,ntitle,nmessinfo,nauthor,nurl,ndate from s_notice");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Notice notice = new E_Notice();
				notice.setNid(rs.getInt(1));
				notice.setNtitle(rs.getString(2));
				notice.setNmess(rs.getString(3));
				notice.setNauthor(rs.getString(4));
				notice.setNurl(rs.getString(5));
				notice.setNdate(rs.getString(6));
				list.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return list;
	}

}
