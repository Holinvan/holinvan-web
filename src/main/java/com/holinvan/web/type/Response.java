package com.holinvan.web.type;

public class Response {
	
	private ResponseStatus status;
	private String messageCode;
	
	public Response(ResponseStatus status, String messageCode) {
		super();
		this.status = status;
		this.messageCode = messageCode;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
}
