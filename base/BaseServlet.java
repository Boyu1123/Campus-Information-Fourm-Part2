package com.ustb.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.exception.PageException;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENTYPE_HTML = "text/html;charset=UTF-8";
	private static final String CONTENTYPE_JSON = "application/json;charset=UTF-8";
	// gsonĿ���ǰ�java����ת��Ϊgson����
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// ʵ�ִ���������
	// 1.�����ظ� 2.�ܹ��ó���Աд����
	public void requsestProcess(Callback call, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> map = new HashMap<>();
		String result = null;
		int status = StatusCode.Common.SUCCESS;
		try {
			result = call.callback(map);
		} catch (Exception e) {
			status = StatusCode.Common.FAIL;
			e.printStackTrace();
		}
		map.put("code", status);
		/*
		 * ���ؿձ�ʾ��null ���Ϊ�գ���ҳ��ת��(jsp)
		 */
		if (result == null) {
			response.setContentType(CONTENTYPE_JSON);
			String json = gson.toJson(map);
//			System.out.println(json);
			try {
				
				response.getWriter().write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (result != null) {
			response.setContentType(CONTENTYPE_HTML);
			if (result.equals("")) {
				throw new PageException("fail");
			}

			request.setAttribute("map", map);
			try {

				request.getRequestDispatcher(result).forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
