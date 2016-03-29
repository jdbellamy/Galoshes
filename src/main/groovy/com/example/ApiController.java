package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @Autowired
    private ApiService service;

    @RequestMapping("/hello")
    public Greeting hello() {
        return service.greet();
    }
}
