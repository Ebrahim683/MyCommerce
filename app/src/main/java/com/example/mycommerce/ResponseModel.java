package com.example.mycommerce;

public class ResponseModel {

	String message;

	public ResponseModel() {
	}

	public ResponseModel(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
