package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Friend;

public class FriendDao extends BaseDao {

	public int addFriend(int userid, int friendid) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("insert into s_friend(id,userid,friendid) values(sq_friend.nextval,?,?)");
			ps.setInt(1, userid);
			ps.setInt(2, friendid);
			row = ps.executeUpdate();
			if (row > 0) {
				ps = con.prepareStatement("select id from s_friend where userid=? and friendid = ?");
				ps.setInt(1, userid);
				ps.setInt(2, friendid);
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

	public int isFriend(int userid, int friendid) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("select id from s_friend where userid=? and friendid = ?");
			ps.setInt(1, userid);
			ps.setInt(2, friendid);
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

	public int deleteFriend(int id) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("delete from s_friend where id = ?");
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

	public ArrayList<E_Friend> getMyFriend(int userid) {
		openConnection();
		ArrayList<E_Friend> list = null;
		try {
			list = new ArrayList<>();
			ps = con.prepareStatement(
					"select f.id,f.friendid,u.username,u.userphotourl,u.userinfo from s_friend f left outer join s_user u on f.friendid = u.userid where f.userid=?");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Friend friend = new E_Friend();
				friend.setId(rs.getInt(1));
				friend.setFriendid(rs.getInt(2));
				friend.setFriendname(rs.getString(3));
				friend.setFriendhead(rs.getString(4));
				friend.setFriendinfo(rs.getString(5));
				list.add(friend);
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
