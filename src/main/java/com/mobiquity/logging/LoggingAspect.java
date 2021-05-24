package com.mobiquity.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut(value="execution(* com.mobiquity.service.*.*.*(..) )")
	public void servicePointCut() {
		
	}
	
	@Pointcut(value="execution(* com.mobiquity.packer.*.*(..) )")
	public void packerPointCut() {
		
	}
	
	@Around("(servicePointCut() || packerPointCut())")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		log.info("method invoked " + className + " : " + methodName + "()" + "arguments {} : ", pjp.getArgs());
		Object object = pjp.proceed();
		log.debug(className + " : " + methodName + "()" + "Response {} : ",object);
		log.info(className + " : " + methodName + "() OutSide");
		return object;
	}
}