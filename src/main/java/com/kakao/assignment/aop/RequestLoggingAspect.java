package com.kakao.assignment.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
public class RequestLoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

	private String paramMapToString(Map<String, String[]> paramMap) {
		String paramString = "";
		for(String header : paramMap.keySet()) {
			if(header != null) {
				for(String value: paramMap.get(header)) {
					paramString = paramString + header + ":" + value + " ";
				}
			}
		}
		return paramString;
	}

	@Pointcut("within(com.kakao.assignment.controller..*)") 
	public void onRequest() {}

	@Around("com.kakao.assignment.aop.RequestLoggingAspect.onRequest()") 
	public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
		  
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		  

		Map<String, String[]> paramMap = request.getParameterMap();
		String params = "";
		if (paramMap.isEmpty() == false) {
			params = paramMapToString(paramMap);
		}

		long start = System.currentTimeMillis();
		try {
			return pjp.proceed(pjp.getArgs()); // 6
		} finally {
			long end = System.currentTimeMillis();
			logger.debug("Request: {} {}:{}{} {}  ({}ms)", request.getMethod(), request.getLocalName(), request.getLocalPort(), request.getRequestURI(), params, end - start);
		} 
	}
}
