package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Activity;
import com.ustb.entity.E_Join;

public class ActivityDao extends BaseDao {

	public int addActivity(E_Activity activity) {
		int row = 0;
		openConnection();
		try {
			ps = con.prepareStatement(
					"insert into s_activity(aid,atitle,amess,atime,aplace,ainfo,apeople,userid,adate,acode,atel,ahave) values(sq_activity.nextval,?,?,?,?,?,?,?,?,?,?,0)");
			ps.setString(1, activity.getAtitle());
			ps.setString(2, activity.getAmess());
			ps.setString(3, activity.getAstarttime());
			ps.setString(4, activity.getAplace());
			ps.setString(5, activity.getAinfo());
			ps.setInt(6, activity.getApeople());
			ps.setInt(7, activity.getUserid());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			ps.setString(8, df.format(new Date()));
			ps.setInt(9, 1);
			ps.setString(10, activity.getAtel());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_Activity> getActivity(int acode) {
		ArrayList<E_Activity> list = null;
		openConnection();
		list = new ArrayList<>();
		try {
			ps = con.prepareStatement(
					"select a.aid,a.atitle,a.amess,a.atime,a.aplace,a.ainfo,a.apeople,a.userid,a.adate,a.acode,a.atel,a.ahave,u.username from s_activity a left outer join s_user u on a.userid=u.userid where a.acode=?");
			ps.setInt(1, acode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Activity activity = new E_Activity();
				activity.setAid(rs.getInt(1));
				activity.setAtitle(rs.getString(2));
				activity.setAmess(rs.getString(3));
				activity.setAstarttime(rs.getString(4));
				activity.setAplace(rs.getString(5));
				activity.setAinfo(rs.getString(6));
				activity.setApeople(rs.getInt(7));
				activity.setUserid(rs.getInt(8));
				activity.setDate(rs.getString(9));
				activity.setAcode(rs.getInt(10));
				activity.setAtel(rs.getString(11));
				activity.setAhave(rs.getInt(12));
				activity.setUsername(rs.getString(13));
				list.add(activity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;
	}

	public ArrayList<E_Activity> getMyActivity(int userid) {
		ArrayList<E_Activity> list = null;
		openConnection();
		list = new ArrayList<>();
		try {
			ps = con.prepareStatement(
					"select a.aid,a.atitle,a.amess,a.atime,a.aplace,a.ainfo,a.apeople,a.userid,a.adate,a.acode,a.atel,a.ahave,u.username from s_activity a left outer join s_user u on a.userid=u.userid where a.userid=?");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Activity activity = new E_Activity();
				activity.setAid(rs.getInt(1));
				activity.setAtitle(rs.getString(2));
				activity.setAmess(rs.getString(3));
				activity.setAstarttime(rs.getString(4));
				activity.setAplace(rs.getString(5));
				activity.setAinfo(rs.getString(6));
				activity.setApeople(rs.getInt(7));
				activity.setUserid(rs.getInt(8));
				activity.setDate(rs.getString(9));
				activity.setAcode(rs.getInt(10));
				activity.setAtel(rs.getString(11));
				activity.setAhave(rs.getInt(12));
				activity.setUsername(rs.getString(13));
				list.add(activity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;
	}

	public int endActivity(int aid) {
		int row = 0;
		openConnection();
		try {
			ps = con.prepareStatement("update s_activity set acode = 0 where aid=?");
			ps.setInt(1, aid);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return row;
	}

}
