package com.example.dao

import com.example.models.Greeting;
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
public interface GreetingRepository extends MongoRepository<Greeting, String> {

    List<Greeting> findByWho(String who)
    List<Greeting> findByPhrase(String phrase)

}