package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.JoinDao;
import com.ustb.entity.E_Join;

public class JoinService {
	

	public int joinActivity(E_Join join) {
		JoinDao joinDao = new JoinDao();
		return joinDao.joinActivity(join);
	}

	public ArrayList<E_Join> getJoin(int aid) {
		JoinDao joinDao = new JoinDao();
		return joinDao.getJoin(aid);
	}

	public int isJoin(int aid, int userid) {
		JoinDao joinDao = new JoinDao();
		return joinDao.isJoin(aid,userid);
	}

}
