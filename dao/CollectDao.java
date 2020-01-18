package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Card;
import com.ustb.entity.E_Collect;

public class CollectDao extends BaseDao {

	public int addCollect(E_Collect collect) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("insert into s_collect(id,cardid,userid) values(sq_collect.nextval,?,?)");
			ps.setInt(1, collect.getCardid());
			ps.setInt(2, collect.getUserid());
			row = ps.executeUpdate();
			if (row > 0) {
				ps = con.prepareStatement("select id from s_collect where cardid=? and userid = ?");
				ps.setInt(1, collect.getCardid());
				ps.setInt(2, collect.getUserid());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					row = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return row;
	}

	public int getCollect(E_Collect collect) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("select id from s_collect where cardid=? and userid = ?");
			ps.setInt(1, collect.getCardid());
			ps.setInt(2, collect.getUserid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return row;
	}

	public int deleteCollect(int id) {
		int row = 0;
		openConnection();
		try {
			ps = con.prepareStatement("delete from s_collect where id = ?");
			ps.setInt(1, id);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_Card> MyCollect(int userid) {
		openConnection();
		ArrayList<E_Card> list = null;
		try {
			ps = con.prepareStatement(
					"select d.cardid,c.cardtitle,c.cardmess,c.cardphoto1,c.userid,c.carddate,c.cardnum,u.username from s_collect d left outer join s_card c on c.cardid = d.cardid left outer join s_user u on c.userid = u.userid where d.userid =?");
			ps.setInt(1, userid);
			list = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Card card = new E_Card();
				card.setCardid(rs.getInt(1));
				card.setCardtitle(rs.getString(2));
				card.setCardmess(rs.getString(3));
				card.setCardphoto1(rs.getString(4));
				card.setUserid(rs.getInt(5));
				card.setCarddate(rs.getString(6));
				card.setNum(rs.getInt(7));
				card.setUsername(rs.getString(8));
				list.add(card);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
