package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import java.time.LocalDateTime

@Controller
@RequestMapping(path = "/web")
class WebController {

    @Autowired GreetingService greetingService
    @Autowired OtherService otherService

    @Value('${api.hello.url}') String helloUrl

    @RequestMapping('/hello')
    def index() {
        new ModelAndView('hello', [
            'time': LocalDateTime.now(),
            'greeting': new Greeting(who: "You")
        ])
    }

    @RequestMapping('/templates/{template}')
    def info(@PathVariable String template) {
        new ModelAndView(template, [
            'r': otherService.getDoubles(3).toArray()
        ])
    }

    @RequestMapping('/api')
    def call() {
        def api = greetingService.callHello()
        new ModelAndView('hello', [
            'time': LocalDateTime.now(),
            'greeting': new Greeting(phrase: api.phrase, who: api.who)
        ])
    }
}
