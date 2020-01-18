package com.ustb.entity;

import java.sql.Date;

public class E_Notice {
	private int nid;
	private String ntitle;
	private String nauthor;
	private String nmess;
	private String nurl;
	private String ndate;
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public String getNmess() {
		return nmess;
	}
	public void setNmess(String nmess) {
		this.nmess = nmess;
	}
	public String getNurl() {
		return nurl;
	}
	public void setNurl(String nurl) {
		this.nurl = nurl;
	}
}
