package com.testpro.w2ssolution.common;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class CommonClass {

	public String errorTraceForLogger(StackTraceElement[] strackTraceArray, String str) {
		String returnValue = str + "\n";
		List<StackTraceElement> listStackTrace = Arrays.asList(strackTraceArray);
		for (StackTraceElement trace : listStackTrace) {
			if ((trace.getClassName().contains("com.testpro.w2ssolution"))
					&& !trace.getClassName().contains("$")) {
				returnValue += trace.getClassName() + ":" + trace.getLineNumber() + "\n";
			}
		}
		
		return returnValue;
	}
}
