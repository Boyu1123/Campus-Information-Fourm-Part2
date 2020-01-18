package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_News;

public class NewsDao extends BaseDao {

	public int addNews(E_News news) {
		openConnection();
		int row = 0;
		try {
			ps= con.prepareStatement("insert into s_news(newsid,newstitle,newsmess,newsauthor,newsphoto1,newsdate) values(sq_news.nextval,?,?,?,?,?)");
			ps.setString(1, news.getNewstitle());
			ps.setString(2, news.getNewsmess());
			ps.setString(3, news.getNewsauthor());
			ps.setString(4, news.getNewsphoto1());
//			ps.setString(5, news.getNewsphoto2());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			ps.setString(5, df.format(new Date()));
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_News> getNews() {
		ArrayList<E_News> list = null;
		openConnection();
		try {
			list = new ArrayList<>();
			ps = con.prepareStatement("select newsid,newstitle,newsmess,newsauthor,newsphoto1,newsdate from s_news");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_News news = new E_News();
				news.setNewsid(rs.getInt(1));
				news.setNewstitle(rs.getString(2));
				news.setNewsmess(rs.getString(3));
				news.setNewsauthor(rs.getString(4));
				news.setNewsphoto1(rs.getString(5));
//				news.setNewsphoto2(rs.getString(6));
				news.setNewsdate(rs.getString(6));
				list.add(news);
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
