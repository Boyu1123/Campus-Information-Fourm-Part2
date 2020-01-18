package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.FriendDao;
import com.ustb.entity.E_Friend;

public class FriendService {

	public int addFriend(int userid, int friendid) {
		FriendDao friendDao = new FriendDao();
		return friendDao.addFriend(userid,friendid);
	}

	public int isFriend(int userid, int friendid) {
		FriendDao friendDao = new FriendDao();
		return friendDao.isFriend(userid,friendid);
	}

	public int deleteFriend(int id) {
		FriendDao friendDao = new FriendDao();
		return friendDao.deleteFriend(id);
	}

	public ArrayList<E_Friend> getMyFriend(int userid) {
		FriendDao friendDao = new FriendDao();
		return friendDao.getMyFriend(userid);
	}

}
