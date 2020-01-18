package com.ustb.servlet;

import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Card;
import com.ustb.entity.E_Collect;
import com.ustb.service.CollectService;

import oracle.net.aso.r;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CollectServlet
 */
public class CollectServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public CollectServlet() {
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
	protected void doPost(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requsestProcess(new Callback() {
			
			@Override
			public String callback(Map<String, Object> map) {
				String type = request.getParameter("type");
				if ("addCollect".equals(type)) {
					String userid = request.getParameter("userid");
					String cardid = request.getParameter("cardid");
					E_Collect collect = new E_Collect();
					collect.setUserid(Integer.valueOf(userid));
					collect.setCardid(Integer.valueOf(cardid));
					
					CollectService collectService = new CollectService();
					int row = collectService.addCollect(collect);
					if (row>0) {
						map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
						map.put("id", row);
					}else {
						map.put("flag", StatusCode.Dao.INSERT_FAIL);
					}
				}
				
				if ("getCollect".equals(type)) {
					String userid = request.getParameter("userid");
					String cardid = request.getParameter("cardid");
					E_Collect collect = new E_Collect();
					collect.setUserid(Integer.valueOf(userid));
					collect.setCardid(Integer.valueOf(cardid));
					
					CollectService collectService = new CollectService();
					int row = collectService.getCollect(collect);
					if (row>0) {
						map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						map.put("id", row);
					}else {
						map.put("flag", StatusCode.Dao.SELECT_FAIL);
					}
				}
				
				if ("deleteCollect".equals(type)) {
					int id = Integer.valueOf(request.getParameter("id"));
					CollectService collectService = new CollectService();
					int row = collectService.deleteCollect(id);
					if (row>0) {
						map.put("flag", StatusCode.Dao.DELETE_SUCCESS);
					}else {
						map.put("flag", StatusCode.Dao.DELETE_FAIL);
					}
				}
			
				if ("MyCollect".equals(type)) {
					int userid = Integer.valueOf(request.getParameter("userid"));
					CollectService collectService = new CollectService();
					ArrayList<E_Card> list = collectService.MyCollect(userid);
					if (list!=null) {
						map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
						map.put("list", list);
					}else {
						map.put("flag", StatusCode.Dao.UPDATE_FAIL);
					}
				}
				
				return null;
			}
		}, request, response);
	}
	
	
}
