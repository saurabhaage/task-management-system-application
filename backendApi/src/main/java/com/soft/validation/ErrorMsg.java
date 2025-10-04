package com.soft.validation;

import java.util.Date;

public class ErrorMsg {

	private int portNo;
	private String msg;
	private Date date;
	
	
	
	public ErrorMsg(int portNo, String msg, Date date) {
		super();
		this.portNo = portNo;
		this.msg = msg;
		this.date = date;
	}
	public int getPortNo() {
		return portNo;
	}
	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
