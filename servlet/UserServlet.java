package com.ustb.servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.ustb.base.BaseServlet;
import com.ustb.common.Callback;
import com.ustb.common.StatusCode;
import com.ustb.entity.E_User;
import com.ustb.service.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public UserServlet() {
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
		// TODO Auto-generated method stub

		requsestProcess(new Callback() {

			@Override
			public String callback(Map<String, Object> map) {

				String type = request.getParameter("type");
				if (type == null) {
					// 带有用户上传头像的操作
					uploadUser(map, request, response);

				} else {

					// 判断用户名是否存在
					if ("judgeAccount".equals(type)) {
						String account = request.getParameter("account");
						UserService userService = new UserService();
						int row = userService.judgeAccount(account);
						if (row == 1) {
							map.put("flag", StatusCode.Dao.SELECT_SUCCESS);
						} else {
							map.put("flag", StatusCode.Dao.SELECT_FAIL);
						}
					}

					// 验证用户信息用于登录
					if ("judgeUser".equals(type)) {
						judgeUser(map, request, response);
					}

					// 用户忘记密码进行修改
					if ("changePass".equals(type)) {
						changePass(map, request, response);
					}
					// 更新用户信息不带头像
					if ("updateUser".equals(type)) {
						updateUser(map, request, response);
					}
					// 修改密码
					if ("updatePass".equals(type)) {
						updatePass(map, request, response);
					}

				}

				return null;
			}
		}, request, response);
	}

	/**
	 * 修改密码
	 * @param map
	 * @param request
	 * @param response
	 */
	protected void updatePass(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		String oldpass = request.getParameter("oldpass");
		int userid = Integer.valueOf(request.getParameter("userid"));
		String pass = request.getParameter("pass");
//		System.out.println(oldpass+"+"+userid+"+"+pass);
		UserService userService = new UserService();
		int row = userService.updatePass(userid,oldpass,pass);
		if (row>0) {
			map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
		}else {
			map.put("flag", StatusCode.Dao.INSERT_FAIL);
		}
	}

	/**
	 * 忘记密码后进行修改密码
	 * 
	 * @param map
	 * @param request
	 * @param response
	 */
	protected void changePass(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		UserService service = new UserService();
		int row = service.changePass(account, pass, email);
		if (row > 0) {
			map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
		} else {
			map.put("flag", StatusCode.Dao.UPDATE_FAIL);
		}
	}

	/**
	 * 修改用户数据
	 * 
	 * @param map
	 * @param request
	 * @param response
	 */
	protected void updateUser(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");
		String photourl = request.getParameter("photourl");
		int id = Integer.valueOf(request.getParameter("id"));

		E_User user = new E_User();
		user.setId(id);
		user.setName(name);
		user.setInfo(info);
		user.setSex(sex);
		user.setPhotourl(photourl);
		user.setEmail(email);

		UserService service = new UserService();
		String newpath = service.updateUser(user);
		if (newpath != null) {
			map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
			map.put("path", newpath);
		} else {
			map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
		}
	}

	/**
	 * 带有上传文件的操作
	 * 
	 * @param map
	 * @param request
	 * @param response
	 */
	private void uploadUser(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
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
			String type = smartUpload.getRequest().getParameter("type");
			String account = smartUpload.getRequest().getParameter("account");
			String name = smartUpload.getRequest().getParameter("name");
			String pass = smartUpload.getRequest().getParameter("pass");
			String email = smartUpload.getRequest().getParameter("email");
			String sex = smartUpload.getRequest().getParameter("sex");
			String info = smartUpload.getRequest().getParameter("info");
			String admin = smartUpload.getRequest().getParameter("admin");
			File file = smartUpload.getFiles().getFile(0);

			// 随机文件名
			String fileName = UUID.randomUUID().toString() + "." + file.getFileExt();
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
			String dmd = date.format(new Date(System.currentTimeMillis()));
			java.io.File file1 = new java.io.File(request.getServletContext().getRealPath("upload") + "/user/" + dmd);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			String path = "upload/user/" + dmd + "/" + fileName;
			file.saveAs(path);

			E_User user = new E_User();
			user.setAccount(account);
			user.setName(name);
			user.setPass(pass);
			user.setEmail(email);
			user.setInfo(info);
			user.setPhotourl(path);
			user.setSex(sex);
			user.setAdmin(Integer.valueOf(admin));

			// 增加用户时
			if ("addUser".equals(type)) {

				UserService userService = new UserService();
				int row = userService.addUser(user);
				if (row > 0) {
					map.put("flag", StatusCode.Dao.INSERT_SUCCESS);
				} else {
					map.put("flag", StatusCode.Dao.INSERT_FAIL);
				}
			}

			// 修改用户时
			if ("updateUser".equals(type)) {
				user.setId(Integer.parseInt(smartUpload.getRequest().getParameter("id")));
				UserService userService = new UserService();
				String newpath = userService.updateUser(user);
				if (newpath != null) {
					map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
					map.put("path", newpath);
				} else {
					map.put("flag", StatusCode.Dao.UPDATE_SUCCESS);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 验证用户
	 * 
	 * @param map
	 * @param request
	 * @param response
	 */
	private void judgeUser(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String pass = request.getParameter("pass");
		UserService userService = new UserService();
		E_User user = userService.judgeUser(account, pass);
		switch (user.getCode()) {
		case 1:
			map.put("flag", StatusCode.Login.LOGIN_SUCCESS);
			map.put("user", user);
			break;
		case 2:
			map.put("flag", StatusCode.Login.LOGIN_PASS_FAIL);
			break;
		case 3:
			map.put("flag", StatusCode.Login.LOGIN_ACCOUNT_FAIL);
			break;
		}

	}
}
