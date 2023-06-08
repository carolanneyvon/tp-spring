package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
// Cible toutes les méthodes publiques des controllers
public class NameMethodLogAspect {

    @Before("execution(public * com.example.demo..*Controller.*(..))")
    public void logMethodName(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        System.out.println("Nom de la méthode : " + methodName + " , dans le controller : " + className);
    }
}
