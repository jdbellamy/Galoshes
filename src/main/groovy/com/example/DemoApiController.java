package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class DemoApiController {

    @RequestMapping("/hello")
    public Greeting hello(Greeting greeting) {
        greeting.setPhrase("Hi");
        greeting.setWho("Me");
        return greeting;
    }
}
