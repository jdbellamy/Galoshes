package com.example

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import static com.example.Detailed.DetailType.PROPERTY_BACKED

@Service
class ApiService {

    @Value('${api.hello.name:World}') def name
    @Value('${api.hello.phrase:Hello}') def phrase

    @Detailed(name="greet", type=PROPERTY_BACKED)
    public Greeting greet() {
        new Greeting(who: name, phrase: phrase)
    }
}
