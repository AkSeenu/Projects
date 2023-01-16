package com.testpro.w2ssolution.common;

@SuppressWarnings("serial")
public class AccesDeniedException extends RuntimeException{

	public AccesDeniedException(String exc){
		super(exc);
	}
}
