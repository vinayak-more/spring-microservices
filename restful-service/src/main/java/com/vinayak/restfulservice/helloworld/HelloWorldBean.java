package com.vinayak.restfulservice.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean() {
		super();
	}

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
