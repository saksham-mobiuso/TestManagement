package com.mobi.exceptions;

import java.util.Date;

public class CustomError{
	private Date timeStamp;
	private Object status;
	private Object response;
	
	
	public CustomError() {
		super();
		
	}

	public CustomError(Date timeStamp, Object status, Object response) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.response = response;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	

}
