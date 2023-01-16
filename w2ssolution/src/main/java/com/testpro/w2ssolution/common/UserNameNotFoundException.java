package com.testpro.w2ssolution.common;

@SuppressWarnings("serial")
public class UserNameNotFoundException extends Exception {

	public UserNameNotFoundException(String userName) {
		super(userName);
	}
}
