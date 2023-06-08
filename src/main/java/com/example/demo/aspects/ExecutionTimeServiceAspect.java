package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// Temps d'exécution pour toutes les méthodes de toutes les classes du package Service
public class ExecutionTimeServiceAspect {
	
	@Around("execution(* com.example.demo.service.*.*(..))")
	// ProceedingJoinPoint utilisé avec le @Around
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable { 
		
		// Enregistre le temps actuel en ms avec l'éxécution de la méthode
        long start = System.currentTimeMillis();

        //  Invoque la méthode originale proceed()
        Object proceed = joinPoint.proceed();

        // Temps d'exécution de la méthode originale (tps départ - tps actuel)
        long executionTime = System.currentTimeMillis() - start;

        System.out.println("La méthode : " + joinPoint.getSignature() + " a pris " + executionTime + "ms");

        // Renvoie le résultat de la méthode originale
        return proceed;
    }
}
