package com.ustb.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ustb.entity.E_Friend;
import com.ustb.model.BeanData;
import com.ustb.model.FriendData;
import com.ustb.status.StatusCode;

public class FriendParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		FriendData data = new FriendData();
		try {
			JSONObject jsonObject = new JSONObject(result);
			int code = jsonObject.getInt("code");
			int flag = jsonObject.getInt("flag");
			data.setCode(code);
			data.setFlag(flag);
			if (flag == StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_Friend> list = new ArrayList<E_Friend>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_Friend friend = new E_Friend();
					friend.setId(object.getInt("id"));
					friend.setFriendid(object.getInt("friendid"));
					friend.setFriendname(object.getString("friendname"));
					friend.setFriendinfo(object.getString("friendinfo"));
					friend.setFriendhead(object.getString("friendhead"));
//					Log.d("test", object.getString("friendinfo"));
					list.add(friend);
				}
				data.setList(list);
			}
			if (flag == StatusCode.Dao.UPDATE_SUCCESS) {
				int id = jsonObject.getInt("id");
				data.setId(id);
			}
			if (flag == StatusCode.Dao.INSERT_SUCCESS) {
				int id = jsonObject.getInt("id");
				data.setId(id);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
