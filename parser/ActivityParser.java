package com.ustb.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ustb.entity.E_Activity;
import com.ustb.model.ActivityData;
import com.ustb.model.BeanData;
import com.ustb.status.StatusCode;

public class ActivityParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		ActivityData activityData = new ActivityData();
	
		try {
			JSONObject jsonObject = new JSONObject(result);
			int code = jsonObject.getInt("code");
			int flag = jsonObject.getInt("flag");
			activityData.setCode(code);
			activityData.setFlag(flag);
			
			if (flag==StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_Activity> list = new ArrayList<E_Activity>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_Activity activity = new E_Activity();
					activity.setAid(object.getInt("aid"));
					activity.setAtitle(object.getString("atitle"));
					activity.setAinfo(object.getString("ainfo"));
					activity.setAstarttime(object.getString("astarttime"));
					activity.setAplace(object.getString("aplace"));
					activity.setApeople(object.getInt("apeople"));
					activity.setAmess(object.getString("amess"));
					activity.setUserid(object.getInt("userid"));
					activity.setUsername(object.getString("username"));
					activity.setAcode(object.getInt("acode"));
					activity.setDate(object.getString("date"));
					activity.setAtel(object.getString("atel"));
					activity.setAhave(object.getInt("ahave"));
					list.add(activity);
				}
				activityData.setList(list);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return activityData;
	}

}
