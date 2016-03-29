package com.example

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import static com.example.Detailed.DetailType.*

@Service
class GreetingService {

    @Value('${api.hello.url}') String url

    @Detailed(name="callHello", type=API_CONSUMER)
    Greeting callHello() {
        new JsonSlurper().parseText(url.toURL().text) as Greeting
    }
}
