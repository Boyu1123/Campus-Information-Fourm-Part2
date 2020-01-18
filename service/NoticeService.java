package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.NoticeDao;
import com.ustb.entity.E_Notice;

public class NoticeService {

	public int addNotice(E_Notice notice) {
		NoticeDao noticeDao = new NoticeDao();
		return noticeDao.addNotice(notice);
	}

	public ArrayList<E_Notice> getNotice() {
		NoticeDao noticeDao = new NoticeDao();
		return noticeDao.getNotice();
	}

}
