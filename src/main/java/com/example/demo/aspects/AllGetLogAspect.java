package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
// Cible toutes les méthodes des packages qui commencent par find
public class AllGetLogAspect {

	@Before("execution(* com.example.demo..find*(..))")
	public void logAfterGet(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();

		System.out.println("Nom de la méthode find : " + methodName + " , dans le package : " + className);
	}

}
