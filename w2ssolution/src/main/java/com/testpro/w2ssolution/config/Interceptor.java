package com.testpro.w2ssolution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.testpro.w2ssolution.common.AccesDeniedException;
import com.testpro.w2ssolution.model.PayLoadData;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Interceptor extends WebRequestHandlerInterceptorAdapter {

	@Autowired
	private JwtClassConfig jwtClassConfig;

	private PayLoadData payLoadData;

	public Interceptor(WebRequestInterceptor requestInterceptor, PayLoadData payLoadData) {
		super(requestInterceptor);
		this.payLoadData = payLoadData;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws AccesDeniedException, Exception {
		Claims claims;
		try {

			if (!(request.getRequestURI().contains("signUp") || request.getRequestURI().contains("signIn")
					|| request.getRequestURI().contains("test"))) {

				claims = jwtClassConfig.verifyToken(request.getHeader("authorization"));

				payLoadData.setName(claims.get("name").toString());

				payLoadData.setEmailId(claims.get("emailId").toString());

				payLoadData.setType(claims.get("type").toString());
			}

			return super.preHandle(request, response, handler);
		} catch (AccesDeniedException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			claims = null;
		}
	}

}
