package com.ustb.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.ustb.entity.E_User;
import com.ustb.model.BeanData;
import com.ustb.model.UserData;
import com.ustb.status.StatusCode;

public class UserParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		UserData userData = new UserData();
		try {
			JSONObject jsonObject = new JSONObject(result);
			int code = jsonObject.getInt("code");
			int flag = jsonObject.getInt("flag");
			userData.setCode(code);
			userData.setFlag(flag);

			if (flag == StatusCode.Login.LOGIN_SUCCESS) {
				JSONObject juser = jsonObject.getJSONObject("user");
				E_User user = new E_User();
				user.setId(juser.getInt("id"));
				user.setAccount(juser.getString("account"));
				user.setName(juser.getString("name"));
				user.setPass(juser.getString("pass"));
				user.setEmail(juser.getString("email"));
				user.setInfo(juser.getString("info"));
				user.setSex(juser.getString("sex"));
				user.setPhotourl(juser.getString("photourl"));
				user.setAdmin(juser.getInt("admin"));
				
				userData.setUser(user);
			}
			if (flag==StatusCode.Dao.UPDATE_SUCCESS) {
				String path = jsonObject.getString("path");
				userData.setPhotourl(path);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userData;
	}

}
