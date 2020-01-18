package com.ustb.parser;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ustb.entity.E_News;
import com.ustb.model.BeanData;
import com.ustb.model.NewsData;
import com.ustb.status.StatusCode;

public class NewsParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		NewsData data = new NewsData();
		try {
			JSONObject jsonObject = new JSONObject(result);
			int flag = jsonObject.getInt("flag");
			int code = jsonObject.getInt("code");
			data.setCode(code);
			data.setFlag(flag);

			if (flag == StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_News> list = new ArrayList<E_News>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_News news = new E_News();
					news.setNewsid(object.getInt("newsid"));
					news.setNewstitle(object.getString("newstitle"));
					news.setNewsmess(object.getString("newsmess"));
					news.setNewsphoto1(object.getString("newsphoto1"));
//					news.setNewsphoto2(object.getString("newsphoto2"));
					news.setNewsauthor(object.getString("newsauthor"));
					news.setNewsdate(object.getString("newsdate"));
					list.add(news);
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
