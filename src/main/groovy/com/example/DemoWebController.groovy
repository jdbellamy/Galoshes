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
class DemoWebController {

    @Autowired
    GreetingService greetingService

    @Value('${api.hello.url}')
    String helloUrl

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
            'time': LocalDateTime.now(),
            'greeting': new Greeting(who: "You"),
            'r': new Random().doubles().limit(3).toArray()
        ])
    }

    @RequestMapping('/api')
    def call() {
        def api = greetingService.helloApi(helloUrl)
        new ModelAndView('hello', [
            'time': LocalDateTime.now(),
            'greeting': new Greeting(phrase: api.phrase, who: api.who)
        ])
    }
}
