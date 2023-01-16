package com.testpro.w2ssolution.service;

import java.util.Map;

import com.testpro.w2ssolution.model.SignInModel;
import com.testpro.w2ssolution.model.UserModel;

public interface LoginService {
	
	
	Map<String, Object> signUpMethod(UserModel userModel) throws Exception;

	Map<String, Object> signInMethod(SignInModel signInModel) throws Exception;
	
	
}
