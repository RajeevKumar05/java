package com.demo.exception;

@SuppressWarnings("serial")
public class LanguageNotFound extends Exception {
	public LanguageNotFound(String msg){
		super(msg);
	}
}
