package com.ustb.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BaseDao {
	protected Connection con = null;
	protected PreparedStatement ps = null;

	public void openConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ORCL", "scott", "Bz123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new BaseDao().openConnection();
	}
}
