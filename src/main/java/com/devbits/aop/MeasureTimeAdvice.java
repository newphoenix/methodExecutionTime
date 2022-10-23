package com.devbits.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class MeasureTimeAdvice {

	@Around("@annotation(MeasureTime)")
	public Object executionDuration(ProceedingJoinPoint point) throws Throwable {
		long t1 = System.currentTimeMillis();
		Object object = point.proceed();
		long t2 = System.currentTimeMillis();

		log.info("""
				Class: {} .
				Method: {} .
				Execution time: {} ms."
				""", //
				point.getSignature().getDeclaringTypeName(), //
				point.getSignature().getName(), //
				(t2 - t1));

		return object;
	}

}
