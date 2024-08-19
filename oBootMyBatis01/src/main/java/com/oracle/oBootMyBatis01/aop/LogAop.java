package com.oracle.oBootMyBatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	// "com.oracle.oBootMyBatis01.dao" package 안의 "EmpDao" 이름을 가진 모든 것 
	@Pointcut("within(com.oracle.oBootMyBatis01.dao.EmpDao*)")
	private void pointcutMetohd() {
		
	}
	
	@Around("pointcutMetohd()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		String singnatureStr = joinPoint.getSignature().toShortString();
		System.out.println("loggerAop(ProceedingJoinPoint joinPoint)" + singnatureStr + "is started");
		
		long st = System.currentTimeMillis();
		
		try {
			// Business Work 핵심 관심사
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println("loggerAop(ProceedingJoinPoint joinPoint)" + singnatureStr + "is finished");
			System.err.println("loggerAop(ProceedingJoinPoint joinPoint)" + singnatureStr + "Elapsed Time : " + (et - st));
		}
	}
	
	@Before("pointcutMetohd()")
	public void beforeMethod() {
		System.out.println("LogAop beforeMethod() is started");
	}
}
