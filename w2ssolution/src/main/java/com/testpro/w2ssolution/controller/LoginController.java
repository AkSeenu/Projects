package com.testpro.w2ssolution.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testpro.w2ssolution.common.CommonClass;
import com.testpro.w2ssolution.model.SignInModel;
import com.testpro.w2ssolution.model.UserModel;
import com.testpro.w2ssolution.service.LoginService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("login")
@Log4j2
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private CommonClass commonClass;
	
	@GetMapping("test")
	public String testMethod() {
		return "Hey It's Working";
	}

	@PostMapping("signUp")
	public ResponseEntity<?> SigUpMethod(@RequestBody UserModel userModel) throws Exception {
		try {
			return new ResponseEntity<Map<String,Object>>(loginService.signUpMethod(userModel), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("SignUp Process Stop Due To " + e.getMessage());
			log.error(commonClass.errorTraceForLogger(e.getStackTrace(), e.getMessage()));
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("signIn")
	public ResponseEntity<?> SigInMethod(@RequestBody SignInModel signInModel) {
		try {
			return new ResponseEntity<Map<String,Object>>(loginService.signInMethod(signInModel), HttpStatus.OK);
		} catch (Exception e) {
			log.error("SignIn Process Stop Due To " + e.getMessage());
			log.error(commonClass.errorTraceForLogger(e.getStackTrace(), e.getMessage()));
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
