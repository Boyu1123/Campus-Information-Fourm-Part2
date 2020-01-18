package com.ustb.service;

import com.ustb.dao.UserDao;
import com.ustb.entity.E_User;

public class UserService {

	public int judgeAccount(String account) {
		UserDao userDao = new UserDao();
		return userDao.judgeAccount(account);
	}

	public int addUser(E_User user) {
		UserDao userDao = new UserDao();
		return userDao.addUser(user);
	}

	public E_User judgeUser(String account, String pass) {
		UserDao userDao = new UserDao();
		return userDao.judgeUser(account,pass);
	}

	public String updateUser(E_User user) {
		UserDao userDao = new UserDao();
		return userDao.updateUser(user);
	}

	public int changePass(String account, String pass, String email) {
		UserDao userDao = new UserDao();
		return userDao.changePass(account,pass,email);
	}

	public int updatePass(int userid, String oldpass, String pass) {
		UserDao userDao = new UserDao();
		return userDao.updatePass(userid,oldpass,pass);
	}

}
