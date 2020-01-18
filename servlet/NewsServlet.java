package com.ustb.servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_News;
import com.ustb.entity.E_Notice;
import com.ustb.service.NewsService;
import com.ustb.service.NoticeService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public NewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
	
		requsestProcess(new Callback() {
			
			@Override
			public String callback(Map<String, Object> map) {
				String type = request.getParameter("type");
				if (type==null) {
					//增加新闻
					addNews(map, request, response);
				}
				if ("getNews".equals(type)) {
					NewsService newsService = new NewsService();
					ArrayList<E_News> list = newsService.getNews();
					if (list!=null) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("list", list);
					}else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}
				return null;
			}
		}, request, response);
	}

	/**
	 * 带有上传文件的操作
	 * 
	 * @param map
	 * @param request
	 * @param response
	 */
	private void addNews(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SmartUpload smartUpload = new SmartUpload();
		try {
			smartUpload.initialize(getServletConfig(), request, response);
			smartUpload.upload();

			String title = smartUpload.getRequest().getParameter("title");
			String mess = smartUpload.getRequest().getParameter("mess");
			String author = smartUpload.getRequest().getParameter("author");
			File file = smartUpload.getFiles().getFile(0);
			File files = smartUpload.getFiles().getFile(1);
			// smartUpload.getFiles().getCount();
			// 随机文件名
			String fileName = UUID.randomUUID().toString() + "." + file.getFileExt();
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
			String dmd = date.format(new Date(System.currentTimeMillis()));
			java.io.File file1 = new java.io.File(request.getServletContext().getRealPath("upload") + "/news/" + dmd);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			String path1 = "upload/news/" + dmd + "/" + fileName;
			file.saveAs(path1);
			
			String fileName1 = UUID.randomUUID().toString() + "." + files.getFileExt();
			java.io.File file2 = new java.io.File(request.getServletContext().getRealPath("upload") + "/news/" + dmd);
			if (!file2.exists()) {
				file2.mkdirs();
			}
			String path2 = "upload/news/" + dmd + "/" + fileName1;
			files.saveAs(path2);

			E_News news = new E_News();
			news.setNewstitle(title);
			news.setNewsmess(mess);
			news.setNewsauthor(author);
			news.setNewsphoto1(path1);
			news.setNewsphoto2(path2);
			
			NewsService newsService = new NewsService();
			int row = newsService.addNews(news);
			if (row > 0) {
				map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
				// map.put("res", "success");
			} else {
				map.put("flag", StatusCode.Dao.INSERT_FAIL);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
