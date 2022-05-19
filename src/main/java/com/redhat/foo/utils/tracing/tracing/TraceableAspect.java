package com.redhat.foo.utils.tracing.tracing;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.opentracing.Span;
import io.opentracing.Tracer;


@Aspect
@Component
public class TraceableAspect {

    private static final Logger LOGGER=LoggerFactory.getLogger(TraceableAspect.class);

    @Autowired
    Tracer tracer;

    @Around("@annotation(Traceable)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String operationName = joinPoint.getTarget().getClass().getSimpleName() + " / " + signature.getMethod().getName();

        LOGGER.info("--------------> operationName: {}", operationName);
        
        Span newSpan = tracer.buildSpan(operationName).asChildOf(tracer.activeSpan()).start();

        Object proceed = joinPoint.proceed();

        newSpan.finish();

        return proceed;
    }
}
