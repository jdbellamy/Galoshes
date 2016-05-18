package com.example.controllers

import com.example.logging.Detailed
import com.example.models.Greeting
import com.example.services.GreetingService
import com.example.services.SignalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import static com.example.logging.Detailed.DetailType.API_PROVIDER
import static org.springframework.web.bind.annotation.RequestMethod.*

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @Autowired GreetingService greetingService
    @Autowired SignalService signalService

    @Detailed(name="getGreeting", type=API_PROVIDER)
    @RequestMapping(path='/hello/{id}', method=GET)
    def getGreeting(@PathVariable String id) {
        greetingService.getGreeting(id)
    }

    @Detailed(name="getGreetings", type=API_PROVIDER)
    @RequestMapping(path='/hello', method=GET)
    def greetings() {
        greetingService.greetings()
    }

    @Detailed(name="putGreeting", type=API_PROVIDER)
    @RequestMapping(path='/hello', method=PUT)
    def putGreeting(@RequestBody Greeting greeting) {
        greetingService.insertGreeting(greeting.who, greeting.phrase)
    }

    @Detailed(name="updateGreeting", type=API_PROVIDER)
    @RequestMapping(path='/hello/{id}', method=POST)
    def postGreeting(@PathVariable String id,
                     @RequestParam(required = false) String who,
                     @RequestParam(required = false) String phrase) {
        greetingService.updateGreeting(id, who, phrase)
    }

    @Detailed(name="deleteGreeting", type=API_PROVIDER)
    @RequestMapping(path='/hello/{id}', method=DELETE)
    def deleteGreeting(@PathVariable String id) {
        greetingService.deleteGreeting(id)
    }

}
