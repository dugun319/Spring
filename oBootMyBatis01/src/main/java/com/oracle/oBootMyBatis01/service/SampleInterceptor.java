package com.oracle.oBootMyBatis01.service;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SampleInterceptor implements HandlerInterceptor {
	public SampleInterceptor() {
		// TODO Auto-generated constructor stub
	}
	
	
	// interceptor #3
	@Override
	public void postHandle(	HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView)
							throws Exception {
		
		System.out.println("Post Handler is started");
		
		String ID	= (String)modelAndView.getModel().get("id");
		int memCnt	= (Integer)modelAndView.getModel().get("memCnt");
		
		System.out.println("SampleInterceptor postHandle memCnt -> " + memCnt);
		
		if(memCnt < 1) {
			System.out.println("SampleInterceptor postHandle memCnt is NOT exists");
			request.getSession().setAttribute("ID", ID);
			response.sendRedirect("/doMemberWrite");
		} else {
			System.out.println("SampleInterceptor postHandle memCnt is exists");
			request.getSession().setAttribute("ID", ID);
			response.sendRedirect("/doMemberList");
		}
		
	}
	
	
	
	// interceptor #1
	@Override
	public boolean preHandle(	HttpServletRequest request,
								HttpServletResponse response, 
								Object handler)
								throws Exception {
		
		System.out.println("Pre Handler is started");
		
		HandlerMethod method 	= (HandlerMethod) handler;
		Method methodObj		= method.getMethod();
		
		System.out.println("Bean : " + method.getBean());
		System.out.println("Method : " + methodObj);

		
		return true;
	}
}	
