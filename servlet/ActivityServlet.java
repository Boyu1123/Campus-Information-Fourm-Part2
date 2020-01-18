package com.ustb.servlet;

import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Activity;
import com.ustb.entity.E_Join;
import com.ustb.service.ActivityService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActivityServlet
 */
public class ActivityServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public ActivityServlet() {
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
				if ("addActivity".equals(type)) {
					//发布活动
					addActivity(map, request, response);
				}
				if ("getActivity".equals(type)) {
					int acode = Integer.valueOf(request.getParameter("acode"));
					ActivityService activityService = new ActivityService();
					ArrayList<E_Activity> list = activityService.getActivity(acode);
					if (list!=null) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("list", list);
					}else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}
				
				if ("getMyActivity".equals(type)) {
					int userid = Integer.valueOf(request.getParameter("userid"));
					ActivityService activityService = new ActivityService();
					ArrayList<E_Activity> list = activityService.getMyActivity(userid);
					if (list!=null) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("list", list);
					}else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}
				
				if ("endActivity".equals(type)) {
					int aid = Integer.valueOf(request.getParameter("aid"));
					ActivityService activityService = new ActivityService();
					int row = activityService.endActivity(aid);
					if (row>0) {
						map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
					}else {
						map.put("flag", StatusCode.Dao.UPDATE_FAIL);
					}
				}
				
				
				
				return null;
			}
		}, request, response);
	}

	protected void addActivity(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String info = request.getParameter("info");
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String people = request.getParameter("people");
		String mess = request.getParameter("mess");
		String tel = request.getParameter("tel");
		int userid = Integer.valueOf(request.getParameter("userid"));
		E_Activity activity = new E_Activity();
		activity.setAtitle(title);
		activity.setAinfo(info);
		activity.setAstarttime(time);
		activity.setAplace(place);
		activity.setApeople(Integer.valueOf(people));
		activity.setAmess(mess);
		activity.setUserid(userid);
		activity.setAtel(tel);
		
		ActivityService activityService = new ActivityService();
		int row = activityService.addActivity(activity);
		if (row>0) {
			map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
		}else {
			map.put("flag", StatusCode.Dao.INSERT_FAIL);
		}
	}

}
