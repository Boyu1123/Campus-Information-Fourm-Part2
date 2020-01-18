package com.ustb.servlet;

import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Friend;
import com.ustb.service.FriendService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FriendServlet
 */
public class FriendServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public FriendServlet() {
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
	protected void doPost(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requsestProcess(new Callback() {

			@Override
			public String callback(Map<String, Object> map) {
				String type = request.getParameter("type");
				if ("addFriend".equals(type)) {
					int userid = Integer.valueOf(request.getParameter("userid"));
					int friendid = Integer.valueOf(request.getParameter("friendid"));
					FriendService friendService = new FriendService();
					int row = friendService.addFriend(userid, friendid);
					if (row > 0) {
						map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
						map.put("id", row);
					} else {
						map.put("flag", StatusCode.Dao.INSERT_FAIL);
					}

				}
				if ("deleteFriend".equals(type)) {
					int id = Integer.valueOf(request.getParameter("id"));
					FriendService friendService = new FriendService();
					int row = friendService.deleteFriend(id);
					if (row > 0) {
						map.put("flag", StatusCode.Dao.DELETE_SUCCESS);
					} else {
						map.put("flag", StatusCode.Dao.DELETE_FAIL);
					}
				}

				if ("getFriend".equals(type)) {
					int userid = Integer.valueOf(request.getParameter("userid"));
					FriendService friendService = new FriendService();
					ArrayList<E_Friend> list = friendService.getMyFriend(userid);
					if (list != null) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("list", list);
					} else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}

				if ("isFriend".equals(type)) {
					int userid = Integer.valueOf(request.getParameter("userid"));
					int friendid = Integer.valueOf(request.getParameter("friendid"));
					FriendService friendService = new FriendService();
					int row = friendService.isFriend(userid, friendid);
					if (row > 0) {
						map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
						map.put("id", row);
					} else {
						map.put("flag", StatusCode.Dao.UPDATE_FAIL);
					}
				}

				return null;
			}
		}, request, response);
	}

}
