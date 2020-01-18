package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.ActivityDao;
import com.ustb.entity.E_Activity;
import com.ustb.entity.E_Join;

public class ActivityService {

	public int addActivity(E_Activity activity) {
		ActivityDao activityDao = new ActivityDao();
		return activityDao.addActivity(activity);
	}

	public ArrayList<E_Activity> getActivity(int acode) {
		ActivityDao activityDao = new ActivityDao();
		return activityDao.getActivity(acode);
	}

	public ArrayList<E_Activity> getMyActivity(int userid) {
		ActivityDao activityDao = new ActivityDao();
		return activityDao.getMyActivity(userid);
	}

	public int endActivity(int aid) {
		ActivityDao activityDao = new ActivityDao();
		return activityDao.endActivity(aid);
	}

	

}
