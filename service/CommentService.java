package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.CommentDao;
import com.ustb.entity.E_Comment;

public class CommentService {

	public int addComment(E_Comment comment) {
		CommentDao commentDao = new CommentDao();
		return commentDao.addComment(comment);
	}

	public ArrayList<E_Comment> getComment(int cardid) {
		CommentDao commentDao = new CommentDao();
		return commentDao.getComment(cardid);
	}

}
