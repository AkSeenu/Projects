package com.testpro.w2ssolution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.testpro.w2ssolution.model.PayLoadData;

@Configuration
public class CustomWebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private WebRequestInterceptor requestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}

	@Bean
	@RequestScope
	PayLoadData getPayLoadData() {
		return new PayLoadData();
	}

	@Bean
	Interceptor interceptor() {
		return new Interceptor(requestInterceptor, getPayLoadData());
	}

}
