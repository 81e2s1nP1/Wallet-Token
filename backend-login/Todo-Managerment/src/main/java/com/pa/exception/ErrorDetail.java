package com.pa.exception;

import java.time.LocalDateTime;

public class ErrorDetail {
	private LocalDateTime timeStamp;
	private String msg;
	private String details;
	
	public ErrorDetail() {
	}
	
	public ErrorDetail(LocalDateTime timeStamp, String msg, String details) {
		this.timeStamp = timeStamp;
		this.msg = msg;
		this.details = details;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
