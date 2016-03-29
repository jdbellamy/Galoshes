package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.out;

@Aspect
@Component
public class ServiceMonitor {

    @Pointcut("execution(public * *(..))")
    public void anyPublicMethod() {}

    @Pointcut("execution(* com.example..*Helper.*(..))")
    public void anyHelperMethod() {}

    @After("anyHelperMethod()")
    public void logHelperAccess(JoinPoint joinPoint) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("helper.signature", joinPoint.getSignature());
        logMeta(meta);
    }

    @Around("anyPublicMethod() && @annotation(detailed)")
    public Object detail(ProceedingJoinPoint joinPoint, Detailed detailed) throws Throwable {
        Map<String, Object> meta = new HashMap<>();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        meta.put("details.return.type", method.getReturnType().getTypeName());
        meta.put("details.parameter.types",
                Arrays.asList(method.getParameterTypes()).stream()
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining()));
        meta.put("details.parameter.names",
                Arrays.asList(method.getParameters()).stream()
                        .map(Parameter::getName)
                        .collect(Collectors.joining()));
        meta.put("details.this.class.name", joinPoint.getThis().getClass().getSimpleName());
        meta.put("details.name", detailed.name());
        meta.put("details.type", detailed.type());
        Object retval = joinPoint.proceed();
        if (Greeting.class.equals(((MethodSignature)joinPoint.getSignature()).getReturnType())) {
            meta.put("details.return.greeting.phrase", ((Greeting)retval).getPhrase());
            meta.put("details.return.greeting.who", ((Greeting)retval).getWho());
        }
        logMeta(meta);
        return retval;
    }

    private void logMeta(Map meta) {
        out.println(meta);
    }
}