package com.ustb.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ustb.entity.E_Comment;
import com.ustb.model.BeanData;
import com.ustb.model.CommentData;
import com.ustb.status.StatusCode;

public class CommentParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		CommentData commentData = new CommentData();

		try {
			JSONObject jsonObject = new JSONObject(result);
			int code = jsonObject.getInt("code");
			int flag = jsonObject.getInt("flag");
			commentData.setCode(code);
			commentData.setFlag(flag);
			if (flag == StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_Comment> list = new ArrayList<E_Comment>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_Comment comment = new E_Comment();
					comment.setCid(object.getInt("cid"));
					comment.setCardid(object.getInt("cardid"));
					comment.setUid(object.getInt("uid"));
					comment.setCmess(object.getString("cmess"));
					comment.setUname(object.getString("uname"));
					comment.setUhead(object.getString("uhead"));
					comment.setDate(object.getString("date"));
//					comment.setCtitle(object.getString("ctitle"));
					list.add(comment);
				}
				commentData.setList(list);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return commentData;
	}

}
