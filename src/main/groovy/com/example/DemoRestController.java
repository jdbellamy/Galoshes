package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @RequestMapping("/hello")
    public Greeting hello(Greeting greeting) {
        greeting.setPhrase("Hi");
        greeting.setWho("Me");
        return greeting;
    }
}
