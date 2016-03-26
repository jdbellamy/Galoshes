package com.example

import groovy.json.JsonSlurper
import org.springframework.stereotype.Service

@Service
class GreetingService {

    def helloApi(String url) {
        new JsonSlurper().parseText(url.toURL().text)
    }
}
