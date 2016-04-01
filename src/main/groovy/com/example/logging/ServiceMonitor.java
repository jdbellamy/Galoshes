package com.example.logging;

import com.example.dao.SignalRepository;
import com.example.models.Greeting;
import com.example.models.SignalElement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Aspect
@Component
public class ServiceMonitor {

    @Autowired
    SignalRepository repository;

    @Pointcut("execution(public * *(..))")
    public void anyPublicMethod() {}

    @Around("anyPublicMethod() && @annotation(detailed)")
    public Object detail(ProceedingJoinPoint joinPoint, Detailed detailed) throws Throwable {
        SignalElement element = new SignalElement();
        element.setCorrelationId(CorrelationContext.getId());
        element.setTransactionId(UUID.randomUUID().toString());
        Map<String, String> meta = new HashMap<>();
        meta.put("target_type", detailed.type().getClass().getSimpleName());
        meta.put("target_name", detailed.name());
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        meta.put("target_parameter_types",
                Arrays.asList(method.getParameterTypes()).stream()
                        .map(Class::getSimpleName)
                        .collect(Collectors.joining(", ")));
        meta.put("target_parameter_names",
                Arrays.asList(method.getParameters()).stream()
                        .map(Parameter::getName)
                        .collect(Collectors.joining(", ")));
        meta.put("target_this_class_name", joinPoint.getThis().getClass().getSimpleName());
        meta.put("target_return_type", method.getReturnType().getTypeName());
        Object retval = joinPoint.proceed();
        if (Greeting.class.equals(((MethodSignature)joinPoint.getSignature()).getReturnType())) {
            meta.put("target_return_greeting_phrase", ((Greeting)retval).getPhrase());
            meta.put("target_return_greeting_who", ((Greeting)retval).getWho());
        }
        element.setDetails(meta);
        repository.save(element);
        return retval;
    }
}