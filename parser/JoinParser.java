package com.ustb.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ustb.entity.E_Join;
import com.ustb.model.BeanData;
import com.ustb.model.JoinData;
import com.ustb.status.StatusCode;

public class JoinParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		JoinData data = new JoinData();
		try {
			JSONObject jsonObject = new JSONObject(result);
			int code = jsonObject.getInt("code");
			int flag = jsonObject.getInt("flag");
			data.setCode(code);
			data.setFlag(flag);
			if (flag == StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_Join> list = new ArrayList<E_Join>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_Join join = new E_Join();
					join.setAid(object.getInt("aid"));
					join.setJid(object.getInt("jid"));
					join.setJname(object.getString("jname"));
					join.setJtel(object.getString("jtel"));
					join.setUserid(object.getInt("userid"));
					list.add(join);
				}
				data.setList(list);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
