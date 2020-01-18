package com.ustb.servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.sun.corba.se.spi.orbutil.fsm.State;
import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Notice;
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
 * Servlet implementation class NoticeServlet
 */
public class NoticeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		requsestProcess(new Callback() {
			
			@Override
			public String callback(Map<String, Object> map) {
				String type = request.getParameter("type");
				if (type==null) {
					//上传通知
					addNotice(map, request, response);
				}
				if ("getNotice".equals(type)) {
					NoticeService noticeService = new NoticeService();
					ArrayList<E_Notice> list = noticeService.getNotice();
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
	private void addNotice(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
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
//			smartUpload.getFiles().getCount();
			// 随机文件名
			String fileName = UUID.randomUUID().toString() + "." + file.getFileExt();
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
			String dmd = date.format(new Date(System.currentTimeMillis()));
			java.io.File file1 = new java.io.File(request.getServletContext().getRealPath("upload") + "/notice/" + dmd);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			String path = "upload/notice/" + dmd + "/" + fileName;
			file.saveAs(path);
			
			E_Notice notice = new E_Notice();
			notice.setNtitle(title);
			notice.setNmess(mess);
			notice.setNauthor(author);
			notice.setNurl(path);
			
			NoticeService noticeService = new NoticeService();
			int row = noticeService.addNotice(notice);
			if (row>0) {
				map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
//				map.put("res", "success");
			}else {
				map.put("flag", StatusCode.Dao.INSERT_FAIL);
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
