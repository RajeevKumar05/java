package com.demo.exception;

@SuppressWarnings("serial")
public class LanguageAlreadyExistsException extends Exception {
	public LanguageAlreadyExistsException(String msg){
		super(msg);
	}
}
