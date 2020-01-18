package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Card;

public class CardDao extends BaseDao {

	public int addCard(E_Card card) {
		int row = 0;
		openConnection();
		try {
			ps = con.prepareStatement(
					"insert into s_card(cardid,cardtitle,cardmess,cardphoto1,userid,carddate,cardnum) values(sq_card.nextval,?,?,?,?,?,0)");
			ps.setString(1, card.getCardtitle());
			ps.setString(2, card.getCardmess());
			ps.setString(3, card.getCardphoto1());
			ps.setInt(4, card.getUserid());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
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

	public ArrayList<E_Card> getCard() {
		openConnection();
		ArrayList<E_Card> list = null;
		try {
			list = new ArrayList<>();
			ps = con.prepareStatement(
					"select c.cardid,c.cardtitle,c.cardmess,c.cardphoto1,c.userid,c.carddate,c.cardnum,u.username from s_card c left outer join s_user u on c.userid=u.userid");
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
		} finally {
			closeConnection();
		}
		return list;
	}

	public int addNum(Integer cardid) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("update s_card set cardnum = cardnum+1 where cardid=?");
			ps.setInt(1, cardid);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_Card> getMyCard(int userid) {
		ArrayList<E_Card> list = null;
		openConnection();
		try {
			list = new ArrayList<>();
			ps = con.prepareStatement("select c.cardid,c.cardtitle,c.cardmess,c.cardphoto1,c.userid,c.carddate,c.cardnum,u.username from s_card c left outer join s_user u on c.userid=u.userid where c.userid=?");
			ps.setInt(1, userid);
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
		} finally {
			closeConnection();
		}
		return list;
	}

}
