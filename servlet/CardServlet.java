package com.ustb.servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Card;
import com.ustb.entity.E_News;
import com.ustb.service.CardService;
import com.ustb.service.NewsService;

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
 * Servlet implementation class CardServlet
 */
public class CardServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public CardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				if (type == null) {
					//发帖
					addCard(map, request, response);
				}
				if ("getCard".equals(type)) {
					CardService cardService = new CardService();
					ArrayList<E_Card> list = cardService.getCard();
					if (list!=null) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("list", list);
					}else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}
				if ("addNum".equals(type)) {
					String cardid = request.getParameter("cardid");
					CardService cardService = new CardService();
					int row = cardService.addNum(Integer.valueOf(cardid));
					if (row>0) {
						map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
					}else {
						map.put("flag", StatusCode.Dao.UPDATE_FAIL);
					}
				}
				if ("getMyCard".equals(type)) {
					int userid = Integer.valueOf(request.getParameter("userid"));
					CardService cardService = new CardService();
					ArrayList<E_Card> list = cardService.getMyCard(userid);
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
	private void addCard(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
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
			String userid = smartUpload.getRequest().getParameter("userid");
			File file = smartUpload.getFiles().getFile(0);
//			File files = smartUpload.getFiles().getFile(1);
			// smartUpload.getFiles().getCount();
			// 随机文件名
			String fileName = UUID.randomUUID().toString() + "." + file.getFileExt();
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
			String dmd = date.format(new Date(System.currentTimeMillis()));
			java.io.File file1 = new java.io.File(request.getServletContext().getRealPath("upload") + "/card/" + dmd);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			String path1 = "upload/card/" + dmd + "/" + fileName;
			file.saveAs(path1);
/*
			String fileName1 = UUID.randomUUID().toString() + "." + files.getFileExt();
			java.io.File file2 = new java.io.File(request.getServletContext().getRealPath("upload") + "/news/" + dmd);
			if (!file2.exists()) {
				file2.mkdirs();
			}
			String path2 = "upload/news/" + dmd + "/" + fileName1;
			files.saveAs(path2);
*/
			E_Card card = new E_Card();
			card.setCardtitle(title);
			card.setCardmess(mess);
			card.setCardphoto1(path1);
			card.setUserid(Integer.valueOf(userid));
			
			CardService cardService = new CardService();
			int row = cardService.addCard(card);
			if (row > 0) {
				map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
			} else {
				map.put("flag", StatusCode.Dao.INSERT_FAIL);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
