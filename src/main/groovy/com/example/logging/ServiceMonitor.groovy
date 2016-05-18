package com.example.logging

import com.example.dao.SignalRepository
import com.example.models.Greeting
import com.example.models.SignalElement
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Aspect
@Component
class ServiceMonitor {

    @Autowired
    SignalRepository repository

    @Value('${signal.url}')
    String signalUrl;

    @Value('${signal.port}')
    String signalPort;

    @Around('@annotation(detailed)')
    Object detail(ProceedingJoinPoint joinPoint, Detailed detailed) throws Throwable {

        Object retval = joinPoint.proceed()

        def meta = [:]
        meta.put('target_type', detailed.type())
        meta.put('target_name', detailed.name())

        switch (joinPoint) {
            case Greeting:
                meta.put('greeting_phrase', retval.phrase())
                meta.put('greeting_who', retval.who())
                break
            case List:
                meta.put('greeting_count', retval.size())
                break
            default:
                meta.put('greeting_info', 'default')
        }

        def signal = SignalElement.builder()
                .timestamp(LocalDateTime.now())
                .correlationId(CorrelationContext.getId())
                .transactionId(UUID.randomUUID().toString())
                .details(meta)
                .build()

        println signal

        repository.save(signal)

//        def collectorUrl = "http://$signalUrl:$signalPort/signal/"
//        new RESTClient(collectorUrl).post { meta }

        return retval
    }

}