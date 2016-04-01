package com.example.controllers

import com.example.logging.Detailed
import com.example.services.GreetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import java.time.LocalDateTime

import static com.example.logging.Detailed.DetailType.INTERNAL_REFERENCE
import static org.springframework.web.bind.annotation.RequestMethod.GET

@Controller
@RequestMapping(path = "/hello")
class GreetingController {

    @Autowired GreetingService greetingService

    @Value('${api.hello.url}') String helloUrl

    @RequestMapping(path = '/{id}', method = GET)
    def putHello(@PathVariable String id) {
        new ModelAndView('hello', [
            'time': LocalDateTime.now(),
            'greeting': greetingService.getGreeting(id)
        ])
    }

    @RequestMapping('/')
    def index() {
        new ModelAndView('hellos',[
            'time': LocalDateTime.now(),
            'greetings': greetingService.greetings()
        ])
    }

    @RequestMapping('/call')
    @Detailed(name='call', type=INTERNAL_REFERENCE)
    def call() {
        new ModelAndView('hellos', [
            'time': LocalDateTime.now(),
            'greeting': greetingService.callHello()
        ])
    }
}
