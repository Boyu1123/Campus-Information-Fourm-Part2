package com.ustb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ustb.base.BaseDao;
import com.ustb.entity.E_Comment;

public class CommentDao extends BaseDao {

	public int addComment(E_Comment comment) {
		openConnection();
		int row = 0;
		try {
			ps = con.prepareStatement("insert into s_comment(cid,cmess,userid,cardid,cdate) values(sq_comment.nextval,?,?,?,?)");
			ps.setString(1, comment.getCmess());
			ps.setInt(2, comment.getUid());
			ps.setInt(3, comment.getCardid());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			ps.setString(4, df.format(new Date()));
			row = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return row;
	}

	public ArrayList<E_Comment> getComment(int cardid) {
		ArrayList<E_Comment> list= null;
		openConnection();
		try {
			list = new ArrayList<>();
			ps = con.prepareStatement("select c.cid,c.cmess,c.cdate,c.userid,u.username,u.userphotourl from s_comment c left outer join s_user u on c.userid=u.userid where c.cardid=?");
			ps.setInt(1, cardid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				E_Comment comment = new E_Comment();
				comment.setCid(rs.getInt(1));
				comment.setCmess(rs.getString(2));
				comment.setDate(rs.getString(3));
				comment.setUid(rs.getInt(4));
				comment.setUname(rs.getString(5));
				comment.setUhead(rs.getString(6));
				list.add(comment);
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
