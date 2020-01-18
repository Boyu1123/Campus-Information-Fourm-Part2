package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Join;

public class JoinDao extends BaseDao {

	public int joinActivity(E_Join join) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("insert into s_join(jid,jname,jtel,aid,userid) values(sq_join.nextval,?,?,?,?)");
			ps.setString(1, join.getJname());
			ps.setString(2, join.getJtel());
			ps.setInt(3, join.getAid());
			ps.setInt(4, join.getUserid());
			row = ps.executeUpdate();
			if (row>0) {
				ps = con.prepareStatement("update s_activity set ahave =ahave+1 where aid=?");
				ps.setInt(1, join.getAid());
				row = ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_Join> getJoin(int aid) {
		openConnection();
		ArrayList<E_Join> list = new ArrayList<>();
		try {
			ps = con.prepareStatement("select jid,jname,jtel from s_join where aid=?");
			ps.setInt(1, aid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Join join = new E_Join();
				join.setAid(aid);
				join.setJid(rs.getInt(1));
				join.setJname(rs.getString(2));
				join.setJtel(rs.getString(3));
				list.add(join);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;
	}

	public int isJoin(int aid, int userid) {
		openConnection();
		int row = 0;
		try {
			ps= con.prepareStatement("select jid from s_join where aid=? and userid = ?");
			ps.setInt(1, aid);
			ps.setInt(2, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				row = 1;
			}else {
				row = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return row;
	}

}
