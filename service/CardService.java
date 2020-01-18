package com.ustb.service;

import java.util.ArrayList;

import com.ustb.dao.CardDao;
import com.ustb.entity.E_Card;

public class CardService {

	public int addCard(E_Card card) {
		CardDao cardDao = new CardDao();
		return cardDao.addCard(card);
	}

	public ArrayList<E_Card> getCard() {
		CardDao cardDao = new CardDao();
		return cardDao.getCard();
	}

	public int addNum(Integer valueOf) {
		CardDao cardDao = new CardDao();
		return cardDao.addNum(valueOf);
	}

	public ArrayList<E_Card> getMyCard(int userid) {
		CardDao cardDao = new CardDao();
		return cardDao.getMyCard(userid);
	}

}
