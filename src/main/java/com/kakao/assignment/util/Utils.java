package com.kakao.assignment.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	
	public static String makeBodyFromRequest(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		String paramString = "";
		for(String key : paramMap.keySet()) {
			if(key != null) {
				for(String value: paramMap.get(key)) {
					paramString = paramString + key + ":" + value + " ";
				}
			}
		}
		return paramString;
	}
	
	public static String makeHeaderFromRequest(HttpServletRequest request) {
		
		Enumeration<String> e = request.getHeaderNames();
		
		String headerData = "";
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			headerData = headerData + key + ":" + request.getHeader(key) + " ";
		}
		
		return headerData;
	}
}
