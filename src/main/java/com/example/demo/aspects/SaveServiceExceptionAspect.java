package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// S'exécutera lorsque la méthode save lancera une exception
public class SaveServiceExceptionAspect {
	
	@AfterThrowing(pointcut = "execution(* com.example.demo.service.PersonService.save(..))", throwing = "ex")
	public void logAfterThrowingAllMethods(JoinPoint joinPoint, Exception ex) {
		System.out.println("Exception dans PersonService.save() : " + ex.getMessage() + " dans la méthode : " + joinPoint.getSignature().getName());
	}
}
