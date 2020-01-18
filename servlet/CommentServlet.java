package com.ustb.servlet;

import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_Comment;
import com.ustb.service.CommentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public CommentServlet() {
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
				if ("addComment".equals(type)) {
					String carid = request.getParameter("cardid");
					String userid = request.getParameter("userid");
					String mess = request.getParameter("mess");
					E_Comment comment = new E_Comment();
					comment.setCardid(Integer.valueOf(carid));
					comment.setUid(Integer.valueOf(userid));
					comment.setCmess(mess);
					
					CommentService commentService = new CommentService();
					int row = commentService.addComment(comment);
					if (row>0) {
						map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
					}else {
						map.put("flag", StatusCode.Dao.INSERT_FAIL);
					}
				}
				
				if ("getComment".equals(type)) {
					int cardid = Integer.valueOf(request.getParameter("cardid"));
					CommentService commentService = new CommentService();
					ArrayList<E_Comment> list = commentService.getComment(cardid);
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

}
