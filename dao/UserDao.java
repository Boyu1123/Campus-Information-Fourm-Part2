package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_User;

public class UserDao extends BaseDao {

	public int judgeAccount(String account) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("select userid from s_user where useraccount=?");
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// 存在
				row = 0;
			} else {
				// 不存在
				row = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return row;
	}

	public int addUser(E_User user) {
		int row = 0;
		openConnection();
		try {
			ps = con.prepareStatement(
					"insert into s_user(userid,useraccount,username,userpass,useremail,userinfo,userphotourl,useradmin,usersex) values(sq_user.nextval,?,?,?,?,?,?,?,?)");
			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPass());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getInfo());
			ps.setString(6, user.getPhotourl());
			ps.setInt(7, user.getAdmin());
			ps.setString(8, user.getSex());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return row;
	}

	/**
	 * 用户登陆验证
	 * 
	 * @param account
	 * @param pass
	 * @return
	 */
	public E_User judgeUser(String account, String pass) {
		openConnection();
		E_User user = new E_User();
		try {
			ps = con.prepareStatement(
					"select userid,useraccount,username,userpass,useremail,userinfo,userphotourl,useradmin,usersex from s_user where useraccount=?");
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String getpass = rs.getString(4);
				if (getpass.equals(pass)) {
					user.setId(rs.getInt(1));
					user.setAccount(rs.getString(2));
					user.setName(rs.getString(3));
					user.setPass(getpass);
					user.setEmail(rs.getString(5));
					user.setInfo(rs.getString(6));
					user.setPhotourl(rs.getString(7));
					user.setAdmin(rs.getInt(8));
					user.setSex(rs.getString(9));
					user.setCode(1);
				} else {
					// 密码错误
					user.setCode(2);
				}
			} else {
				// 无此账号
				user.setCode(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return user;
	}

	public String updateUser(E_User user) {
		openConnection();
		String path = null;
		try {
			ps = con.prepareStatement(
					"update s_user set username = ?,useremail = ?,userinfo = ?,userphotourl = ?,usersex = ? where userid =?");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getInfo());
			ps.setString(4, user.getPhotourl());
			ps.setString(5, user.getSex());
			ps.setInt(6, user.getId());
			int row = ps.executeUpdate();
			if (row > 0) {
				path = user.getPhotourl();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return path;
	}

	public int changePass(String account, String pass, String email) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("update s_user set userpass = ? where  useraccount =? and useremail = ?");
			ps.setString(1, pass);
			ps.setString(2, account);
			ps.setString(3, email);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return row;
	}

	public int updatePass(int userid, String oldpass, String pass) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("update s_user set userpass = ? where  userid =? and userpass = ?");
			ps.setString(1, pass);
			ps.setInt(2, userid);
			ps.setString(3, oldpass);
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
