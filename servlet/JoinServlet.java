package com.ustb.servlet;

import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Join;
import com.ustb.service.ActivityService;
import com.ustb.service.JoinService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
public class JoinServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requsestProcess(new Callback() {
			
			@Override
			public String callback(Map<String, Object> map) {
				String type = request.getParameter("type");
				if ("joinActivity".equals(type)) {
					int aid = Integer.valueOf(request.getParameter("aid"));
					String joinname = request.getParameter("joinname");
					String jointel = request.getParameter("jointel");
					int userid = Integer.valueOf(request.getParameter("userid"));
					E_Join join = new E_Join();
					join.setAid(aid);
					join.setJname(joinname);
					join.setJtel(jointel);
					join.setUserid(userid);
					JoinService joinService = new JoinService();
					int row = joinService.joinActivity(join);
					if (row>0) {
						map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
					}else {
						map.put("flag", StatusCode.Dao.INSERT_FAIL);
					}
					
				}
				if ("getJoin".equals(type)) {
					int aid = Integer.valueOf(request.getParameter("aid"));
					JoinService joinService = new JoinService();
					ArrayList<E_Join> list = joinService.getJoin(aid);
					if (list!=null) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("list", list);
					}else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}
				if ("isJoin".equals(type)) {
					int aid = Integer.valueOf(request.getParameter("aid"));
					int userid = Integer.valueOf(request.getParameter("userid"));
					JoinService joinService = new JoinService();
					int row = joinService.isJoin(aid,userid);
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

}
