package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.NewsDao;
import com.ustb.entity.E_News;

public class NewsService {

	public int addNews(E_News news) {
		NewsDao dao = new NewsDao();
		return dao.addNews(news);
	}

	public ArrayList<E_News> getNews() {
		NewsDao dao = new NewsDao();
		return dao.getNews();
	}

}
