package com.example.controllers

import com.example.services.SignalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import java.time.LocalDateTime

@Controller
@RequestMapping(path="/signal")
class SignalController {

    @Autowired SignalService service

    @Value('${api.hello.url}') String helloUrl

    @RequestMapping(path = '/{id}')
    def putHello(@PathVariable String id) {
        new ModelAndView('getSignal', [
            'time': LocalDateTime.now(),
            'signal': service.getSignal(id)
        ])
    }

    @RequestMapping('/')
    def index() {
        new ModelAndView('info',[
            'time': LocalDateTime.now(),
            'signals': service.signals()
        ])
    }

    @RequestMapping('/correlated/{corrId}')
    def byCorrelation(@PathVariable String corrId) {
        new ModelAndView('info',[
            'time': LocalDateTime.now(),
            'signals': service.correlated(corrId)
        ])
    }
}
