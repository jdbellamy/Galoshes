package com.example.services

import com.example.dao.GreetingRepository
import com.example.logging.CorrelationContext
import com.example.logging.Detailed
import com.example.models.Greeting
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import static com.example.logging.Detailed.DetailType.API_CONSUMER
import static com.example.logging.Detailed.DetailType.DB_CLIENT

@Service
class GreetingService {

    @Value('${api.hello.url}') String url
    @Value('${api.getGreeting.name:World}') def name
    @Value('${api.getGreeting.phrase:Hello}') def phrase

    @Autowired GreetingRepository repository

    @Detailed(name="greetings", type=DB_CLIENT)
    public List<Greeting> greetings() {
        repository.findAll()
    }

    @Detailed(name="getGreeting", type=DB_CLIENT)
    public Greeting getGreeting(String id) {
        repository.findOne(id)
    }

    @Detailed(name="insertGreeting", type=DB_CLIENT)
    public void insertGreeting(String who, String phrase) {
        def greeting = new Greeting(who: who)
        if(phrase != null) greeting.setPhrase(phrase)
        repository.save(greeting)
    }

    @Detailed(name="deleteGreeting", type=DB_CLIENT)
    public void deleteGreeting(String id) {
        repository.delete(id)
    }

    @Detailed(name="updateHello", type=DB_CLIENT)
    public void updateGreeting(String id, String who, String phrase) {
        Greeting greeting = repository.findOne(id)
        if(who != null) greeting.setWho(who)
        if(phrase != null) greeting.setPhrase(phrase)
        repository.save(greeting)
    }

    @Detailed(name="callHello", type=API_CONSUMER)
    public List<Greeting> callHello() {
        def headers = ['Correlation-Id': CorrelationContext.id]
        def json = url.toURL().getText(requestProperties: headers)
        new JsonSlurper().parseText(json) as List<Greeting>
    }
}
