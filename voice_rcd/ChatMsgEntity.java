package com.ustb.voice_rcd;

import com.ustb.entity.E_Friend;
import com.ustb.entity.E_User;

public class ChatMsgEntity {
	private static final String TAG = ChatMsgEntity.class.getSimpleName();

	private String name;

	private String date;

	private String text;

	private String time;

	private E_User user;

	private String head;
	

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public E_User getUser() {
		return user;
	}

	public void setUser(E_User user) {
		this.user = user;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private boolean isComMeg = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getMsgType() {
		return isComMeg;
	}

	public void setMsgType(boolean isComMsg) {
		isComMeg = isComMsg;
	}

	public ChatMsgEntity() {
	}

	public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
		super();
		this.name = name;
		this.date = date;
		this.text = text;
		this.isComMeg = isComMsg;
	}

}
