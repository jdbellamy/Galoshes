package com.example.controllers

import com.example.logging.Detailed
import com.example.services.GreetingService
import com.example.services.SignalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.*

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @Autowired GreetingService greetingService
    @Autowired SignalService signalService

    @RequestMapping(path='/hello/{id}', method=GET)
    def getGreeting(@PathVariable String id) {
        greetingService.getGreeting(id)
    }

    @Detailed(name="greetings", type=Detailed.DetailType.INTERNAL_REFERENCE)
    @RequestMapping(path='/hello', method=GET)
    def greetings() {
        greetingService.greetings()
    }

    @RequestMapping(path='/hello', method=PUT)
    def putGreeting(@RequestParam String who,
                    @RequestParam(required = false) String phrase) {
        greetingService.insertGreeting(who, phrase)
    }

    @RequestMapping(path='/hello/{id}', method=POST)
    def postGreeting(@PathVariable String id,
                     @RequestParam(required = false) String who,
                     @RequestParam(required = false) String phrase) {
        greetingService.updateGreeting(id, who, phrase)
    }

    @RequestMapping(path='/hello/{id}', method=DELETE)
    def deleteGreeting(@PathVariable String id) {
        greetingService.deleteGreeting(id)
    }

    @RequestMapping(path='/signal/correlated/{id}')
    def correlated(@PathVariable String id) {
        signalService.correlated(id)
    }

    @RequestMapping('/signal')
    def index() {
        signalService.signals()
    }
}
