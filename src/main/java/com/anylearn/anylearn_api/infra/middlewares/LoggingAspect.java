package com.anylearn.anylearn_api.infra.middlewares;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.anylearn..*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) 
    throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.debug(">>>======= Entering Method: {}", joinPoint.getSignature());

        Object resuObject;
        try {
            resuObject = joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            logger.debug("=======>>> Exitting method {} (Time taken: {}ms)", joinPoint.getSignature(), endTime - startTime);
        }
        return resuObject;
    }
}
