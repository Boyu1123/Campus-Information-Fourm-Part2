package com.ustb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.ustb.push.MqttBroker;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChatServlet
 */
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");
		if (message == null) {
			// �յ�������

			SmartUpload smartUpload = new SmartUpload();
			try {
				smartUpload.initialize(getServletConfig(), request, response);
				smartUpload.upload();
				String deviceId = smartUpload.getRequest().getParameter("deviceId");
				File file = smartUpload.getFiles().getFile(0);

				// ����ļ�����
				String fileName = UUID.randomUUID().toString() + "." + file.getFileExt();

				SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
				String dmd = date.format(new Date(System.currentTimeMillis()));
				java.io.File file1 = new java.io.File(
						request.getServletContext().getRealPath("upload") + "/yuyin/" + dmd);
				if (!file1.exists()) {
					file1.mkdirs();
				}
				String path = "upload/yuyin/" + dmd + "/" + fileName;

				file.saveAs(path);

				JSONObject json = new JSONObject();
				json.put("message", path);
				MqttBroker.getInstance().sendMessage(deviceId, json.toString());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			String deviceId = request.getParameter("deviceId");
			JSONObject json = new JSONObject();
			json.put("message", message);
			MqttBroker.getInstance().sendMessage(deviceId, json.toString());
		}
	}

}
