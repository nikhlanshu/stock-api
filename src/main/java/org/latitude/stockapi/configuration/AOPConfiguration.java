package org.latitude.stockapi.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@ConditionalOnProperty(prefix = "api.aop", name = "enabled", havingValue = "true")
public class AOPConfiguration {
    @Around("@annotation(org.latitude.stockapi.annotation.LogRequestResponse)")
    public Object logRequestResponse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger((proceedingJoinPoint.getTarget().getClass()));
        logger.info(String.format("Input received %s ", proceedingJoinPoint.getArgs()));
        long start = System.currentTimeMillis();
        Object response = proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(String.format("Response %s received in millis %d ", response, executionTime));
        return response;
    }
}
