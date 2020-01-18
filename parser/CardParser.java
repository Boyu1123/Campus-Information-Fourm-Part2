package com.ustb.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ustb.entity.E_Card;
import com.ustb.model.BeanData;
import com.ustb.model.CardData;
import com.ustb.status.StatusCode;

public class CardParser extends BeanParser {

	@Override
	public BeanData parser(String result) {
		CardData cardData = new CardData();
		try {
			JSONObject jsonObject = new JSONObject(result);
			int code = jsonObject.getInt("code");
			int flag = jsonObject.getInt("flag");
			cardData.setCode(code);
			cardData.setFlag(flag);

			if (flag == StatusCode.Dao.SELECT_SUCCESS) {
				ArrayList<E_Card> list = new ArrayList<E_Card>();
				JSONArray array = jsonObject.getJSONArray("list");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					E_Card card = new E_Card();
					card.setCardid(object.getInt("cardid"));
					card.setCardtitle(object.getString("cardtitle"));
					card.setCardmess(object.getString("cardmess"));
					card.setCarddate(object.getString("carddate"));
					card.setCardphoto1(object.getString("cardphoto1"));
					card.setUserid(object.getInt("userid"));
					card.setUsername(object.getString("username"));
					card.setNum(object.getInt("num"));
					list.add(card);
				}
				cardData.setList(list);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cardData;
	}

}
