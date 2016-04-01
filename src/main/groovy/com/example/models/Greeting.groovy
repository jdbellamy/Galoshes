package com.example.models

import org.springframework.data.annotation.Id

class Greeting {

    @Id String id

    String phrase = "Hello"
    String who

    @Override
    String toString() {
        "{phrase: $phrase, who: $who}"
    }
}
