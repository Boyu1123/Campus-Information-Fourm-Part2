package com.ustb.parser;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ustb.entity.E_Notice;
import com.ustb.model.BeanData;
import com.ustb.model.NoticeData;
import com.ustb.status.StatusCode;

public class NoticeParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		NoticeData noticeData = new NoticeData();
		try {
			JSONObject jsonObject = new JSONObject(result);
			int flag = jsonObject.getInt("flag");
			int code = jsonObject.getInt("code");
			noticeData.setCode(code);
			noticeData.setFlag(flag);

			if (flag == StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_Notice> list = new ArrayList<E_Notice>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_Notice notice = new E_Notice();
					notice.setNid(object.getInt("nid"));
					notice.setNtitle(object.getString("ntitle"));
					notice.setNmess(object.getString("nmess"));
					notice.setNauthor(object.getString("nauthor"));
					notice.setNurl(object.getString("nurl"));
					notice.setNdate(object.getString("ndate"));
				
					list.add(notice);
				}
				noticeData.setList(list);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noticeData;
	}

}
