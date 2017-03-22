package com.example.myapp.util.actions;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoggingInterceptor implements Interceptor {

	private static final long serialVersionUID = 294058339606947197L;

	private static Logger log = Logger.getLogger(LoggingInterceptor.class);

	@Override
	public void init() {
		log.info("Initializing LoggingInterceptor...");
	}

	@Override
	public void destroy() {
		log.info("Destroying LoggingInterceptor...");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		String className = invocation.getAction().getClass().getName();
		long startTime = System.currentTimeMillis();
		log.info("Before calling action: " + className);

		String result = invocation.invoke();

		long endTime = System.currentTimeMillis();
		log.info("After calling action: " + className + " Time taken: " + (endTime - startTime) + " ms");

		return result;
	}

}