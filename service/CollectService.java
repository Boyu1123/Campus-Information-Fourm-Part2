package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.CollectDao;
import com.ustb.entity.E_Card;
import com.ustb.entity.E_Collect;

public class CollectService {

	public int addCollect(E_Collect collect) {
		CollectDao collectDao = new CollectDao();
		return collectDao.addCollect(collect);
	}

	public int getCollect(E_Collect collect) {
		CollectDao collectDao = new CollectDao();
		return collectDao.getCollect(collect);
	}

	public int deleteCollect(int id) {
		CollectDao collectDao = new CollectDao();
		return collectDao.deleteCollect(id);
	}

	public ArrayList<E_Card> MyCollect(int userid) {
		CollectDao collectDao = new CollectDao();
		return collectDao.MyCollect(userid);
	}

}
