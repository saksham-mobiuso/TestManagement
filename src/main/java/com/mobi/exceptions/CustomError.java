package com.mobi.exceptions;

import java.util.Date;

public class CustomError{
	private Date timeStamp;
	private String message;
	
	
	public CustomError() {
		super();
		
	}
	public CustomError(Date timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
